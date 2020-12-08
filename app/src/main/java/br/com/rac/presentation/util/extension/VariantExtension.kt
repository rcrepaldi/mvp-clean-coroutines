package br.com.rac.presentation.util.extension

import br.com.rac.BuildConfig

inline fun runWhenDebug(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    }
}

inline fun runWhenRelease(block: () -> Unit) {
    if (!BuildConfig.DEBUG) {
        block()
    }
}