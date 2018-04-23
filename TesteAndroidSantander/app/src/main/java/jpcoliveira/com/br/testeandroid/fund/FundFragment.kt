package jpcoliveira.com.br.testeandroid.fund

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.Toast
import jpcoliveira.com.br.testeandroid.R
import jpcoliveira.com.br.testeandroid.fund.model.Fund

class FundFragment : Fragment(), FundContract.View {

    private lateinit var presenter: FundContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fund, container, false)

        return view
    }

    override fun setPresenter(presenter: FundContract.Presenter) {
        this.presenter = presenter
    }

    override fun onResume() {
        super.onResume()
        presenter.getFund()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_share -> {
                Toast.makeText(activity, activity?.getString(R.string.share), Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showFund(fund: Fund?) {
        Log.i(TAG, "showFund " + fund.toString())
    }

    override fun showMessageError(message: String?) {
        Log.i(TAG, "showMessageError " + message)
    }

    companion object {
        val TAG = FundFragment::class.java.simpleName
    }
}