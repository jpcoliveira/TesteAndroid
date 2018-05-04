package jpcoliveira.com.br.testeandroid.domain

import jpcoliveira.com.br.testeandroid.domain.validate.PhoneCellValidate
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotSame
import org.junit.Test

class CellMaskTest {
    @Test
    fun shouldCheckMaskPhoneNumber8Digits_isOk() {

        val text = "1199999999"
        val textExpected = "(11) 9999-9999"

        PhoneCellValidate(INVALID_PHONE).applyMask(text, result = {
            assertEquals(textExpected, it)
        })
    }

    @Test
    fun shouldCheckMaskPhoneNumber9Digits_isOk() {

        val text = "11999999999"
        val textExpected = "(11) 99999-9999"

        PhoneCellValidate(INVALID_PHONE).applyMask(text, result = {
            assertEquals(textExpected, it)
        })
    }

    @Test
    fun shouldCheckMaskPhoneNumber8Digits_isNotOk() {

        val text = "119999999"
        val textExpected = "(11) 9999-9999"

        PhoneCellValidate(INVALID_PHONE).applyMask(text, result = {
            assertNotSame(textExpected, it)
        })
    }

    @Test
    fun shouldCheckMaskPhoneNumber9Digits_isNotOk() {

        val text = "119999999"
        val textExpected = "(11) 99999-9999"

        PhoneCellValidate(INVALID_PHONE).applyMask(text, result = {
            assertNotSame(textExpected, it)
        })
    }

    companion object {
        val INVALID_PHONE = "número inválido"
    }
}