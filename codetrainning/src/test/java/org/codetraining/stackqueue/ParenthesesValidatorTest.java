package org.codetraining.stackqueue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParenthesesValidatorTest {

    @Test
    void testValidCases() {
        ParenthesesValidator validator = new ParenthesesValidator();
        assertTrue(validator.isValid("()"));
        assertTrue(validator.isValid("({[]})"));
        assertTrue(validator.isValid(""));
    }

    @Test
    void testInvalidCases() {
        ParenthesesValidator validator = new ParenthesesValidator();
        assertFalse(validator.isValid("("));
        assertFalse(validator.isValid("([)]"));
        assertFalse(validator.isValid("({[})]"));
    }

    @Test
    void testNullInput() {
        ParenthesesValidator validator = new ParenthesesValidator();
        assertFalse(validator.isValid(null));
    }

}
