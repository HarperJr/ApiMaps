package com.vlsu.maps.presentation.fragment.notifying


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.vlsu.maps.R
import com.vlsu.maps.di.Dagger
import com.vlsu.maps.domain.model.NotificationType
import kotlinx.android.synthetic.main.fragment_notifying.*

class NotifyingFragment : MvpFragment<NotifyingView, NotifyingPresenter>(), NotifyingView {

    private val component = Dagger.appComponent.notifyingComponent()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_notifying, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notifying_accept_btn.setOnClickListener { presenter.onAcceptBtnClicked() }
        notifying_comment_edit_text.addTextChangedListener(notifyingTextWatcher)

        notifying_type_ambulance.setOnClickListener {
            presenter.onNotificationTypeChanged(NotificationType.AMBULANCE)
        }
        notifying_type_support.setOnClickListener {
            presenter.onNotificationTypeChanged(NotificationType.SUPPORT_REQUEST)
        }
        notifying_type_collapse.setOnClickListener {
            presenter.onNotificationTypeChanged(NotificationType.COLLAPSE)
        }
        notifying_type_message.setOnClickListener {
            presenter.onNotificationTypeChanged(NotificationType.MESSAGE)
        }
    }

    override fun setNotificationType(notificationType: NotificationType) {
        val typeLabel = when (notificationType) {
            NotificationType.AMBULANCE -> getString(R.string.notifying_type_ambulance)
            NotificationType.COLLAPSE -> getString(R.string.notifying_type_collapse)
            NotificationType.SUPPORT_REQUEST -> getString(R.string.notifying_type_support)
            NotificationType.MESSAGE -> getString(R.string.notifying_type_message)
            else -> ""
        }
        notifying_type_label.text = typeLabel
    }

    override fun createPresenter(): NotifyingPresenter {
        return component.notifyingPresenter()
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
