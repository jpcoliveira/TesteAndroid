package jpcoliveira.com.br.testeandroid.util

class Constants {
    companion object {
        val PATTERN_EMAIL = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
        val PATTERN_PHONE = "^(\\([0-9]{2}\\))\\s([9]{1})?([0-9]{4})-([0-9]{4})|(\\([0-9]{2}\\))\\s([9]{1})?([0-9]{5})-([0-9]{4})\$"
        val PATTERN_REMOVE_SPECIAL_CHAR = "[^a-zA-Z0-9]"
        val PATTERN_REMOVE_PARENTHESIS = "[\\s\\-()]"
    }
}