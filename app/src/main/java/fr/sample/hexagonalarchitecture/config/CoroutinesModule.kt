package fr.sample.hexagonalarchitecture.config

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.sample.hexagonalarchitecture.commons_io.InputAdapterScope
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeMain
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeWorker
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {
    @Provides
    @Singleton
    fun provideInputAdapterScope(): InputAdapterScope {
        return InputAdapterScope(Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideOutputAdapterScopeMain(): OutputAdapterScopeMain {
        return OutputAdapterScopeMain(Dispatchers.Main)
    }

    @Provides
    @Singleton
    fun provideOutputAdapterScopeWorker(): OutputAdapterScopeWorker {
        return OutputAdapterScopeWorker(Dispatchers.IO)
    }
}