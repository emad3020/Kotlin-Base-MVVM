<h1 align="center">
Android Clean Architecture 
</h1>


<div align="center">
<a name="open_source"><img src="https://badges.frapsoft.com/os/v1/open-source.svg?v=102?style=for-the-badge"></a>
<a name="stars"><img src="https://img.shields.io/github/stars/Mina-Mikhail/Kotlin-Base-MVVM?style=for-the-badge"></a>
<a name="forks"><img src="https://img.shields.io/github/forks/Mina-Mikhail/Kotlin-Base-MVVM?logoColor=green&style=for-the-badge"></a>
<a name="contributions"><img src="https://img.shields.io/github/contributors/Mina-Mikhail/Kotlin-Base-MVVM?logoColor=green&style=for-the-badge"></a>
<a name="license"><img src="https://img.shields.io/github/license/sadanandpai/javascript-code-challenges?style=for-the-badge"></a>
</div>


:point_right: Clean Architecture:
-----------------
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture.png)


:point_right: Architectural Approach:
-----------------
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture_layers.png)


:point_right: Architectural Reactive Approach:
-----------------
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture_layers_details.png)


:point_right: Architecture:
-----------------
- Following Clean Architecture.
- MVVM Architecture.
- Repository pattern.
- Applying SOLID principles, each class has a single job with separation of concerns by making classes independent
  of each other and communicating with interfaces.
- Using Kotlin-KTS & buildSrc to handle project dependencies.


:point_right: Tech Stack & Libraries:
-----------------
- Navigation component - navigation graph for navigating and replacing screens/fragments
- DataBinding - allows to more easily write code that interacts with views and replaces ```findViewById```.
- ViewModel - UI related data holder, lifecycle aware.
- LiveData - Build data objects that notify views when the underlying database changes.
- Dagger-Hilt for dependency injection. Object creation and scoping is handled by Hilt.
- Kotlin Coroutines - for managing background threads with simplified code and reducing needs for callbacks
- Retrofit2 & OkHttp3 - to make REST requests to the web service integrated.
- Coil - for image loading.
- Material Bottom Navigation - to handle bottom tabs with support for multiple backStack.


:point_right: Project Structure:
-----------------
- Sample includes some basic features required in each project like :
  - Splash.
  - App Tutorial.
  - Login - (With Business Logic).
  - Sign Up - (Blank Screens).
  - Forgot Password -(Blanck Screens).
  - Home Screen - (Contains 3 Tabs with 3 NavGraphs).


:point_right: Extra Modules:
-----------------
- You will find extra modules also developed by me like :
  - AppTutorial - (To handle onBoarding tutorial screens).
  - ActionChooser - (A customized pop up with recyclerView of single selection).
  - PrettyPopUp (A customized pop up to display message to user with two actions (positive & negative buttons)).
  - ImagesSlider (An images slider supports auto scrolling for images from url and support GIF images).


:point_right: Code Style:
-----------
- Following official kotlin code style


:point_right: Apply Git Hooks:
-----------
- To apply git hooks in order to automate process of styling and checking your code, just follow this steps:
  - Copy ```pre-commit``` file from ```myGitHooks```.
  - Paste it into ```.git/hooks``` in your project.
- Now each time you commit your changes, ```ktlintFormat``` and  ```ktlintCheck``` will automatically run


:point_right: TO DO:
-----------
- [X] Apply ktlint for checking code style.
- [X] Use git hooks to automate code checking and styling before any new commit.
- [ ] Use Flow in Domain layer.
- [ ] Use UseCases.
- [X] Handle Different Build Variants.
- [ ] Explore full MVI implementation.
- [ ] Add some unit tests.


:point_right: Contributing to Project:
-----------
- Just make pull request. You are in! :thumbsup:


:point_right: Find this project useful ? :heart:
-----------
- Support it by clicking the :star: button on the upper right of this page. :v:


:point_right: Stargazers: :star:
-----------
[![Stargazers repo roster for @sadanandpai/javascript-code-challenges](https://reporoster.com/stars/Mina-Mikhail/Kotlin-Base-MVVM)](https://github.com/Mina-Mikhail/Kotlin-Base-MVVM/stargazers)


:point_right: Forkers: :hammer_and_pick:
-----------
[![Forkers repo roster for @sadanandpai/javascript-code-challenges](https://reporoster.com/forks/Mina-Mikhail/Kotlin-Base-MVVM)](https://github.com/Mina-Mikhail/Kotlin-Base-MVVM/network/members)



:warning: License:
--------
```
   Copyright (C) 2021 MINDORKS NEXTGEN PRIVATE LIMITED

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
