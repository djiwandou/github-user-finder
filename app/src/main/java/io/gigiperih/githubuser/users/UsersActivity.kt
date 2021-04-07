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
import io.gigiperih.githubuser.uitls.ext.gone
import io.gigiperih.githubuser.uitls.ext.hideSoftKeyboard
import io.gigiperih.githubuser.uitls.ext.visible
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
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
                if (it.isNotEmpty()) viewModel.findUsers(it)
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

                hideSoftKeyboard()
                textResult.text =
                    "Found ${users.totalCount} data; keywords \"${textInputSearch.text.toString()}\"."
            }
        })

        viewModel.isLoading.observe(this, { isLoading ->
            with(dataBinding) {
                if (isLoading) {
                    recyclerViewUser.gone()
                    loadingAnimation.visible()
                } else {
                    recyclerViewUser.visible()
                    loadingAnimation.gone()
                }
            }

        })
    }
}