package jpcoliveira.com.br.testeandroid

import jpcoliveira.com.br.testeandroid.domain.validate.EmailCellValidate
import jpcoliveira.com.br.testeandroid.domain.validate.PhoneCellValidate
import jpcoliveira.com.br.testeandroid.domain.validate.TextCellValidate
import junit.framework.Assert.assertFalse
import org.junit.Test

class CellValidateTest {
    @Test
    fun shouldCheckPhoneNumber_isValid() {
        val validPhone1 = "(11) 1234-1234"
        val validPhone2 = "(11) 91234-1234"
        assert(PhoneCellValidate().isValid(validPhone1))
        assert(PhoneCellValidate().isValid(validPhone2))
    }

    @Test
    fun shouldCheckPhoneNumber_isNotValid() {
        val notValidPhone1 = "(11) 123400-1234"
        val notValidPhone2 = "(11) 912341234"
        assertFalse(PhoneCellValidate().isValid(notValidPhone1))
        assertFalse(PhoneCellValidate().isValid(notValidPhone2))
    }

    @Test
    fun shouldCheckEmail_isValid() {
        val email = "teste@teste.com"
        assert(EmailCellValidate().isValid(email))
    }

    @Test
    fun shouldCheckPhoneEmail_isNotValid() {
        val email = "teste@teste"
        assertFalse(EmailCellValidate().isValid(email))
    }

    @Test
    fun shouldCheckText_isValid() {
        val text = "OLA"
        assert(TextCellValidate().isValid(text))
    }

    @Test
    fun shouldCheckPhoneText_isNotValid() {
        val text = " "
        assertFalse(TextCellValidate().isValid(text))
    }
}
