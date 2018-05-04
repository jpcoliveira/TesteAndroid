package jpcoliveira.com.br.testeandroid.domain.validate

class TextCellValidate(messageValidationError: String?) : CellValidate(messageValidationError) {

    private var isValid: Boolean = false

    override fun validate(text: String) {
        isValid = !text.isNullOrBlank()
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