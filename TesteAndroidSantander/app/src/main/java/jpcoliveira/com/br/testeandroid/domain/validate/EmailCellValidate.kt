package jpcoliveira.com.br.testeandroid.domain.validate

import jpcoliveira.com.br.testeandroid.util.Constants
import java.util.regex.Pattern

class EmailCellValidate(messageValidationError: String?) : CellValidate(messageValidationError) {

    private var isValid: Boolean = false

    override fun validate(text: String) {
        val pattern = Pattern.compile(Constants.PATTERN_EMAIL)
        val matcher = pattern.matcher(text)
        isValid = matcher.matches()
    }

    override fun isValid(): Boolean {
        return isValid
    }

    override fun getMessageError(): String? {
        if (!isValid)
            return super.getMessageError()

        return ""
    }

    override fun applyMask(text: String, result: (String?) -> Unit) {
        return
    }

}