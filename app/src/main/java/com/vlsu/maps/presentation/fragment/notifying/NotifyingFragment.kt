package com.vlsu.maps.presentation.fragment.notifying


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import com.vlsu.maps.domain.model.NotificationType
import kotlinx.android.synthetic.main.fragment_notifying.*

class NotifyingFragment : MvpFragment<NotifyingView, NotifyingPresenter>(), NotifyingView {

    private val component = Dagger.appComponent.notifyingComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_notifying, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notifying_accept_btn.setOnClickListener { presenter.onAcceptBtnClicked() }
        notifying_comment_edit_text.addTextChangedListener(notifyingTextWatcher)
        notifying_type_radio_group.setOnCheckedChangeListener(onNotifyingTypeChangedListener)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun createPresenter(): NotifyingPresenter {
        return component.notifyingPresenter()
    }

    private val onNotifyingTypeChangedListener = { _: RadioGroup, selected: Int ->
        when (selected) {
            R.id.notifying_type_support -> presenter.onNotificationTypeChanged(NotificationType.SUPPORT_REQUEST)
            R.id.notifying_type_collapse -> presenter.onNotificationTypeChanged(NotificationType.COLLAPSE)
            R.id.notifying_type_ambulance -> presenter.onNotificationTypeChanged(NotificationType.AMBULANCE)
            R.id.notifying_type_message -> presenter.onNotificationTypeChanged(NotificationType.MESSAGE)
        }
    }

    private val notifyingTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            presenter.onNotifyingTextChanged(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    }

    companion object {
        fun newInstance() = NotifyingFragment()
    }
}