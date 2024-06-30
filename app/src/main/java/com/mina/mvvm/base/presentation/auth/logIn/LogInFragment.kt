package com.mina.mvvm.base.presentation.auth.logIn

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView.BufferType.SPANNABLE
import androidx.fragment.app.viewModels
import com.core.presentation.R
import com.core.presentation.base.BaseFragment
import com.core.presentation.base.utils.showSoftInput
import com.core.presentation.extensions.collect
import com.core.presentation.extensions.getMyColor
import com.core.presentation.extensions.handleApiError
import com.core.presentation.extensions.hideKeyboard
import com.core.presentation.extensions.navigateSafe
import com.core.presentation.extensions.openActivityAndClearStack
import com.core.presentation.extensions.showSnackBar
import com.core.utils.Resource
import com.mina.base.mvvm.databinding.FragmentLoginBinding
import com.mina.mvvm.base.domain.auth.enums.AuthFieldsValidation
import com.mina.mvvm.base.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLoginBinding, LogInViewModel>(
  FragmentLoginBinding::inflate
) {

  override val viewModel: LogInViewModel by viewModels()

  override fun FragmentLoginBinding.initializeUI() {
    setUpSignUpButton()
  }


  private fun setUpSignUpButton() {
    val finalMessage =
      "${resources.getString(R.string.not_have_account)} ${resources.getString(R.string.sign_up)}"

    val spanString = SpannableString(finalMessage)

    val clickableSpan = object : ClickableSpan() {
      override
      fun onClick(textView: View) {
        openSignUp()
      }
    }

    // Define my span
    spanString
      .setSpan(
        clickableSpan,
        finalMessage.indexOf(resources.getString(R.string.sign_up)),
        finalMessage.indexOf(resources.getString(R.string.sign_up)) + resources
          .getString(R.string.sign_up).length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
      )

    // Set span color
    spanString.setSpan(
      ForegroundColorSpan(getMyColor(R.color.blue)),
      finalMessage.indexOf(resources.getString(R.string.sign_up)),
      finalMessage.indexOf(resources.getString(R.string.sign_up)) + resources
        .getString(R.string.sign_up).length,
      Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    // Set span style
    spanString.setSpan(
      StyleSpan(Typeface.BOLD),
      finalMessage.indexOf(resources.getString(R.string.sign_up)),
      finalMessage.indexOf(resources.getString(R.string.sign_up)) + resources
        .getString(R.string.sign_up).length,
      Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    // Add underline to span
    spanString.setSpan(
      UnderlineSpan(),
      finalMessage.indexOf(resources.getString(R.string.sign_up)),
      finalMessage.indexOf(resources.getString(R.string.sign_up)) + resources
        .getString(R.string.sign_up).length,
      Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    binding.btnSignUp.movementMethod = LinkMovementMethod.getInstance()
    binding.btnSignUp.setText(spanString, SPANNABLE)
    binding.btnSignUp.isSelected = true
  }

  override fun setupObservers() {
    viewModel.openForgotPassword.observe(this) {
      openForgotPassword()
    }

    viewModel.validationException.observe(this) {
      when (it) {
        AuthFieldsValidation.EMPTY_EMAIL.value -> {
          binding.etEmail.requestFocus()
          showSoftInput(binding.etEmail, requireContext())
          requireView().showSnackBar(resources.getString(R.string.empty_email))
        }
        AuthFieldsValidation.INVALID_EMAIL.value -> {
          binding.etEmail.requestFocus()
          showSoftInput(binding.etEmail, requireContext())
          requireView().showSnackBar(resources.getString(R.string.invalid_email))
        }
        AuthFieldsValidation.EMPTY_PASSWORD.value -> {
          binding.etPassword.requestFocus()
          showSoftInput(binding.etPassword, requireContext())
          requireView().showSnackBar(resources.getString(R.string.empty_password))
        }
      }
    }

    collect(viewModel.logInResponse) {
      when (it) {
        Resource.Loading -> {
          hideKeyboard()
          showLoading()
        }
        is Resource.Success -> {
          hideLoading()
          openHome()
        }
        is Resource.Failure -> {
          hideLoading()
          handleApiError(it, failureAction = { viewModel.onLogInClicked() })
        }

        else -> {}
      }
    }
  }

  private fun openForgotPassword() {
    navigateSafe(LogInFragmentDirections.actionOpenForgotPasswordFragment())
  }

  private fun openSignUp() {
    navigateSafe(LogInFragmentDirections.actionOpenSignUpFragment())
  }

  private fun openHome() {
    requireActivity().openActivityAndClearStack(HomeActivity::class.java)
  }
}