package jpcoliveira.com.br.testeandroid.contact

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import jpcoliveira.com.br.testeandroid.R
import jpcoliveira.com.br.testeandroid.fund.FundFragment
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

    override fun noInternetAvailable() {

    }

    override fun showMessageError(message: String?) {
        Log.i(FundFragment.TAG, "showMessageError " + message)
        Toast.makeText(activity, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }

    override fun getContextFrag(): Context? {
        return activity
    }
}