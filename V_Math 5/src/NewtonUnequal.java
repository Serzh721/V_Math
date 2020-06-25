public class NewtonUnequal {

    static double[] xTrio;
    static double[] yTrio;

    static double solve(double[] x, double[] y, double arg) {
        int size = x.length;
        double ans = 0;

        if (arg > x[size - 2]) {
            for (int j = 1; j >= 0; j--) {
                xTrio = new double[]{x[size - 3 - j], x[size - 2 - j], x[size - 1 - j]};
                yTrio = new double[]{y[size - 3 - j], y[size - 2 - j], y[size - 1 - j]};
                ans += yTrio[0] + f1(0, 1) * (arg - xTrio[0]) + f2(0, 1, 2) * (arg - xTrio[0]) * (arg - xTrio[1]);
            }
        } else if (arg < x[1]) {
            for (int j = 0; j <= 1; j++) {
                xTrio = new double[]{x[j], x[j + 1], x[j + 2]};
                yTrio = new double[]{y[j], y[j + 1], y[j + 2]};
                ans += yTrio[0] + f1(0, 1) * (arg - xTrio[0]) + f2(0, 1, 2) * (arg - xTrio[0]) * (arg - xTrio[1]);
            }
        } else {
            int i = 1;
            while (arg > x[i] && (size - 1 - i) >= 2) {
                ans = 0;
                for (int j = 0; j <= 1; j++) {
                    xTrio = new double[]{x[i + j - 1], x[i + j], x[i + j + 1]};
                    yTrio = new double[]{y[i + j - 1], y[i + j], y[i + j + 1]};
                    ans += yTrio[0] + f1(0, 1) * (arg - xTrio[0]) + f2(0, 1, 2) * (arg - xTrio[0]) * (arg - xTrio[1]);
                }
                i++;
            }
        }
        return ans / 2;
    }

    static double f1(int a, int b) {
        return (yTrio[b] - yTrio[a]) / (xTrio[b] - xTrio[a]);
    }

    static double f2(int a, int b, int c) {
        return (f1(b, c) - f1(a, b)) / (xTrio[c] - xTrio[a]);
    }
}