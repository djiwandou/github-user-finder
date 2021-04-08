package io.gigiperih.githubuser.users

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.gigiperih.githubuser.R
import io.gigiperih.githubuser.arch.BaseActivity
import io.gigiperih.githubuser.databinding.ActivityUsersBinding
import io.gigiperih.githubuser.domain.entity.ResponseUser
import io.gigiperih.githubuser.uitls.RxPagingObservable
import io.gigiperih.githubuser.uitls.RxSearchObservable
import io.gigiperih.githubuser.uitls.ext.gone
import io.gigiperih.githubuser.uitls.ext.hideSoftKeyboard
import io.gigiperih.githubuser.uitls.ext.observe
import io.gigiperih.githubuser.uitls.ext.visible
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class UsersActivity : BaseActivity<ActivityUsersBinding>() {
    private val viewModel: UsersViewModel by viewModels()
    private val usersAdapter = UsersAdapter()

    private val startingPage = 1
    private var currentPage = 2

    override fun layoutRes() = R.layout.activity_users

    override fun initView(savedInstanceState: Bundle?) {
        observe(viewModel.usersState, ::bindState)

        ViewCompat.setNestedScrollingEnabled(dataBinding.recyclerViewUser, true)
        setupPagingObserver()
        setupSearchObserver()
        setupRecyclerView()
    }

    private fun setupPagingObserver() {
        RxPagingObservable.fromView(dataBinding.recyclerViewUser)
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it) viewModel.findUsers(
                    dataBinding.textInputSearch.text.toString(),
                    currentPage++
                )
            }, {
                it.printStackTrace()
            })

    }

    private fun setupSearchObserver() {
        RxSearchObservable.fromView(dataBinding.textInputSearch)
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isNotEmpty()) {
                    usersAdapter.reset()
                    viewModel.findUsers(it, startingPage)
                }
            }, {
                it.printStackTrace()
            })

    }

    private fun setupRecyclerView() {
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

    }

    private fun bindState(state: UsersState<ResponseUser>) {
        when (state) {
            is UsersState.OnInitialLoading -> {
                with(dataBinding) {
                    recyclerViewUser.gone()
                    loadingAnimation.visible()
                }
            }
            is UsersState.OnLoadMore -> {
                // TODO: add loading item view type in recyclerView
            }
            is UsersState.OnSuccess -> {
                val users = state.data
                users?.let { response ->
                    usersAdapter.addItems(response.items)

                    hideSoftKeyboard()
                    with(dataBinding) {
                        recyclerViewUser.visible()
                        loadingAnimation.gone()

                        textResult.text = getString(
                            R.string.result_search,
                            response.totalCount,
                            textInputSearch.text.toString()
                        )
                    }
                }

            }
            is UsersState.OnCompletelyLoaded -> {
                Snackbar.make(
                    dataBinding.root,
                    getString(R.string.all_pages_are_displayed),
                    Snackbar.LENGTH_LONG
                ).show()
            }
            is UsersState.OnError -> {
                val error = state.message
                with(dataBinding) {
                    recyclerViewUser.gone()
                    loadingAnimation.visible()
                    textResult.text = error
                }
            }
        }
    }

}