package jpcoliveira.com.br.testeandroid.domain.validate

import jpcoliveira.com.br.testeandroid.util.Constants
import java.util.regex.Pattern

class PhoneCellValidate : CellValidate {

    private var isUpdating: Boolean = false
    private var oldString: String? = ""
    private var mask = listOf("(##) ####-####", "(##) #####-####")
    private var maskCount = 0

    override fun isValid(text: String): Boolean {
        val pattern = Pattern.compile(Constants.PATTERN_PHONE)
        val matcher = pattern.matcher(text)
        return matcher.matches()
    }

    override fun applyMask(text: String, result: (String?) -> Unit) {

        val textWithoutSpecialCharacter = replaceChars(text)

//        setUpFlags(count, textWithoutSpecialCharacter)

        /* if (count == 0) {
             isUpdating = true
         }
 */
        if (isUpdating) {
            oldString = textWithoutSpecialCharacter
            isUpdating = false
            return
        }

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

    private fun setUpFlags(count: Int?, text: String) {

        if (count == 0) {
            isUpdating = true
        }

        if (isUpdating) {
            oldString = text
            isUpdating = false
        }
    }

    private fun increaseOrDecreaseCounterByText(text: String) {
        if (textSizeIsGreaterThanCurrentMask(text)
                && maskCountIsLessOrEqualsMaskListSize()) {
            maskCount++
        } else if (textSizeIsLessThanCurrentMask(text)) {
            maskCount = 0
        }
    }

    private fun isCharacterMask(m: Char): Boolean {
        return m != '#'
    }

    private fun currentTextSizeIsGreaterThanOldText(text: String): Boolean {
        return text.length > oldString?.length!!
    }

    private fun textSizeIsGreaterThanCurrentMask(text: String): Boolean {
        return (text.length >
                mask[maskCount]
                        .replace("(", "")
                        .replace(")", "")
                        .replace("-", "")
                        .replace(" ", "")
                        .length)
    }

    private fun textSizeIsLessThanCurrentMask(text: String): Boolean {
        return (text.length <
                mask[maskCount]
                        .replace("(", "")
                        .replace(")", "")
                        .replace("-", "")
                        .replace(" ", "")
                        .length)
    }

    private fun maskCountIsLessOrEqualsMaskListSize(): Boolean {
        return maskCount + 1 <= (mask.size - 1)
    }

    private fun replaceChars(text: String): String {

        var result = text
        val pattern = Pattern.compile(Constants.PATTERN_REMOVE_SPECIAL_CHAR)
        val match = pattern.matcher(result)

        while (match.find()) {
            val s = match.group()
            result = result.replace(("\\" + s).toRegex(), "")
        }

        return result
    }
}