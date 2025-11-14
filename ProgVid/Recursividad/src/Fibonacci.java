public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(recursividad(100));
    }

    public static long recursividad(long n) {
        if (n == 1) return 0;
        if (n == 2) return 1;
        else return recursividad(n - 1) + recursividad(n - 2);
    }
}
