public class Calculation {

    double min;
    double max;
    double accuracy;
    int a;
    int n = 4;

    public Calculation(double min, double max, double accuracy, int a) {
        this.min = min;
        this.max = max;
        this.accuracy = accuracy;
        this.a = a;
    }

    public double calculate() {

        double h;
        double In = 0;
        double In2 = 0;
        double answer = 0.0;
        boolean sw = false;

        if (min > max) {
            System.out.println("Нижний предел должен быть меньше верхнего! Произведена замена");
            double q = min;
            min = max;
            max = q;
            sw = true;
        }

        if (min != max) {

            h = (max - min) / n;
            double sum = 0;
            for (int i = 1; i < n; i++) {
                sum += 4 * Point(a, min + i * h);
                ++i;
                sum += 2 * Point(a, min + i * h);
            }
            In = (sum + Point(a, min) - Point(a, max)) * h / 3;

            double r;

            do {
                n = n * 2;
                h = (max - min) / n;
                double sum2 = 0;
                for (int i = 1; i < n; i++) {
                    sum2 += 4 * Point(a, min + i * h);
                    ++i;
                    sum2 += 2 * Point(a, min + i * h);
                }
                In2 = (sum2 + Point(a, min) - Point(a, max)) * h / 3;

                r = Math.abs(In2 - In);

                if (r > accuracy) {
                    In = In2;
                } else
                    answer = In2;
            } while (r > accuracy);

        } else {
            answer = 0;
            System.out.println("Пределы интегрирования равны, результат вычисления будет равен 0 в любом случае");
        }
//        if (sw = true)
//            return answer * (-1);
//        else
            return answer;
    }

            public double Point(int a, double x) {
        double ans = 0;
        switch (a) {
            case 1:
                ans = Math.sqrt(1 + Math.pow(x, 2));
                break;
            case 2:
                ans = Math.pow(x, 2) + 3 * x;
                break;
            case 3:
                ans = 1 / (1 + x);
                break;
        }
        return ans;
    }

    public int getN() {
        return n;
    }
}