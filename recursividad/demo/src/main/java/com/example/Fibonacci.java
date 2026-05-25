package com.example;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fibonacci(5));
    }

    public static int fibonacci(int limite) {
        if (limite == 0)
            return 0;

        if (limite == 1)
            return 1;

        return fibonacci(limite - 1) + fibonacci(limite - 2);
    }
    
}
