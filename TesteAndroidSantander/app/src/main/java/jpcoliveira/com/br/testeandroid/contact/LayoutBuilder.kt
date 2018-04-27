package jpcoliveira.com.br.testeandroid.contact

import android.content.Context
import android.os.Build
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import jpcoliveira.com.br.testeandroid.contact.model.CellsItem

class LayoutBuilder(val context: Context?) {

    private var linearLayout: LinearLayout? = null

    inner class Builder {

        fun buildContainer(params: LinearLayout.LayoutParams): Builder? {
            linearLayout = LinearLayout(context)
            linearLayout?.layoutParams = params
            return this
        }

        fun buildButton(item: CellsItem): Builder {

            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
            val button = Button(context)
            button.id = item.id!!
            button.text = item.message
            button.visibility = if (item.hidden!!) View.GONE else View.VISIBLE
            params.topMargin = item.topSpacing!!
            button.layoutParams = params
            linearLayout?.addView(button)
            return this
        }

        fun buildTextView(item: CellsItem): Builder {
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
            val textView = TextView(context)
            textView.id = item.id!!
            textView.setText(item.message)
            textView.visibility = if (item.hidden!!) View.GONE else View.VISIBLE
            params.topMargin = item.topSpacing!!
            textView.layoutParams = params
            linearLayout?.addView(textView)
            return this
        }

        fun buildEditText(item: CellsItem): Builder {
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
            val textInputLayout = TextInputLayout(context)
            val editText = EditText(context)
            editText.id = item.id!!
            editText.hint = item.message
            textInputLayout.addView(editText)
            textInputLayout.visibility = if (item.hidden!!) View.GONE else View.VISIBLE
            params.topMargin = item.topSpacing!!
            textInputLayout.layoutParams = params
            linearLayout?.addView(textInputLayout)
            return this
        }

        fun buildCheckbox(cell: CellsItem): Builder {
            return this
        }

        fun buildImage(cell: CellsItem): Builder {
            return this
        }
    }

    fun build(): LinearLayout? {
        if (linearLayout == null) throw NullPointerException("can not build without buildContainer")
        return linearLayout
    }
}