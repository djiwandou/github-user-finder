package io.gigiperih.githubuser

import android.app.Application
import timber.log.Timber

class GithubUserApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}