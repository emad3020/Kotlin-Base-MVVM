package com.core.utils.annotations

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultScope

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoScope

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainScope