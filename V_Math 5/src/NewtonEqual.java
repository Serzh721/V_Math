public class NewtonEqual {
    double[] x;
    double[] y;
    double arg;
    int size;
    double t;
    static int k;
    double res;
    int razn = 1;

    public NewtonEqual(double[] x, double[] y, double arg) {
        this.x = x;
        this.y = y;
        this.arg = arg;
        this.size = x.length;
    }

    public double solve() {
        if (arg < x[0]) {
            t = (arg - x[0]) / (x[1] - x[0]);
            backward(t);
        } else if (arg > x[size - 1]) {
            t = (arg - x[size - 1]) / (x[1] - x[0]);
            forward(t);
        } else if (arg <= x[size / 2]) {
            while (arg > x[k])
                k++;
            k--;
            t = (arg - x[k]) / (x[1] - x[0]);
            forward(t);
        } else {
            t = (arg - x[size - 1]) / (x[1] - x[0]);
            backward(t);
        }
        return res;
    }

    private void forward(double t) {
        double res = y[k] + t * delta(1);
        double numerator = t;
        int iter = k;
        for (int i = 1; iter < size - 2; i++) {
            numerator *= (t - i);
            res += numerator / fact(i + 1) * delta(i + 1);
            iter++;
            razn++;
        }
        System.out.println("Порядков разностей подсчитано:" + razn);
        this.res = res;
    }

    private void backward(double t) {
        double res = y[size - 1] + t * delta(1, size - 2);
        double numerator = t;
        for (int i = 1; i < size - 1; i++) {
            numerator *= (t + i);
            res += numerator / fact(i + 1) * delta(i + 1, size - i - 2);
            razn++;
        }
        System.out.println("Порядков разностей подсчитано:" + razn);
        this.res = res;
    }

    private double delta(int q) {
        return calcCurrentDelta(q)[k];
    }

    private double delta(int q, int p) {
        return calcCurrentDelta(q)[p];
    }

    private double[] calcCurrentDelta(int q) {
        double[] res = new double[size];
        if (q == 1) {
            res = new double[size];
            for (int i = 0; i < size - 1; i++) {
                res[i] = y[1 + i] - y[i];
            }
        } else {
            for (int i = 0; i < size - 1; i++) {
                double a = calcCurrentDelta(q - 1)[i + 1];
                double b = calcCurrentDelta(q - 1)[i];
                res[i] = a - b;
            }
        }
        return res;
    }

    int fact(int n) {
        if (n <= 1)
            return 1;
        else
            return n * fact(n - 1);
    }
}