package io.github.diamantidis.feedreader

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

internal actual fun ApplicationScope(): CoroutineScope = MainScope()
