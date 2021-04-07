package io.gigiperih.githubuser.users

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.gigiperih.githubuser.R
import io.gigiperih.githubuser.arch.BaseActivity
import io.gigiperih.githubuser.databinding.ActivityUsersBinding
import io.gigiperih.githubuser.uitls.RxSearchObservable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class UsersActivity : BaseActivity<ActivityUsersBinding>() {
    private val viewModel: UsersViewModel by viewModels()

    override fun layoutRes() = R.layout.activity_users

    override fun initView(savedInstanceState: Bundle?) {
        ViewCompat.setNestedScrollingEnabled(dataBinding.recyclerViewUser, true)

        RxSearchObservable.fromView(dataBinding.textInputSearch)
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d("kememdiknas > $it")
                viewModel.findUsers(it)
            }, {
                it.printStackTrace()
            })



        viewModel.users.observe(this, { users ->
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
                    addItemDecoration(
                        DividerItemDecoration(
                            this@UsersActivity,
                            RecyclerView.VERTICAL
                        )
                    )
                    adapter = usersAdapter
                }

                textResult.text =
                    "Found ${users.totalCount} data; keywords \"${textInputSearch.text.toString()}\"."
            }
        })
    }
}