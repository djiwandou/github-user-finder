package io.gigiperih.githubuser.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

object RxPagingObservable {
    fun fromView(recyclerView: RecyclerView): Observable<Boolean> {
        val subject = PublishSubject.create<Boolean>()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = recyclerView.layoutManager!!.childCount
                    val totalItemCount = recyclerView.layoutManager!!.itemCount
                    val pastVisibleItems =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        subject.onNext(true)
                    } else {
                        subject.onNext(false)
                    }
                }
            }
        })

        return subject
    }
}