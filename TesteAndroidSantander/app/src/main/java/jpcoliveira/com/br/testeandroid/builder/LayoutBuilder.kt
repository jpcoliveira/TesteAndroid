package jpcoliveira.com.br.testeandroid.builder

import android.content.Context
import android.content.res.Resources
import android.support.design.widget.TextInputLayout
import android.view.ContextThemeWrapper
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

            params.marginStart = context?.resources?.getDimension(R.dimen.default_32)?.toInt()!!
            params.marginEnd = context?.resources?.getDimension(R.dimen.default_32)?.toInt()!!

            linearLayout?.layoutParams = params

            return this
        }

        fun buildButton(item: CellsItem): Builder {

            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    context?.resources?.getDimension(R.dimen.default_48)?.toInt()!!)

            val style = R.style.CustomButton

            val button = Button(ContextThemeWrapper(context, style), null, style)

            button.text = item.message
            button.gravity = Gravity.CENTER
            button.isClickable = true

            configureAndAddView(button, item, params)
            return this
        }

        fun buildTextView(item: CellsItem): Builder {

            val style = R.style.Regular16sp

            val textView = TextView(ContextThemeWrapper(context, style), null, style)

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

            val style = R.style.CustomCheckbox

            val checkBox = CheckBox(ContextThemeWrapper(context, style), null, style)

            checkBox.text = item.message
            checkBox.gravity = Gravity.CENTER
            checkBox.isClickable=true

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

        private fun configureAndAddView(view: View?,
                                        item: CellsItem?,
                                        params: LinearLayout.LayoutParams? = null) {

            var layoutParams = params

            if (layoutParams == null) {
                layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT)
            }

            layoutParams.topMargin = item?.topSpacing?.px!!
            layoutParams.gravity = Gravity.CENTER

            view?.id = item.id!!
            view?.layoutParams = layoutParams
            view?.visibility = if (item.hidden!!) View.GONE else View.VISIBLE

            linearLayout?.addView(view)
        }

        fun build(): LinearLayout? {
            checkNotNull(linearLayout, { "linearlayout can not be null" })
            return linearLayout
        }
    }

    val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()
    val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()
}