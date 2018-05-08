package jpcoliveira.com.br.testeandroid.exception

open class InvalidValidationException(override val message: String?, val resId: Int?) : IllegalArgumentException(message)