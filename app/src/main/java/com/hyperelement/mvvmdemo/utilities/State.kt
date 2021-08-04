package com.hyperelement.mvvmdemo.utilities

open class State

data class LoadingState(val tag: Any? = null) : State()

data class SuccessState(val tag: Any? = null, val message: String = "") : State()

data class EmptyState(val tag: Any? = null) : State()

data class ErrorState(val tag: Any? = null, val throwable: Throwable) : State()

data class MsgState(val tag: Any? = null) : State()