import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    private static final Map<String, Integer> ROMAN_NUMERALS = new HashMap<>();
    static {
        ROMAN_NUMERALS.put("I", 1);
        ROMAN_NUMERALS.put("II", 2);
        ROMAN_NUMERALS.put("III", 3);
        ROMAN_NUMERALS.put("IV", 4);
        ROMAN_NUMERALS.put("V", 5);
        ROMAN_NUMERALS.put("VI", 6);
        ROMAN_NUMERALS.put("VII", 7);
        ROMAN_NUMERALS.put("VIII", 8);
        ROMAN_NUMERALS.put("IX", 9);
        ROMAN_NUMERALS.put("X", 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение:");
        String input = scanner.nextLine();

        try {
            String result = calc(input);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            System.exit(0);
        }
    }

    public static String calc(String input) throws Exception {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new Exception("Некорректный формат выражения");
        }

        int a, b;
        try {
            a = Integer.parseInt(parts[0]);
            b = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new Exception("Допускаются только арабские или римские цифры от 1 до 10");
        }

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new Exception("Допускаются только арабские или римские цифры от 1 до 10");
        }

        int result;
        switch (parts[1]) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new Exception("Допускаются только операции +, -, *, /");
        }

        if (ROMAN_NUMERALS.containsKey(parts[0]) && ROMAN_NUMERALS.containsKey(parts[2])) {
            return toRomanNumerals(result);
        } else {
            return Integer.toString(result);
        }
    }

    private static String toRomanNumerals(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Результат не может быть меньше единицы или больше 3999");
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : ROMAN_NUMERALS.entrySet()) {
            String romanNumeral = entry.getKey();
            int arabicNumber = entry.getValue();
            while (number >= arabicNumber) {
                sb.append(romanNumeral);
                number -= arabicNumber;
            }
        }
        return sb.toString();
    }
}