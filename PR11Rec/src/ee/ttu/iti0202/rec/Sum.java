package ee.ttu.iti0202.rec;

public class Sum {
    public static int recSum(int n) {
        if (n > 0) return n == 0 ? 0 : n + recSum(n - 1);
        return n == 0 ? 0 : n + recSum(n + 1);
    }

    public static int recSum(int a, int b) {
        if (a == b) return a;
        if (a > b) return a + recSum(a - 1, b);
        return b + recSum(b - 1, a);
    }
}
