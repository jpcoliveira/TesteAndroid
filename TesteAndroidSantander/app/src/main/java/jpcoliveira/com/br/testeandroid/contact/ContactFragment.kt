package jpcoliveira.com.br.testeandroid.contact

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import jpcoliveira.com.br.testeandroid.R
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : Fragment(), ContactContract.View {

    private var presenter: ContactContract.Presenter? = null

    private val progress by lazy {
        ProgressDialog(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun setPresenter(presenter: ContactContract.Presenter) {
        this.presenter = presenter
    }

    override fun onResume() {
        super.onResume()
        presenter?.buildForm()
    }

    override fun showLayout(layout: LinearLayout?) {
        container_contact?.removeAllViews()
        container_contact?.addView(layout)
    }

    override fun showProgress() {
        progress.show()
    }

    override fun hideProgress() {
        progress.cancel()
    }

    override fun clickSendMessage() {
        presenter?.sendMessage()
    }

    override fun noInternetAvailable() {

    }

    override fun showMessageError(message: String?) {
        Toast.makeText(activity, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }

    override fun getTextById(id: Int?): String? {
        return container_contact?.findViewById<TextInputLayout>(id!!)?.editText?.text?.toString()
    }

    override fun showErrorValidateEmail(resId: Int?) {
        val editText = container_contact?.findViewById<TextInputLayout>(resId!!)
        editText?.error = getString(R.string.invalid_mail)
        editText?.isErrorEnabled = true
    }

    override fun showErrorValidatePhone(resId: Int?) {
        val editText = container_contact?.findViewById<TextInputLayout>(resId!!)
        editText?.error = getString(R.string.invalid_phone)
        editText?.isErrorEnabled = true
    }

    override fun showErrorValidateText(resId: Int?) {
        val editText = container_contact?.findViewById<TextInputLayout>(resId!!)
        editText?.error = getString(R.string.required_field)
        editText?.isErrorEnabled = true
    }

    override fun isEnable(resId: Int?): Boolean {
        return container_contact?.findViewById<TextInputLayout>(resId!!)?.visibility == View.VISIBLE
    }

    override fun sendMessage() {
        Toast.makeText(activity, "sendMessage", Toast.LENGTH_SHORT).show()
    }

    override fun getContextFrag(): Context? {
        return activity
    }
}