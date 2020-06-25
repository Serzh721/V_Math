import java.util.Scanner;

public class Main {

    static Calculation calc = new Calculation();

    public static void st() {
        Scanner in = new Scanner(System.in);
        int a = 0;
        try {
            do {
                System.out.print("Меню: \n  1 - Начать вычисление\n  2 - Выйти\nВаш выбор: ");
                a = in.nextInt();
            } while ((a < 1) | (a > 2));
        } catch (Exception e) {
            System.out.println("Некорректный ввод!");
            st();
        }

        switch (a) {
            case 1:
                System.out.println("\nДана функция: -1.8x^3 -2.94x^2 + 10.37x + 5.38\n");
                st2();
                break;
            case 2:
                System.exit(0);
                break;
        }
    }

    public static void st2() {
        Scanner in = new Scanner(System.in);
        int a = 0;
        try {
            do {
                System.out.println("Выберите метод решения: \n  1 - Метод половинного деления\n  2 - Метод Ньютона\n  3 - Метод простой итерации\nВаш выбор: ");
                a = in.nextInt();
            } while ((a < 1) | (a > 3));
        } catch (Exception e) {
            System.out.println("Некорректный ввод!");
            st2();
        }

        switch (a) {
            case 1:
                calc.half();
                break;
            case 2:
                calc.newton();
                break;
            case 3:
                calc.simple();
                break;
        }
    }

    public static void main(String[] args) {
        st();
    }
}