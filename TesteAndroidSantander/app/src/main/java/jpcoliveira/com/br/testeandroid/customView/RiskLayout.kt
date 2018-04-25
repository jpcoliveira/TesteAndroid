package jpcoliveira.com.br.testeandroid.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import jpcoliveira.com.br.testeandroid.R
import kotlinx.android.synthetic.main.custom_layout_investment_risk.view.*

class RiskLayout(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_layout_investment_risk, this)
    }

    fun selectRisk(valueRisk: Int) {

        when (valueRisk) {
            0 -> updateRisk(veryLowRisk, imageVeryLowRisk)
            1 -> updateRisk(lowRisk, imageLowRisk)
            2 -> updateRisk(moderateRisk, imageModerateRisk)
            3 -> updateRisk(highRisk, imageHighRisk)
            4 -> updateRisk(veryHighRisk, imageVeryHighRisk)
        }
    }

    fun updateRisk(risk: View, image: ImageView) {
        val params = risk.layoutParams
        params.height = context.resources.getDimension(R.dimen.risk_selected) as Int
        risk.layoutParams = params
        image.visibility = View.VISIBLE
    }
}


