package jpcoliveira.com.br.testeandroid.domain

import jpcoliveira.com.br.testeandroid.domain.validate.EmailCellValidate
import jpcoliveira.com.br.testeandroid.domain.validate.PhoneCellValidate
import jpcoliveira.com.br.testeandroid.domain.validate.TextCellValidate
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import org.junit.After
import org.junit.Before
import org.junit.Test

class CellValidateTest {

    private var phoneValidate: PhoneCellValidate? = null
    private var emailValidate: EmailCellValidate? = null
    private var textValidate: TextCellValidate? = null

    @Before
    fun setUp() {
        phoneValidate = PhoneCellValidate(INVALID_PHONE)
        emailValidate = EmailCellValidate(INVALID_EMAIL)
        textValidate = TextCellValidate(REQUIRED_FIELD)
    }

    @Test
    fun shouldCheckPhoneNumber_isValid() {
        val validPhone1 = "(11) 1234-1234"
        val validPhone2 = "(11) 91234-1234"

        phoneValidate?.validate(validPhone1)
        assert(phoneValidate?.isValid()!!)
        assertEquals("", phoneValidate?.getMessageError())

        phoneValidate?.validate(validPhone2)
        assert(phoneValidate?.isValid()!!)
        assertEquals("", phoneValidate?.getMessageError())
    }

    @Test
    fun shouldCheckPhoneNumber_isNotValid() {
        val notValidPhone1 = "(11) 123400-1234"
        val notValidPhone2 = "(11) 912341234"

        phoneValidate?.validate(notValidPhone1)
        assertFalse(phoneValidate?.isValid()!!)
        assertEquals(INVALID_PHONE, phoneValidate?.getMessageError())

        phoneValidate?.validate(notValidPhone2)
        assertFalse(phoneValidate?.isValid()!!)
        assertEquals(INVALID_PHONE, phoneValidate?.getMessageError())
    }

    @Test
    fun shouldCheckEmail_isValid() {
        val email = "teste@teste.com"

        emailValidate?.validate(email)
        assert(emailValidate?.isValid()!!)
        assertEquals("", emailValidate?.getMessageError())
    }

    @Test
    fun shouldCheckPhoneEmail_isNotValid() {
        val email = "teste@teste"

        emailValidate?.validate(email)
        assertFalse(emailValidate?.isValid()!!)
        assertEquals(INVALID_EMAIL, emailValidate?.getMessageError())
    }

    @Test
    fun shouldCheckText_isValid() {
        val text = "OLA"

        textValidate?.validate(text)
        assert(textValidate?.isValid()!!)
        assertEquals("", textValidate?.getMessageError())
    }

    @Test
    fun shouldCheckPhoneText_isNotValid() {
        val text = " "

        textValidate?.validate(text)
        assertFalse(textValidate?.isValid()!!)
        assertEquals(REQUIRED_FIELD, textValidate?.getMessageError())
    }

    @After
    fun tearDown() {
        phoneValidate = null
        emailValidate = null
        textValidate = null
    }

    companion object {
        val REQUIRED_FIELD = "campo obrigatório"
        val INVALID_EMAIL = "email inválido"
        val INVALID_PHONE = "número inválido"
    }
}
