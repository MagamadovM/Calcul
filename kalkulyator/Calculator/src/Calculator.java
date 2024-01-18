import java.util.*;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите первое число(от 1 до 10 или от I до X):");
        String input = scanner.nextLine();
        System.out.println("Ответ: " + calc(input));
    }
    public static  String calc(String input){

        String[] tokens = input.split(" ");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Неверное количество операндов. Введите выражение в формате 'число оператор число'.");
        }

        String input1 =tokens[0].toUpperCase();
        int num1;
        try {
            num1 = convertToArabic(input1);
        } catch (IllegalArgumentException e) {
            try {
                num1 = Integer.parseInt(input1);
                if (num1 < 1 || num1 > 10) {
                    throw new IllegalArgumentException("Число должно быть от 1 до 10 или от I до X.");
                }
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Число должно быть от 1 до 10 или от I до X.");
            }
        }



        String operator =tokens[1];
        String input2 = tokens[2].toUpperCase();
        int num2;
        try {
            num2 = convertToArabic(input2);
        } catch (IllegalArgumentException e) {
            try {
                num2 = Integer.parseInt(input2);
                if (num2 < 1 || num2 > 10) {
                    throw new IllegalArgumentException("Число должно быть от 1 до 10 или от I до X.");
                }
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Число должно быть от 1 до 10 или от I до X.");
            }
        }

        if ((isRomanNumeral(input1) && !isRomanNumeral(input2)) || (!isRomanNumeral(input1) && isRomanNumeral(input2))) {
            throw new IllegalArgumentException("Нельзя складывать римские и арабские числа.");
        }


        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Делить на ноль нельзя");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Неверный оператор, допустимы +, -, * or /.");
        }

        if (result < 0 || result > 100) {
            throw new IllegalArgumentException("Ответ должен быть положительным.");
        }


        if ((isRomanNumeral(input1) && isRomanNumeral(input2)) || (isRomanNumeral(input1) && isRomanNumeral(input2))) {
           return String.valueOf( convertToRoman(result));
        }
        else
            return String.valueOf(result);

    }

    private static int convertToArabic(String input) {
        switch (input) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                throw new IllegalArgumentException("Неверные римские числа, введите от I до X");
        }
    }

    private static String convertToRoman(int number) {
        if (number < 0 ||  number > 100) {
            throw new IllegalArgumentException("Ответ должен быть больше нуля");
        }
        StringBuilder result = new StringBuilder();
        int[] arabicValues = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanNumerals = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int i = 0;
        while (number > 0) {
            if (number - arabicValues[i] >= 0) {
                result.append(romanNumerals[i]);
                number -= arabicValues[i];
            } else {
                i++;
            }
        }
        return result.toString();
    }

    private static boolean isRomanNumeral(String input) {
        String romanNumerals = "IVXLCDM";
        for (char c : input.toCharArray()) {
            if (!romanNumerals.contains(String.valueOf(c))) {
                return false;
            }
        }
        return true;
    }
}

