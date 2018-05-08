package jpcoliveira.com.br.testeandroid.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import jpcoliveira.com.br.testeandroid.R
import kotlinx.android.synthetic.main.include_unavailable_connection.*

open class BaseFragment :BaseView,  Fragment() {

    private val progress by lazy {
        ProgressDialog(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureProgress()
    }

    private fun configureProgress() {
        progress.setTitle(getString(R.string.wait))
        progress.setMessage(getString(R.string.loading_information))
        progress.setCancelable(false)
    }

    override fun showProgress() {
        progress.show()
    }

    override fun hideProgress() {
        progress.cancel()
    }

    override fun noInternetAvailable() {
        internet_unavailable?.visibility = View.VISIBLE
    }

    override fun showMessageError(message: String?) {
        Toast.makeText(activity, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }

}