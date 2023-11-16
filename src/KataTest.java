import java.text.NumberFormat;
import java.util.Scanner;

public class KataTest {
        public static void main(String[] args) throws Exception{
            Scanner input = new Scanner(System.in);
            Main result = new Main();
            System.out.println("Введите арифметическое выражение:");                   // Запрос ввода
            String expression = input.nextLine();           // Ввод выражения
            String answer = result.calc(expression);        // Метод calc для объекта result


            System.out.println(answer);        // Выводим ответ
        }
    }

    class Main{
        public static String calc(String input) throws Exception{
            boolean romanOrArab = false;                      // Для понимания какое число на выходе (рим или араб
            Main romanExamination = new Main();               // Вводим для проверки и перевода из рим в араб
            Main arabToRoman = new Main();                    // Для перевода ответа в римские
            int result = 0;                                   // Считает выражение
            String[] inputSplit = input.split(" ");
            if (inputSplit.length != 3){
                throw new Exception ("throw Exception");                            // Ловим, если не 3 элемента
            }
            Integer firstNumber = 0;
            Integer secondNumber = 0;
            try {
                firstNumber = Integer.valueOf(inputSplit[0]);
                secondNumber = Integer.valueOf(inputSplit[2]);
                    } catch (NumberFormatException e) {                          // Ловим, если не арабское
                try {
                    firstNumber = romanExamination.romanToArab(inputSplit[0]);
                    secondNumber = romanExamination.romanToArab(inputSplit[2]);
                    romanOrArab = true;
                } catch (NumberFormatException ex) {                     // Ловим, если не римское
                    throw new Exception("throw Exception");
                }
            }

            if ((firstNumber < 1) || (firstNumber > 10) || (secondNumber < 1) || (secondNumber > 10)){
                throw new Exception ("throw Exception");                                       // Указываем диапазон значений
            }

            String sign = inputSplit[1];
            switch (sign) {
                case "+" -> result = firstNumber + secondNumber;
                case "-" -> result = firstNumber - secondNumber;
                case "*" -> result = firstNumber * secondNumber;
                case "/" -> result = firstNumber / secondNumber;
                default -> {
                    throw new Exception ("throw Exception");                                    // Ловим, если не знак
                }
            }

            String output;                                               // Наш вывод

            if (romanOrArab){
                if(result < 1){
                    throw new Exception("throw Exception");
                } else {
                    output = arabToRoman.arabToRome(result);
                }
            } else {
                output = Integer.toString(result);
            }

            return output;
        }

        Integer romanToArab(String romanInput){                            // Переводим римский ввод в арабский
            int result = 0;
            int[] arab = {10, 9, 5, 4, 1};
            String[] roman = {"X", "IX", "V", "IV", "I"};
            for (int i = 0; i < arab.length; i++ ) {
                while (romanInput.indexOf(roman[i]) == 0) {
                    result += arab[i];
                    romanInput = romanInput.substring(roman[i].length());  // Исключаем посчитанные числа
                }
            }

            return result;
        }

        String arabToRome(int arabInput){                                  // Перевод араб в рим
            String result = "";
            int value = 0;
            int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            for (int i = 0; i < arab.length; i++){
                value = arabInput / arab[i];
                for (int j = 0; j < value; j++){
                    result = result.concat(roman[i]);
                }
                arabInput = arabInput % arab[i];
            }
            return result;
        }
    }
