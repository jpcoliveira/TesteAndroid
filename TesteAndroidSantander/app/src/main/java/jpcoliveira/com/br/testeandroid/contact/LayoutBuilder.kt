package jpcoliveira.com.br.testeandroid.contact

import android.content.Context
import android.widget.Button
import android.widget.LinearLayout

class LayoutBuilder(val context: Context) {

    var linearLayout: LinearLayout? = null

    inner class Builder {

        fun buildContainer(params: LinearLayout.LayoutParams): Builder? {
            val linearLayout = LinearLayout(context)
            linearLayout.layoutParams = params
            return this
        }

        fun buildField(): Builder {
            val button = Button(context)
            linearLayout?.addView(button)
            return this
        }
    }

    fun build(): LinearLayout? {
        if (linearLayout == null) throw NullPointerException("can not build without buildContainer")
        return linearLayout
    }

}