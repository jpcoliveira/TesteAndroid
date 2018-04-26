package jpcoliveira.com.br.testeandroid.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import jpcoliveira.com.br.testeandroid.R
import jpcoliveira.com.br.testeandroid.fund.model.MoreInfo
import kotlinx.android.synthetic.main.custom_layout_more_info_fund.view.*

class MoreInfoView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_layout_more_info_fund, this)
    }

    fun setInfoTitle(title: String?) {
        info_title?.text = title
    }

    fun setMoreInfo(moreInfo: MoreInfo?) {
        more_info_cdi_month?.text = moreInfo?.month?.cdi?.toString()?.percent()
        more_info_cdi_year?.text = moreInfo?.year?.cdi?.toString()?.percent()
        more_info_cdi_months?.text = moreInfo?.months?.cdi?.toString()?.percent()
        more_info_fund_month?.text = moreInfo?.month?.fund?.toString()?.percent()
        more_info_fund_year?.text = moreInfo?.year?.fund?.toString()?.percent()
        more_info_fund_months?.text = moreInfo?.months?.fund?.toString()?.percent()
    }

    fun String.percent(): String {
        return this.plus("%")
    }
}