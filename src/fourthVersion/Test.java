package fourthVersion;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Test {

    public static String consoleInput(Scanner scan) {
        return scan.nextLine();
    }
    public static String fileInput() throws IOException {
        File input = new File("input");
        Scanner scan = new Scanner(input);
        return scan.nextLine();
    }

    public static void consoleOutput(double result) {
        System.out.println(result);
    }

    public static void fileOutput(double result) throws IOException {
        File output = new File("output");
        PrintWriter writerResult = new PrintWriter(output);
        writerResult.println(result);
        writerResult.close();
    }

    public static void outputSelection(double result) {
        Scanner scan = new Scanner(System.in);
        while(true) {
            int method = scan.nextInt();
            scan.nextLine();
            switch(method) {
                case 1:
                    consoleOutput(result);
                    return;
                case 2:
                    try {
                        fileOutput(result);
                    } catch (IOException e) {
                        System.out.println("File not found!");
                    }
                    return;
                default:
                    System.out.println("""
                            1. Console Output
                            2. File Output
                            """);
            }
        }
    }

    public static String inputSelection() {
        Scanner scan = new Scanner(System.in);
        while(true) {
            int method = scan.nextInt();
            scan.nextLine();
            switch(method) {
                case 1:
                    return consoleInput(scan);
                case 2:
                    try {
                        return fileInput();
                    } catch (IOException e) {
                        System.out.println("File not found!");
                    }
                    break;
                default:
                    System.out.println("""
                            1. Console Input
                            2. File Input
                            """);
            }
        }
    }

    public static void main(String[] args) {
        String[] arithmeticExpression = inputSelection().split(" ");
        try {
            double a = checkingNumber(arithmeticExpression[0]), b = checkingNumber(arithmeticExpression[2]);
            String operator = arithmeticExpression[1];
            outputSelection(calculate(a, operator, b));
        } catch(NumberFormatException e) {
            System.out.println("Error! Not number");
        } catch(IllegalArgumentException e) {
            System.out.println("Operation Error!");
        } catch(ArithmeticException e) {
            System.out.println("Error! Division by zero");
        }
    }

    public static double checkingNumber(String number) throws NumberFormatException {
        return Double.parseDouble(number);
    }

    public static double calculate(double a, String operator, double b) throws IllegalArgumentException, ArithmeticException {
        switch(operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if(b == 0) {
                    throw new ArithmeticException();
                } else {
                    return a / b;
                }
            default:
                throw new IllegalArgumentException();
        }
    }
}