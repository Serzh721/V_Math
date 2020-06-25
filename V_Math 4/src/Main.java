import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Данные в файле должны быть разделены пробелом!");
        System.out.print("Введите имя файла для ввода данных:  ");
        Scanner in = new Scanner(System.in);
        String filename = in.nextLine();
        System.out.print("Введите количество пар х-у:  ");
        int n = in.nextInt();
        double[] x = new double[n];
        double[] y = new double[n];

        Scanner scanner = new Scanner(new File(filename));
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextDouble();
        }
        for (int i = 0; i < n; i++) {
            y[i] = scanner.nextDouble();
        }

        Calculation calc = new Calculation();

        System.out.print("Введите имя файла для вывода ответа:  ");
        String filename2 = in.next();
        FileOutputStream fos = new FileOutputStream(filename2);
        PrintStream fPrint = new PrintStream(fos);

        double[] AB = new double[11];

        String text = "Вид функции         |     a     |     b     |     c     |     S     |   Sigma   |";
        fPrint.println(text);

        calc.Linear(x, y, n);
        AB[0] = calc.a;
        AB[1] = calc.b;
        double min = calc.S;
        String minName = "Линейная";
        text = "f = a*x + b         |   " + String.format("%.3f", calc.a) + "      " + String.format("%.3f", calc.b) + "         -         " + String.format("%.3f", calc.S) + "       " + String.format("%.3f", calc.sigma);
        fPrint.println(text);

        calc.pow(x, y, n);
        AB[2] = calc.a;
        AB[3] = calc.b;
        if (calc.S < min) {
            min = calc.S;
            minName = "Степенная";
        }
        text = "f = a*x^b           |   " + String.format("%.3f", calc.a) + "       " + String.format("%.3f", calc.b) + "         -         " + String.format("%.3f", calc.S) + "       " + String.format("%.3f", calc.sigma);
        fPrint.println(text);

        calc.exp(x, y, n);
        AB[4] = calc.a;
        AB[5] = calc.b;
        if (calc.S < min) {
            min = calc.S;
            minName = "Экспоненциальная";
        }
        text = "f = a*e^(bx)        |   " + String.format("%.3f", calc.a) + "       " + String.format("%.3f", calc.b) + "         -         " + String.format("%.3f", calc.S) + "      " + String.format("%.3f", calc.sigma);
        fPrint.println(text);

        calc.log(x, y, n);
        AB[6] = calc.a;
        AB[7] = calc.b;
        if (calc.S < min) {
            min = calc.S;
            minName = "Логарифмическая";
        }
        text = "f = a*lnx + b       |   " + String.format("%.3f", calc.a) + "     " + String.format("%.3f", calc.b) + "         -         " + String.format("%.3f", calc.S) + "      " + String.format("%.3f", calc.sigma);
        fPrint.println(text);

        calc.quadratic(x, y, n);
        AB[8] = calc.a;
        AB[9] = calc.b;
        AB[10] = calc.c;
        if (calc.S < min) {
            min = calc.S;
            minName = "Квадратичная";
        }
        text = "f = a*x^2 + b*x + c |   " + String.format("%.3f", calc.a) + "       " + String.format("%.3f", calc.b) + "       " + String.format("%.3f", calc.c) + "      " + String.format("%.3f", calc.S) + "       " + String.format("%.3f", calc.sigma);
        fPrint.println(text);
        fPrint.println();
        fPrint.println("  x      y     f = a*x + b   f = a*x^b   f = a*e^(bx)   f = a*lnx + b   f = a*x^2 + b*x + c");
        for (int i = 0; i < n; i++) {
            fPrint.println(String.format("%.3f", x[i]) + "  " + String.format("%.3f", y[i]) + "     " + String.format("%.3f", calc.lin[i]) + "        " + String.format("%.3f", calc.pow[i]) + "        " + String.format("%.3f", calc.exp[i]) + "         " + String.format("%.3f", calc.log[i]) + "            " + String.format("%.3f", calc.quad[i]));
        }
        fPrint.println();
        fPrint.println("Наилучшая аппроксимирующая функция - " + minName);
        Graph graph = new Graph();
        graph.build(AB, y);
    }
}