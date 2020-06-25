import java.io.*;
import java.util.Scanner;

public class Calculation {

    double a = 0;
    double b = 0;
    double E = 0;
    double x = 0;
    int n = 0;
    Graph graph = new Graph();

    public double f(double x) {
        return -1.8 * Math.pow(x, 3) - 2.94 * Math.pow(x, 2) + 10.37 * x + 5.38;
    }

    public double df(double x) {
        return -5.4 * Math.pow(x, 2) - 5.88 * x + 10.37;
    }

    public double ddf(double x) {
        return -10.8 * x - 5.88;
    }

    public double dfi(double x, double L) {
        return -5.4 * L * Math.pow(x, 2) - 5.88 * L * x + 10.37 * L + 1;
    }

    public void in() {
        Scanner in = new Scanner(System.in);
        int q = 0;
        try {
            do {
                System.out.println("\nКаким образом вы хотите ввести данные?\n  1 - Из файла \n  2 - С клавиатуры");
                q = in.nextInt();
            } while ((q < 1) | (q > 2));
        } catch (Exception e) {
            System.out.println("Некорректный ввод!");
            in();
        }

        switch (q) {
            case 1:
                System.out.println("Данные в файле должны быть разделены пробелом и представлены в последовательности a b E");
                System.out.print("Введите имя файла:  ");
                Scanner inn = new Scanner(System.in);
                String filename = inn.nextLine();
                try {
                    FileInputStream fstream = new FileInputStream(filename);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        String[] numbers = line.split(" ");
                        a = Double.parseDouble(numbers[0]);
                        b = Double.parseDouble(numbers[1]);
                        E = Double.parseDouble(numbers[2]);
                    }
                } catch (FileNotFoundException e1) {
                    System.out.println("Файл не найден!");
                    System.exit(0);
                } catch (Exception e) {
                    System.out.println("Возникли проблемы с открытием файла!");
                    System.exit(0);
                }
                graph.build();
                break;
            case 2:
                Scanner innn = new Scanner(System.in);
                System.out.println("При вводе дробного числа отделяйте целую часть запятой!");
                System.out.println("Введите левую границу:");
                a = innn.nextDouble();
                System.out.println("Введите правую границу:");
                b = innn.nextDouble();
                System.out.println("Введите желаемую погрешность:");
                E = innn.nextDouble();
                graph.build();
                break;
        }

        if (a > b) {
            double tmp = a;
            a = b;
            b = tmp;
        }
    }

    public void out(double x, int n) {
        Scanner in = new Scanner(System.in);
        int w = 0;
        try {
            do {
                System.out.println("\nКаким образом вы хотите вывести решение?\n  1 - В файл \n  2 - В консоль");
                w = in.nextInt();
            } while ((w < 1) | (w > 2));
        } catch (Exception e) {
            System.out.println("Некорректный ввод!");
            out(x, n);
        }

        switch (w) {
            case 1:
                System.out.println("Введите имя файла:");
                String filename = in.next();
                try {
                    FileOutputStream fos = new FileOutputStream(filename);
                    PrintStream filePrintStream = new PrintStream(fos);
                    filePrintStream.println("Найденный корень уравнения = " + x + "\nЗначение функции в корне = " + f(x) + "\nЧисло итераций = " + n);
                } catch (Exception e) {
                    System.out.println("Ошибка чтения файла!");
                    System.exit(0);
                }
                System.out.println("Ответ записан!");
                break;
            case 2:
                System.out.println("Найденный корень уравнения = " + x + "\nЗначение функции в корне = " + f(x) + "\nЧисло итераций = " + n);
                break;
        }
    }

    public void half() {
        in();

        if (f(a) * f(b) > 0) {
            System.out.println("Корней на данном промежутке нет!");
            System.exit(0);
        } else {
            while (Math.abs(b - a) > E || Math.abs(f(x)) > E) {
                x = (a + b) / 2;
                n++;
                if (f(a) * f(x) < 0) {
                    b = x;
                } else {
                    a = x;
                }
            }
        }
        out(x, n);
    }

    public void newton() {
        in();
        if (f(a) * f(b) > 0) {
            System.out.println("Корней на данном промежутке нет!");
            System.exit(0);
        }
        if (f(a) * ddf(a) > 0) {
            x = a;
        } else if (f(b) * ddf(b) > 0) {
            x = b;
        }
        while (Math.abs(f(x)) >= E) {
            x = x - f(x) / df(x);
            n++;
        }
        out(x, n);
    }

    public void simple() {
        in();

        if (f(a) * f(b) > 0) {
            System.out.println("Корней на данном промежутке нет!");
            System.exit(0);
        }

        double L = -1 / Math.max(df(a), df(b));
        System.out.println(dfi(a, L));
        System.out.println(dfi(b, L));
        System.out.println(L);

        if (Math.abs(dfi(a, L)) < 1 && Math.abs(dfi(b, L)) < 1) {
            double c = a;
            x = b;
            while (Math.abs(x - c) > E || Math.abs(f(x)) > E) {
                c = x;
                x = c + L * f(c);
                n++;
            }
            out(x, n);
        } else {
            System.out.println("Процесс итерации расходится, ответ вычислить невозможно");
            System.exit(0);
        }
    }
}