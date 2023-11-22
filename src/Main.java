import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        boolean cont = true;
        while (cont) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            String result = calc(input);
            System.out.println(result);
            if (result.equals("throws Exception")) cont = false;
        }
    }

    public static String calc(String input) {
        try {
            String str = input;
            str = str.replaceAll("\\s", "");
            String sign = "";
            String[] numbers = str.split("[+\\-*/]");

            if (numbers.length > 2) {
                throw new Exception();
            }

            int number1 = 0;
            int number2 = 0;

            boolean roman = false;
            boolean roman1 = false;
            boolean itsRoman;

            String[] romans = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                    "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                    "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                    "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                    "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                    "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                    "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                    "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                    "XCVIII", "XCIX", "C"};

            for (int i = 0; i < romans.length; i++) {   // ксли тру - римское число
                if (numbers[0].equals(romans[i])) {
                    roman = true;
                    i = romans.length + 1;
                }
            }
            for (int i = 0; i < romans.length; i++) {   // если тру - римское число
                if (numbers[1].equals(romans[i])) {
                    roman1 = true;
                    i = romans.length + 1;
                }
            }

            if (roman && roman1) {
                for (int i = 0; i < romans.length; i++) {
                    if (numbers[0].equals(romans[i])) {     //конвертация первого чисоа в индекс массива роман
                        number1 = i;
                        i = romans.length + 1;
                    }
                }
                for (int i = 0; i < romans.length; i++) {   //конвертация второго числа
                    if (numbers[1].equals(romans[i])) {
                        number2 = i;
                        i = romans.length + 1;
                    }
                }
                itsRoman = true;

            } else if (!roman && !roman1) {     // оба не римские - значит арабские
                number1 = Integer.parseInt(numbers[0]);
                number2 = Integer.parseInt(numbers[1]);
                itsRoman = false;
            } else
                throw new Exception();

            if (number1 > 10 || number2 > 10) {   //вводимые значение не больше 10
                throw new Exception();
            }

            int result;
            if (str.contains("+")) {    // определение знака
                sign = "+";
            } else if (str.contains("-")) {
                sign = "-";
            } else if (str.contains("*")) {
                sign = "*";
            } else if (str.contains("/")) {
                sign = "/";
            }

            result = switch (sign) {     // выполнение операции
                case "+" -> number1 + number2;
                case "-" -> number1 - number2;
                case "*" -> number1 * number2;
                case "/" -> number1 / number2;
                default -> throw new Exception();
            };
            if (itsRoman) {
                for (int i = 0; i < romans.length; i++) {   // если римские то ответ в римских
                    if (result < 1) {
                        throw new Exception();
                    }
                    if (result == i) {
                        input = romans[i];
                    }
                }
            } else input = String.valueOf(result);
        } catch (Exception e) {
            input = "throws Exception";
        }
        return input;
    }
}
