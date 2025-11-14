public class Factorial {
    public static void main(String[] args) {
        System.out.println(recursividad(4));
    }

    public static int recursividad(int n) {
        if (n == 0 || n == 1) return 1;
        else return n + recursividad(n - 1);
    }
}
