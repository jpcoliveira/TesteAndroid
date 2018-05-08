package jpcoliveira.com.br.testeandroid.contact

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import jpcoliveira.com.br.testeandroid.R
import jpcoliveira.com.br.testeandroid.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.fragment_fund.*
import kotlinx.android.synthetic.main.include_unavailable_connection.*

class ContactFragment : BaseFragment(), ContactContract.View {

    private var presenter: ContactContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_retry.setOnClickListener({ presenter?.buildForm() })
    }

    override fun setPresenter(presenter: ContactContract.Presenter) {
        this.presenter = presenter
    }

    override fun onResume() {
        super.onResume()
        presenter?.buildForm()
    }

    override fun showLayout(layout: LinearLayout?) {
        layout.let {
            internet_unavailable?.visibility = View.GONE
            container_content?.visibility = View.VISIBLE
            container_contact?.removeAllViews()
            container_contact?.addView(layout)
        }
    }

    override fun clickSendMessage() {
        presenter?.sendMessage()
    }

    override fun noInternetAvailable() {
        super.noInternetAvailable()
        container_content?.visibility = View.GONE
    }

    override fun getTextById(resId: Int?): String? {
        return container_contact?.findViewById<TextInputLayout>(resId!!)?.editText?.text?.toString()
    }

    override fun isEnable(resId: Int?): Boolean {
        return container_contact?.findViewById<View>(resId!!)?.visibility == View.VISIBLE
    }

    override fun isFieldValidationError(resId: Int): Boolean {
        return container_contact?.findViewById<TextInputLayout>(resId)?.isErrorEnabled!!
    }

    override fun sendMessage() {
        Toast.makeText(activity, "sendMessage", Toast.LENGTH_SHORT).show()
    }

    override fun getContextFrag(): Context? {
        return activity
    }
}