public class Calculation {
    double a;
    double b;
    double c;
    double S;
    double sigma;
    double SX, SXX, SXXX, SXXXX, SXY, SXXY, SY;
    double[] lin;
    double[] pow;
    double[] exp;
    double[] log;
    double[] quad;

    public void calculate(double[] x, double[] y, int n) {
        SX = 0;
        SXX = 0;
        SXXX = 0;
        SXXXX = 0;
        SXY = 0;
        SXXY = 0;
        SY = 0;
        for (int i = 0; i < n; i++) {
            SX += x[i];
            SXX += Math.pow(x[i], 2);
            SXXX += Math.pow(x[i], 3);
            SXXXX += Math.pow(x[i], 4);
            SXXY += Math.pow(x[i], 2) * y[i];
            SXY += x[i] * y[i];
            SY += y[i];
        }
    }

    public void Linear(double[] x, double[] y, int n) {
        lin = new double[n];
        calculate(x, y, n);
        a = (SXY * n - SX * SY) / (SXX * n - SX * SX);
        b = (SXX * SY - SX * SXY) / (SXX * n - SX * SX);
        S = 0;
        for (int i = 0; i < n; i++) {
            S += Math.pow((a * x[i] + b - y[i]), 2);
            lin[i] = a * x[i] + b;
        }
        sigma = Math.sqrt(S / n);
    }

    public void pow(double[] x, double[] y, int n) {
        pow = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = Math.log(x[i]);
            y[i] = Math.log(y[i]);
        }
        calculate(x, y, n);
        double A = (SXY * n - SX * SY) / (SXX * n - SX * SX);
        double B = (SXX * SY - SX * SXY) / (SXX * n - SX * SX);
        a = Math.exp(B);
        b = A;
        S = 0;
        for (int i = 0; i < n; i++) {
            x[i] = Math.pow(Math.E, x[i]);
            y[i] = Math.pow(Math.E, y[i]);
            S += Math.pow((a * Math.pow(x[i], b) - y[i]), 2);
            pow[i] = a * Math.pow(x[i], b);
        }
        sigma = Math.sqrt(S / n);
    }

    public void exp(double[] x, double[] y, int n) {
        exp = new double[n];
        for (int i = 0; i < n; i++) {
            y[i] = Math.log(y[i]);
        }
        calculate(x, y, n);
        double A = (SXY * n - SX * SY) / (SXX * n - SX * SX);
        double B = (SXX * SY - SX * SXY) / (SXX * n - SX * SX);
        a = Math.exp(B);
        b = A;
        S = 0;
        for (int i = 0; i < n; i++) {
            y[i] = Math.exp(y[i]);
            S += Math.pow((a * Math.exp(b * x[i]) - y[i]), 2);
            exp[i] = a * Math.exp(b * x[i]);
        }
        sigma = Math.sqrt(S / n);
    }

    public void log(double[] x, double[] y, int n) {
        log = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = Math.log(x[i]);
        }
        calculate(x, y, n);
        a = (SXY * n - SX * SY) / (SXX * n - SX * SX);
        b = (SXX * SY - SX * SXY) / (SXX * n - SX * SX);
        S = 0;
        for (int i = 0; i < n; i++) {
            x[i] = Math.exp(x[i]);
            S += Math.pow((a * Math.log(x[i]) + b - y[i]), 2);
            log[i]= a * Math.log(x[i]) + b;
        }
        sigma = Math.sqrt(S / n);
    }

    public void quadratic(double[] x, double[] y, int n) {
        quad = new double[n];
        calculate(x, y, n);
        double[][] matrix = {
                {n, SX, SXX, SY},
                {SX, SXX, SXXX, SXY},
                {SXX, SXXX, SXXXX, SXXY},
        };
        double[][] m1 = {
                {matrix[0][0], matrix[0][1], matrix[0][2]},
                {matrix[1][0], matrix[1][1], matrix[1][2]},
                {matrix[2][0], matrix[2][1], matrix[2][2]},
        };
        double[][] m2 = {
                {matrix[0][3], matrix[0][1], matrix[0][2]},
                {matrix[1][3], matrix[1][1], matrix[1][2]},
                {matrix[2][3], matrix[2][1], matrix[2][2]},
        };
        double[][] m3 = {
                {matrix[0][0], matrix[0][3], matrix[0][2]},
                {matrix[1][0], matrix[1][3], matrix[1][2]},
                {matrix[2][0], matrix[2][3], matrix[2][2]},
        };
        double[][] m4 = {
                {matrix[0][0], matrix[0][1], matrix[0][3]},
                {matrix[1][0], matrix[1][1], matrix[1][3]},
                {matrix[2][0], matrix[2][1], matrix[2][3]},
        };

        double D = det(m1);
        double D1 = det(m2);
        double D2 = det(m3);
        double D3 = det(m4);

        if (D != 0) {
            a = D3 / D;
            b = D2 / D;
            c = D1 / D;
        } else {
            if (D1 == 0 && D2 == 0 && D3 == 0)
                System.out.println("Бесконечное кол-во решений!");
            else if (D1 != 0 || D2 != 0 || D3 != 0)
                System.out.println("Нет решений!");
        }
        S = 0;
        for (int i = 0; i < n; i++) {
            S += Math.pow((a * x[i] * x[i] + b * x[i] + c - y[i]), 2);
            quad[i] = a * x[i] * x[i] + b * x[i] + c;
        }
        sigma = Math.sqrt(S / n);
    }

    public double det(double[][] matrix) {
        double ans = 0;
        ans = matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[2][1] * matrix[1][2])
                - matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0])
                + matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
        return ans;
    }
}