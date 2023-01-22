
import java.util.Scanner;

public class Main {
    public static boolean isRomNumber = false;
    public static int result = 0;

    public static RomNumber[] romNumbers = RomNumber.values();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение между двумя числами:");
        String text = scanner.nextLine();
        System.out.println(calc(text));
    }

    public static String calc(String input) {
        String[] array = input.split("[+*/-]");
        int numberOne, numberTwo;

        if (array.length != 2) {
            try {
                throw new ArrayIndexOutOfBoundsException();
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Калькулятор умеет выполнять операции только с двумя числами";
            }
        }

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (RomNumber romNumber : romNumbers) {
                if (array[i].trim().equals(String.valueOf(romNumber))) {
                    array[i] = romNumber.toInt() + "";
                    count++;
                }
            }
            try {
                Integer.parseInt(array[i].trim());
            } catch (NumberFormatException e) {
                return "Введите целые числа. Калькулятор принимает на вход значения от I(1) до X(10) включительно";
            }
        }

        if (count == 1) {
            try {
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                return "Нельзя использоваать одновременно разные системы счисления";
            }
        } else if (count == 2) {
            isRomNumber = true;
        }

        numberOne = Integer.parseInt(array[0].trim());
        numberTwo = Integer.parseInt(array[1].trim());

        if ((numberOne < 1 || numberOne > 10) || (numberTwo < 1 || numberTwo > 10)) {
            try {
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                return "Калькулятор принимает на вход значения от 1 до 10 включительно";
            }
        }

        if (input.contains("+")) {
            result = sum(numberOne, numberTwo);
        } else if (input.contains("-")) {
            result = subtract(numberOne, numberTwo);
        } else if (input.contains("*")) {
            result = multiplication(numberOne, numberTwo);
        } else if (input.contains("/")) {
            result = div(numberOne, numberTwo);
        }

        if (isRomNumber) {
            if (result < 0) {
                try {
                    throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    return ("В римской системе нет отрицательных чисел");
                }
            }
            return "Результат вычислений: " + convert(result + "");
        }

        return "Результат вычислений: " + result;
    }

    public static String convert(String result) {
        for (RomNumber romNumber : romNumbers) {
            if (result.equals(romNumber.toInt() + "")) {
                return romNumber + "";
            }
        }
        if (result.length() == 2) {
            String firstPart, secPart = "";
            for (RomNumber romNumber : romNumbers) {
                if ((Integer.parseInt(result) % 10 + "").equals(romNumber.toInt() + "")) {
                    secPart = romNumber + "";
                }
            }
            // попробовать switch case
            if (Integer.parseInt(result) < 40) {
                firstPart = ("X").repeat(Integer.parseInt(result)/10);
                return firstPart + secPart;
            }
            if (Integer.parseInt(result) >= 40 && Integer.parseInt(result) < 50) {
                firstPart = "XL";
                return firstPart + secPart;
            }
            if (Integer.parseInt(result) >= 51 && Integer.parseInt(result) < 90) {
                firstPart = "L" + ("X").repeat((Integer.parseInt(result)-50)/10);
                return firstPart + secPart;
            }
            if (Integer.parseInt(result) >= 90 && Integer.parseInt(result) < 100) {
                firstPart = "XC";
                return firstPart + secPart;
            }
        }
        return null;
    }

    public static int sum(int numberOne, int numberTwo) {
        return numberOne + numberTwo;
    }

    public static int subtract(int numberOne, int numberTwo) {
        return numberOne - numberTwo;
    }

    public static int multiplication(int numberOne, int numberTwo) {
        return numberOne * numberTwo;
    }

    public static int div(int numberOne, int numberTwo) {
        return numberOne / numberTwo;
    }
}