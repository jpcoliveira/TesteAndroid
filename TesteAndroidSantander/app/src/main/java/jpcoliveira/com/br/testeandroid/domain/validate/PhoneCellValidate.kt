package jpcoliveira.com.br.testeandroid.domain.validate

import jpcoliveira.com.br.testeandroid.util.Constants
import java.util.regex.Pattern

class PhoneCellValidate(messageValidationError: String?) : CellValidate(messageValidationError) {

    private var isUpdating: Boolean = false
    private var oldString: String? = ""
    private var mask = listOf("(##) ####-####", "(##) #####-####")
    private var maskCount = 0
    private var isValid: Boolean = false

    override fun validate(text: String) {
        val pattern = Pattern.compile(Constants.PATTERN_PHONE)
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

        val textWithoutSpecialCharacter = replaceChars(text, Constants.PATTERN_REMOVE_SPECIAL_CHAR)

        if (shouldBreak(textWithoutSpecialCharacter)) return

        increaseOrDecreaseCounterByText(text)

        result(processMask(textWithoutSpecialCharacter))
    }

    private fun processMask(text: String): String? {

        var i = 0
        var textWithMask = ""

        for (m: Char in mask[maskCount].toCharArray()) {

            if (isCharacterMask(m)
                    && currentTextSizeIsGreaterThanOldText(text)) {
                textWithMask += m
                continue
            }

            try {
                textWithMask += text[i]
            } catch (e: Exception) {
                break
            }
            i++
        }

        isUpdating = true

        return textWithMask
    }

    private fun shouldBreak(text: String): Boolean {

        if (isUpdating) {
            oldString = text
            isUpdating = false
            return true
        }

        return false
    }

    private fun increaseOrDecreaseCounterByText(text: String) {
        if (textSizeIsLessThanCurrentMask(text)) {
            maskCount = 0
        } else if (textSizeIsGreaterThanCurrentMask(text)
                && maskCountIsLessOrEqualsMaskListSize()) {
            maskCount++
        }
    }

    private fun isCharacterMask(m: Char): Boolean {
        return m != '#'
    }

    private fun currentTextSizeIsGreaterThanOldText(text: String): Boolean {
        return text.length > oldString?.length!!
    }

    private fun textSizeIsGreaterThanCurrentMask(text: String): Boolean {
        return (text.length > replaceChars(mask[maskCount], Constants.PATTERN_REMOVE_PARENTHESIS).length)
    }

    private fun textSizeIsLessThanCurrentMask(text: String): Boolean {
        return replaceChars(text, Constants.PATTERN_REMOVE_PARENTHESIS)
                .length <= replaceChars(mask[maskCount], Constants.PATTERN_REMOVE_PARENTHESIS).length
    }

    private fun maskCountIsLessOrEqualsMaskListSize(): Boolean {
        return maskCount < (mask.size - 1)
    }

    private fun replaceChars(text: String, regex: String): String {

        var result = text
        val pattern = Pattern.compile(regex)
        val match = pattern.matcher(result)

        while (match.find()) {
            val s = match.group()
            result = result.replace(("\\" + s).toRegex(), "")
        }

        return result
    }
}