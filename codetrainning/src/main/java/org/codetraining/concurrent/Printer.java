package org.codetraining.concurrent;

class Printer {
    private int number = 1;
    private final int MAX = 10;
    private boolean turn = true; // чей ход

    public static void main(String[] args) {
        Printer p = new Printer();
        new Thread(p::printOdd).start();
        new Thread(p::printEven).start();
    }


    public synchronized void printOdd() {
        while (number <= MAX) {
            if (!turn) {
                try { wait(); } catch (InterruptedException ignored) {

                }
            } else {
                System.out.println("Поток-1: " + number++);
                turn = false;
                notify();
            }
        }
    }

    public synchronized void printEven() {
        while (number <= MAX) {
            if (turn) {
                try { wait(); } catch (InterruptedException ignored) {

                }
            } else {
                System.out.println("Поток-2: " + number++);
                turn = true;
                notify();
            }
        }
    }
}





