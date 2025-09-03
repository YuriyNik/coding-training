package org.codetraining.stackqueue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayQueueTest {
    @Test
    void testQueue(){
        Queue<String> queue = new ArrayQueue<>();
        assertTrue(queue.isEmpty());
        for (int i=0; i<9 ; i++)
        {
            queue.enqueue("Element"+i);
        }
        assertFalse(queue.isEmpty());
        for (int i = 0; i < 9; i++) {
            assertEquals("Element"+i,queue.dequeue());
        }
        assertTrue(queue.isEmpty());
    }
}
