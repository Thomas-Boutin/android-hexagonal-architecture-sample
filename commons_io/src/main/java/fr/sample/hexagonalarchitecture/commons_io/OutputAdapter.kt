package fr.sample.hexagonalarchitecture.commons_io

interface OutputAdapter {
    val adapterScopeMain: OutputAdapterScopeMain
    val adapterScopeWorker: OutputAdapterScopeWorker
}