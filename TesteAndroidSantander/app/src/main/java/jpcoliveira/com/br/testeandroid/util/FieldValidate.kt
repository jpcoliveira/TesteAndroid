package jpcoliveira.com.br.testeandroid.util

import java.util.regex.Pattern


class FieldValidate {

    companion object {

        fun isValidEmail(email: String?): Boolean {
            val pattern = Pattern.compile(Constants.PATTERN_EMAIL)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }

        fun isValidPhone(phone: String?): Boolean {
            val pattern = Pattern.compile(Constants.PATTERN_PHONE)
            val matcher = pattern.matcher(phone)
            return matcher.matches()
        }
    }
}