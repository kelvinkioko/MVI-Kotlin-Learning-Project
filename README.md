# Kotlin MVI learning project
Example MVI implementation, based off of Google's architectural samples.

## Description

This is a sample implementation of the Model View Intent pattern. Full Android examples for MVI are hard to find.

Target audience include, but not limited to:

- Developers making the pivot from an imperative to declarative style of programming.
- Developers looking for working example of functional or reactive programming on Android.

Here are some articles/examples explaining the core principles of MVI and unidirectional flow:

- http://hannesdorfmann.com/android/model-view-intent
- https://cycle.js.org/model-view-intent.html
- http://thenewstack.io/developers-need-know-mvi-model-view-intent/
- http://blog.danlew.net/2017/07/27/an-introduction-to-functional-reactive-programming/
- https://proandroiddev.com/mvi-a-new-member-of-the-mv-band-6f7f0d23bc8a

## Stack

One goal of this repo is to keep things as light as possible. The core libraries used are:

- Kotlin
- Room
- ViewModel
- RxJava 2
- RxBindings
- RxRelay
- Retrofit
- Toothpick DI

If you require a very lightweight project, and still want to do MVI, it's not unreasonable to think you could do without RxJava. As of writing, RxJava dependencies weighs in at about ~2MB.

Once you grasp the basics, I suggest you explore the following existing libraries:

- https://github.com/airbnb/MvRx
- https://github.com/badoo/MVICore
- https://github.com/freeletics/RxRedux
- https://github.com/groupon/grox
- https://github.com/spotify/mobius
- https://github.com/Tinder/StateMachine
