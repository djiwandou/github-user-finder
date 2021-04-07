package io.gigiperih.githubuser.users

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.gigiperih.githubuser.R
import io.gigiperih.githubuser.arch.BaseActivity
import io.gigiperih.githubuser.databinding.ActivityUsersBinding
import timber.log.Timber

@AndroidEntryPoint
class UsersActivity : BaseActivity<ActivityUsersBinding>() {
    private val viewModel: UsersViewModel by viewModels()

    override fun layoutRes() = R.layout.activity_users

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.load()
        viewModel.users.observe(this, { users ->
            Timber.d("lnx $users")
            with(dataBinding) {
                val usersAdapter = UsersAdapter(users.items)
                usersAdapter.notifyDataSetChanged()

                recyclerViewUser.apply {
                    layoutManager =
                        LinearLayoutManager(
                            this@UsersActivity,
                            RecyclerView.VERTICAL,
                            false
                        )
                    addItemDecoration(DividerItemDecoration(this@UsersActivity, RecyclerView.VERTICAL))
                    adapter = usersAdapter
                }
            }
        })
    }
}