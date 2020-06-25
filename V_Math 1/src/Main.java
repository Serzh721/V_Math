import java.io.*;
import java.util.Scanner;

public class Main {

    static void showMatrix(int size, double[][] matrix, String outputString) {
        if (matrix != null) {
            System.out.println(outputString);
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size + 1; j++)
                    matrix[i][j] += 0.0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size + 1; j++) {
                    System.out.format("%6.4f ", matrix[i][j]);
                    if (j == size - 1)
                        System.out.print("|");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    static void showSolutions(int size, double[] sol, String str) {
        System.out.println(str);
        for (int i = 0; i < size; i++) {
            System.out.format("x[%d] = %6.3f\n", i, sol[i]);
        }
    }

    static void showError(int size, double[] err) {
        System.out.println("\nErrors: ");
        for (int i = 0; i < size; i++) {
            //System.out.println("[" + i + "] = " + err[i]);
            System.out.print("[" + i + "] = ");
            System.out.format("%e ", err[i]);
            System.out.println();
        }
    }

    static int inputSize() {
        int a = 0;
        int size = 0;
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("How to input size?\n 1 - Keyboard input\n 2 - From file\nChoose option: ");
            a = in.nextInt();

        } while ((a < 1) || (a > 2));

        switch (a) {
            case 1:
                System.out.print("Input size: ");
                size = in.nextInt();
                break;
            case 2:
                System.out.print("Input file name:  ");
                Scanner inn = new Scanner(System.in);
                String filename = inn.nextLine();
                size = sizeFromFile(filename);
                break;
        }
        return size;
    }

    static double[][] inputMatrix(int size) {
        Scanner in = new Scanner(System.in);
        double[][] matrix = new double[size][size + 1];

        int a = 0;
        do {
            System.out.print("\nHow to input matrix values?\n 1 - Keyboard input\n 2 - From file\nChoose option: ");
            a = in.nextInt();
        } while ((a < 1) | (a > 2));
        switch (a) {
            case 1:
                System.out.println("Input values: ");
                Scanner inn = new Scanner(System.in);
                for (int i = 0; i < size; i++)
                    for (int j = 0; j < size + 1; j++) {
                        System.out.print("matrix[" + i + "][" + j + "] = ");
                        matrix[i][j] = inn.nextDouble();
                    }
                break;
            case 2:
                System.out.print("Input file name: ");
                Scanner innn = new Scanner(System.in);
                String filename = innn.nextLine();
                matrix = matrixFromFile(size, filename);
                break;
        }
        return matrix;
    }

    static int sizeFromFile(String filename) {
        String size = "";
        try {
            FileInputStream fstream = new FileInputStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            if ((size = br.readLine()) != null) {
                return Integer.parseInt(size);
            } else throw new Exception();
        } catch (Exception e) {
            System.out.println("Invalid value");
        }
        return 0;
    }

    static double[][] matrixFromFile(int size, String filename) {
        double[][] matrix = new double[size][size + 1];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < 0; j++)
                matrix[i][j] = 0;
        try {
            FileInputStream fstream = new FileInputStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            int lineCount = 0;
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(" ");
                for (int j = 0; j < size + 1; j++)
                    matrix[lineCount][j] = Double.parseDouble(numbers[j]);
                lineCount++;
            }
        } catch (Exception e) {
            System.out.println("Invalid value");
        }
        return matrix;
    }


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int a = 0;
        do {
            System.out.print("Menu: \n 1 - Input matrix\n 2 - Test\n Choose option: ");
            a = in.nextInt();
        } while ((a < 1) | (a > 2));

        int size = 0;
        double[][] matrix = null;

        switch (a) {
            case 1:
                size = inputSize();
                matrix = inputMatrix(size);
                break;
            case 2:
                size = 3;
                matrix = matrixFromFile(size, "test.txt");
                break;
        }

        showMatrix(size, matrix, "\nMatrix:");
        Gauss Count = new Gauss(size, matrix);

        if (!Count.checkIfHasSolutions()) {
            System.out.println("There is no solution");
        } else {
            double[][] Triangmatrix = Count.getTriangular();
            showMatrix(size, Triangmatrix, "\nTriangular: ");
            showSolutions(size, Count.getSolutions(), "Solution: ");
            showError(size, Count.getError(Count.getSolutions()));
            System.out.println("\nDet = " + Count.getA());
            System.out.println("\nDet2 = " + Count.DD());

        }
    }
}