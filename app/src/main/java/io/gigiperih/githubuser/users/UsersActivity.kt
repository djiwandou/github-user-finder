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
import io.gigiperih.githubuser.uitls.RxPagingObservable
import io.gigiperih.githubuser.uitls.RxSearchObservable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class UsersActivity : BaseActivity<ActivityUsersBinding>() {
    private val viewModel: UsersViewModel by viewModels()
    private val usersAdapter = UsersAdapter()

    private var page = 2
    override fun layoutRes() = R.layout.activity_users

    override fun initView(savedInstanceState: Bundle?) {
        ViewCompat.setNestedScrollingEnabled(dataBinding.recyclerViewUser, true)

        RxSearchObservable.fromView(dataBinding.textInputSearch)
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isNotEmpty()) viewModel.findUsers(it, 1)
            }, {
                it.printStackTrace()
            })

        RxPagingObservable.fromView(dataBinding.recyclerViewUser)
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it) viewModel.findUsers(dataBinding.textInputSearch.text.toString(), page++)
            }, {
                it.printStackTrace()
            })

        with(dataBinding) {
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
        }

        viewModel.users.observe(this, { users ->
            usersAdapter.addItems(users.items)

            with(dataBinding) {
                textResult.text = getString(
                    R.string.result_search,
                    users.totalCount,
                    textInputSearch.text.toString()
                )
            }
        })

        viewModel.isLoading.observe(this, { isLoading ->
//            with(dataBinding) {
//                if (isLoading) {
//                    recyclerViewUser.gone()
//                    loadingAnimation.visible()
//                } else {
//                    recyclerViewUser.visible()
//                    loadingAnimation.gone()
//                }
//            }

        })
    }
}