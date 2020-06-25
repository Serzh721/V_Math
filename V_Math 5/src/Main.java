import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static double[] x = new double[7];
    static double[] y = new double[7];
    static double arg = 0;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        input();
        System.out.print("Введите значение аргумента для метода Лагранжа: ");
        arg = in.nextDouble();
        System.out.println("Ответ: " + Lagrange.solve(x, y, arg));

        System.out.println("Введите значение аргумента для метода Ньютона для неравноотстоящих узлов: ");
        arg = in.nextDouble();
        System.out.println("Ответ: " + NewtonUnequal.solve(x, y, arg));

        input();
        System.out.println("Введите значение аргумента для метода Ньютона для равноотстоящих узлов: ");
        arg = in.nextDouble();
        NewtonEqual neweq = new NewtonEqual(x, y, arg);
        System.out.println("Ответ: " + neweq.solve());
    }

    public static void input() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите имя файла с исходной таблицей: ");
        String filename = in.nextLine();
        Scanner scanner = new Scanner(new File(filename));
        for (int i = 0; i < x.length; i++) {
            x[i] = scanner.nextDouble();
            y[i] = scanner.nextDouble();
        }
    }
}