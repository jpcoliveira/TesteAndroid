package jpcoliveira.com.br.testeandroid.domain.validate

abstract class CellValidate(private val messageValidationError: String?) {
    abstract fun validate(text: String)
    abstract fun isValid(): Boolean
    abstract fun applyMask(text: String, result: (String?) -> Unit)
    open fun getMessageError(): String? {
        return messageValidationError
    }

}