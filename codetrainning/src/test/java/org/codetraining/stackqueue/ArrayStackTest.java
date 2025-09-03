package org.codetraining.stackqueue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStackTest {

    @Test
    void arrayStackTest(){
        Stack<String> stack = new ArrayStack<>();
        assertTrue(stack.isEmpty());

        stack.push("Hello");
        stack.push("Have");
        stack.push("A");
        stack.push("Good");
        stack.push("Day");

        assertFalse(stack.isEmpty());
        assertEquals("Day",stack.pop());
        assertEquals("Good",stack.pop());
        assertEquals("A",stack.pop());
        assertEquals("Have",stack.pop());
        assertEquals("Hello",stack.pop());
        assertTrue(stack.isEmpty());


    }
}
