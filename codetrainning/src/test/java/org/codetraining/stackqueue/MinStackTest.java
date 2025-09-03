package org.codetraining.stackqueue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinStackTest {

    @Test
    void testPushAndGetMin() {
        MinStack minStack = new MinStack();
        minStack.push(3);
        minStack.push(5);
        assertEquals(3, minStack.getMin());

        minStack.push(2);
        minStack.push(1);
        assertEquals(1, minStack.getMin());

        minStack.pop();
        assertEquals(2, minStack.getMin());

        minStack.pop();
        assertEquals(3, minStack.getMin());
    }

    @Test
    void testTop() {
        MinStack minStack = new MinStack();
        minStack.push(10);
        minStack.push(20);
        assertEquals(20, minStack.top());

        minStack.pop();
        assertEquals(10, minStack.top());
    }

    @Test
    void testEmptyStackExceptions() {
        MinStack minStack = new MinStack();
        assertThrows(IllegalStateException.class, minStack::top);
        assertThrows(IllegalStateException.class, minStack::getMin);
        assertThrows(IllegalStateException.class, minStack::pop);
    }
}

