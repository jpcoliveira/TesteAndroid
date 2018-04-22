package jpcoliveira.com.br.testeandroid.fund

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jpcoliveira.com.br.testeandroid.R

class FundFragment : Fragment(), FundContract.View {

    private lateinit var presenter: FundContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fund, container, false)
        return view
    }

    override fun setPresenter(presenter: FundContract.Presenter) {
        this.presenter = presenter
    }

    override fun onResume() {
        super.onResume()
        presenter.getFunds()
    }
}