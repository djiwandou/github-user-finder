package io.gigiperih.githubuser.uitls

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject


object RxSearchObservable {
    fun fromView(textView: TextInputEditText): Observable<String> {
        val subject = PublishSubject.create<String>()
        textView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // do nuffin
            }

            override fun afterTextChanged(s: Editable?) {
                subject.onNext(s.toString())
            }
        })
        return subject
    }
}