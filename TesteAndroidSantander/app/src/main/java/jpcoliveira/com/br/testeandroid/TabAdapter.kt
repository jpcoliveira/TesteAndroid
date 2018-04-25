package jpcoliveira.com.br.testeandroid

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.newInstance
import jpcoliveira.com.br.testeandroid.contact.ContactFragment
import jpcoliveira.com.br.testeandroid.contact.ContactPresenter
import jpcoliveira.com.br.testeandroid.fund.FundFragment
import jpcoliveira.com.br.testeandroid.fund.FundPresenter

class TabAdapter(fragmentManager: FragmentManager, val context: Context, val kodein: Kodein)
    : FragmentPagerAdapter(fragmentManager) {

    val fragments by lazy { listOf(FundFragment(), ContactFragment()) }

    override fun getItem(position: Int): Fragment {
        val fragment = fragments[position] as Fragment
        injectPresenter(fragment)
        return fragment
    }

    private fun injectPresenter(fragment: Fragment?) {

        when (fragment) {
            is FundFragment -> {
                kodein.newInstance { FundPresenter(instance(), fragment) }
            }
            is ContactFragment -> {
                kodein.newInstance { ContactPresenter(instance(), fragment) }
            }
        }
    }

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int) =
            when (fragments[position]) {
                is ContactFragment -> context.getString(R.string.contact)
                is FundFragment -> context.getString(R.string.investiment)
                else -> ""
            }
}