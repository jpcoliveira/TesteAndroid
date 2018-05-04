package jpcoliveira.com.br.testeandroid.domain.validate

import jpcoliveira.com.br.testeandroid.util.Constants
import java.util.regex.Pattern

class EmailCellValidate : CellValidate {

    override fun isValid(text: String): Boolean {
        val pattern = Pattern.compile(Constants.PATTERN_EMAIL)
        val matcher = pattern.matcher(text)
        return matcher.matches()
    }

    override fun applyMask(text: String, result: (String?) -> Unit) {
        return
    }
}