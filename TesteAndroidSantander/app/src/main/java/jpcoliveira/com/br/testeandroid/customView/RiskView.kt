package jpcoliveira.com.br.testeandroid.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import jpcoliveira.com.br.testeandroid.R
import kotlinx.android.synthetic.main.custom_layout_investment_risk.view.*

class RiskView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_layout_investment_risk, this)
    }

    fun selectRisk(valueRisk: Int) {
        when (valueRisk) {
            1 -> updateRisk(veryLowRisk, imageVeryLowRisk)
            2 -> updateRisk(lowRisk, imageLowRisk)
            3 -> updateRisk(moderateRisk, imageModerateRisk)
            4 -> updateRisk(highRisk, imageHighRisk)
            5 -> updateRisk(veryHighRisk, imageVeryHighRisk)
        }
    }

    fun updateRisk(risk: View, image: ImageView) {
        val params = risk.layoutParams
        params.height = context.resources.getDimension(R.dimen.risk_selected).toInt()
        risk.layoutParams = params
        image.visibility = View.VISIBLE
    }
}


