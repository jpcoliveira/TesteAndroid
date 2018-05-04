package jpcoliveira.com.br.testeandroid.domain.validate

class TextCellValidate : CellValidate {

    override fun isValid(text: String): Boolean {
        return !text.isNullOrBlank()
    }

    override fun applyMask(text: String, result: (String?) -> Unit) {
        return
    }
}