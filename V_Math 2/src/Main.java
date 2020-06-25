import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        double min;
        double max;
        double accuracy;
        Scanner in = new Scanner(System.in);
        int a;
        do {
            System.out.print("\nВыберите интеграл\n 1. y = sqrt(1 + x^2)\n 2. y = x^2 + 3x\n 3. y = 1 / (1 + x)\n Choose option: ");
            a = in.nextInt();
        } while ((a < 1) | (a > 3));

        System.out.println("Введите нижний предел интегрирования");
        min = in.nextDouble();
        System.out.println("Введите верхний предел интегрирования");
        max = in.nextDouble();
        System.out.println("Введите точность вычисления");
        accuracy = in.nextDouble();

        Calculation calc = new Calculation(min, max, accuracy, a);
        System.out.println("Значение интеграла:");
        System.out.print(calc.calculate());
        System.out.println();
        System.out.println("Число разбиения интервала интегрирования:");
        System.out.println(calc.getN());

    }
}