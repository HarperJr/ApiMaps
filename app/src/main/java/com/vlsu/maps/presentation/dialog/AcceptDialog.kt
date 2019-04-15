package com.vlsu.maps.presentation.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vlsu.maps.R
import com.vlsu.maps.extensions.args
import com.vlsu.maps.extensions.withArgs
import kotlinx.android.synthetic.main.dialog_accept.*

class AcceptDialog : DialogFragment() {

    private val title by args(ARGS_ACCEPT_TITLE, "")
    private val subtitle by args(ARGS_CANCEL_SUBTITLE, "")
    private val acceptBtnLabel by args(ARGS_ACCEPT_BTN_LABEL, "")
    private val cancelBtnLabel by args(ARGS_CANCEL_BTN_LABEL, "")

    var acceptBtnClickListener: (() -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(
            R.layout.dialog_accept, container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog_accept_title.text = title
        dialog_accept_subtitle.text = subtitle
        with(dialog_accept_btn_cancel) {
            if (cancelBtnLabel.isNotEmpty()) text = cancelBtnLabel
            setOnClickListener { dismiss() }
        }
        with(dialog_accept_btn_accept) {
            if (acceptBtnLabel.isNotEmpty()) text = acceptBtnLabel
            setOnClickListener { acceptBtnClickListener?.invoke() }
        }
    }

    companion object {
        fun newInstance(
            title: String, subtitle: String, acceptLabel: String = "", cancelLabel: String = ""
        ) = AcceptDialog()
            .withArgs {
                putString(ARGS_ACCEPT_TITLE, title)
                putString(ARGS_CANCEL_SUBTITLE, subtitle)
                putString(ARGS_ACCEPT_BTN_LABEL, acceptLabel)
                putString(ARGS_CANCEL_BTN_LABEL, cancelLabel)
            }

        private const val ARGS_ACCEPT_TITLE = "ARGS_ACCEPT_TITLE"
        private const val ARGS_CANCEL_SUBTITLE = "ARGS_CANCEL_SUBTITLE"
        private const val ARGS_ACCEPT_BTN_LABEL = "ARGS_ACCEPT_BTN_LABEL"
        private const val ARGS_CANCEL_BTN_LABEL = "ARGS_CANCEL_BTN_LABEL"
    }
}