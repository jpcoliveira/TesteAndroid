package jpcoliveira.com.br.testeandroid

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import jpcoliveira.com.br.testeandroid.contact.ContactFragment
import jpcoliveira.com.br.testeandroid.fund.FundFragment

class TabAdapter(fragmentManager: FragmentManager, val context: Context)
    : FragmentPagerAdapter(fragmentManager) {

    val fragments by lazy { listOf(FundFragment(), ContactFragment()) }

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int) =
            when (fragments[position]) {
                is ContactFragment -> context.getString(R.string.contact)
                is FundFragment -> context.getString(R.string.funds)
                else -> ""
            }
}