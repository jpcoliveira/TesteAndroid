package jpcoliveira.com.br.testeandroid.builder

import android.content.Context
import android.content.res.Resources
import android.support.design.widget.TextInputLayout
import android.text.InputType
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.View
import android.widget.*
import jpcoliveira.com.br.testeandroid.R
import jpcoliveira.com.br.testeandroid.contact.ContactContract
import jpcoliveira.com.br.testeandroid.contact.model.CellsItem

class LayoutBuilder(val context: Context?) {

    private var linearLayout: LinearLayout? = null

    inner class Builder(val presenter: ContactContract.Presenter?) {

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

        fun buildButton(item: CellsItem, items: List<CellsItem>): Builder {

            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    context?.resources?.getDimension(R.dimen.default_48)?.toInt()!!)

            val style = R.style.CustomButton

            val button = Button(ContextThemeWrapper(context, style), null, style)

            button.text = item.message
            button.gravity = Gravity.CENTER
            button.isClickable = true

            button.setOnClickListener({
                presenter?.clickSendMessage(items)
            })

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
            editText.inputType = getInputType(item.typefield)
            textInputLayout.addView(editText)

            editText.setOnFocusChangeListener({ view, b ->
                textInputLayout.isErrorEnabled = false
            })

            configureAndAddView(textInputLayout, item)
            return this
        }

        fun buildCheckbox(item: CellsItem): Builder {

            val style = R.style.CustomCheckbox
            val checkBox = CheckBox(ContextThemeWrapper(context, style), null, style)

            checkBox.text = item.message
            checkBox.gravity = Gravity.CENTER
            checkBox.isClickable = true

            checkBox.setOnCheckedChangeListener({ compoundButton, isChecked ->
                linearLayout
                        ?.findViewById<View>(item.show?.toInt()!!)
                        ?.visibility = if (isChecked) View.VISIBLE else View.GONE
            })

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

        private fun getInputType(type: String?) =
                when (type) {
                    TypeField.text.id -> InputType.TYPE_CLASS_TEXT
                    TypeField.email.id -> InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                    TypeField.telnumber.name -> InputType.TYPE_CLASS_PHONE
                    else -> InputType.TYPE_CLASS_TEXT
                }


        fun build(): LinearLayout? {
            checkNotNull(linearLayout, { "linearlayout can not be null" })
            return linearLayout
        }
    }

    val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()
    val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()
}