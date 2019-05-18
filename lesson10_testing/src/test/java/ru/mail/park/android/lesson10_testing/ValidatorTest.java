package ru.mail.park.android.lesson10_testing;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {
    @Test
    public void validationCorrect() {
        assertTrue(new NameValidatorImpl().isValid("LongName"));
    }

    @Test
    public void magicianShallNotPass() {
        assertFalse(new NameValidatorImpl().isValid("Oz"));
    }
}