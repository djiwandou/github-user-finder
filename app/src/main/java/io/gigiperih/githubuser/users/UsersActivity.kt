package io.gigiperih.githubuser.users

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.gigiperih.githubuser.R

@AndroidEntryPoint
class UsersActivity : AppCompatActivity(R.layout.activity_users) {
    private val viewModel: UsersViewModel by viewModels()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        viewModel.load()
    }
}