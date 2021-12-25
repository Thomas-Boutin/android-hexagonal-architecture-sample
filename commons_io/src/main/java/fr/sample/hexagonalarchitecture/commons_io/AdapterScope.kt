package fr.sample.hexagonalarchitecture.commons_io

import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

abstract class AdapterScope(private val context: CoroutineContext) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = context
}
