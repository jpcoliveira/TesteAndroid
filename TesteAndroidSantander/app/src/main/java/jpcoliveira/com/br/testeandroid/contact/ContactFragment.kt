package jpcoliveira.com.br.testeandroid.contact

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import jpcoliveira.com.br.testeandroid.R

class ContactFragment : Fragment(), ContactContract.View {

    private lateinit var presenter: ContactContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)

        return view
    }

    override fun setPresenter(presenter: ContactContract.Presenter) {
        this.presenter = presenter
    }

    override fun onResume() {
        super.onResume()

        presenter.buildForm()
    }

    override fun getContextFrag(): Context? {
        return activity
    }

    override fun showLayout(build: LinearLayout?) {
        TODO("not implemented") //add in view container
    }
}