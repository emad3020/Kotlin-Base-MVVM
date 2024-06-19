package com.core.utils.mappers

interface CoreMapper {

  fun <FirstModel, SecondModel> map(model: FirstModel): SecondModel
}