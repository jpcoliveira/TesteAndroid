package jpcoliveira.com.br.testeandroid.domain.validate

interface CellValidate {
    fun isValid(text: String): Boolean

    fun applyMask(text: String, result: (String?) -> Unit)
}