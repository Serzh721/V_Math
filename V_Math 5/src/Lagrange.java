public class Lagrange {
    public static double solve(double[] x, double[] y, double arg) {
        double ans = 0, numerator, denominator;
        for (int i = 0; i < x.length; i++) {
            numerator = 1;
            denominator = 1;
            for (int j = 0; j < x.length; j++) {
                if (i != j) {
                    numerator *= (arg - x[j]);
                    denominator *= (x[i] - x[j]);
                }
            }
            ans += (numerator / denominator * y[i]);
        }
        return ans;
    }
}