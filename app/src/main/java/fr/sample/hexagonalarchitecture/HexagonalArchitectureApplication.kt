package fr.sample.hexagonalarchitecture

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber.*

import timber.log.Timber

@HiltAndroidApp
class HexagonalArchitectureApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}