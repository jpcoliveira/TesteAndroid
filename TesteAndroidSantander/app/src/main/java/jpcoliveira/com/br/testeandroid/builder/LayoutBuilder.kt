package jpcoliveira.com.br.testeandroid.builder

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.view.Gravity
import android.view.View
import android.widget.*
import jpcoliveira.com.br.testeandroid.R
import jpcoliveira.com.br.testeandroid.contact.model.CellsItem

class LayoutBuilder(val context: Context?) {

    private var linearLayout: LinearLayout? = null

    inner class Builder {

        fun buildContainer(): Builder? {

            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)

            linearLayout = LinearLayout(context)
            linearLayout?.orientation = LinearLayout.VERTICAL
            linearLayout?.layoutParams = params

            return this
        }

        fun buildButton(item: CellsItem): Builder {

            val button = Button(context)

            button.text = item.message
            button.gravity = Gravity.CENTER

            configureAndAddView(button, item)
            return this
        }

        fun buildTextView(item: CellsItem): Builder {

            val textView = TextView(context)

            textView.text = item.message
            textView.gravity = Gravity.CENTER

            configureAndAddView(textView, item)
            return this
        }

        fun buildEditText(item: CellsItem): Builder {

            val textInputLayout = TextInputLayout(context)
            val editText = EditText(context)

            editText.hint = item.message
            textInputLayout.addView(editText)

            configureAndAddView(textInputLayout, item)
            return this
        }

        fun buildCheckbox(item: CellsItem): Builder {
            val checkBox = CheckBox(context)

            checkBox.text = item.message
            checkBox.gravity = Gravity.CENTER

            configureAndAddView(checkBox, item)
            return this
        }

        fun buildImage(item: CellsItem): Builder {
            val image = ImageView(context)

            image.setImageResource(R.drawable.ic_download)
            image.contentDescription = item.message

            configureAndAddView(image, item)
            return this
        }

        private fun configureAndAddView(view: View?, item: CellsItem?) {

            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)

            params.topMargin = item?.topSpacing!!
            params.gravity = Gravity.CENTER
            view?.id = item.id!!
            view?.layoutParams = params
            view?.visibility = if (item.hidden!!) View.GONE else View.VISIBLE

            linearLayout?.addView(view)
        }

        fun build(): LinearLayout? {
            if (linearLayout == null) throw NullPointerException("can not build without buildContainer")
            return linearLayout
        }
    }
}