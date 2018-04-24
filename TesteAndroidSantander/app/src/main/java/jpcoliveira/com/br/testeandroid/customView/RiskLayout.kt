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

    fun setRiskInfo(valueRisk: Int) {

        when (valueRisk) {
            0 -> setRiskIndicator(risk1, imageRisk1)
            1 -> setRiskIndicator(risk2, imageRisk2)
            2 -> setRiskIndicator(risk3, imageRisk3)
            3 -> setRiskIndicator(risk4, imageRisk4)
            4 -> setRiskIndicator(risk5, imageRisk5)
        }
    }

    fun setRiskIndicator(risk: View, image: ImageView) {
        val params = risk.layoutParams
        params.height = 40
        risk.layoutParams = params
        image.visibility = View.VISIBLE
    }
}


