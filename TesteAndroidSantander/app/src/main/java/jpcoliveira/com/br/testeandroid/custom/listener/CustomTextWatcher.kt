package jpcoliveira.com.br.testeandroid.custom.listener

import android.text.Editable
import android.text.TextWatcher
import jpcoliveira.com.br.testeandroid.domain.validate.CellValidate

class CustomTextWatcher(val cellValidate: CellValidate?,
                        val mask: (String?) -> Unit,
                        val isValid: (Boolean?, String?) -> Unit) : TextWatcher {

    override fun afterTextChanged(editable: Editable) {

    }

    override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, count: Int) {

        cellValidate?.validate(text.toString())

        isValid(cellValidate?.isValid(), cellValidate?.getMessageError())
        cellValidate?.applyMask(
                text.toString(),
                { formatted -> mask(formatted) }
        )
    }
}

