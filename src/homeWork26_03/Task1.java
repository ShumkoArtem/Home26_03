package homeWork26_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Task1 {
    public static void main(String[] args) throws IOException {
        /**
         * Задание 1
         * Пользователь вводит с клавиатуры набор чисел. По-
         * лученные числа необходимо сохранить в список. После
         * чего нужно показать меню, в котором предложить поль-
         * зователю набор пунктов:
         * 1. Добавить элемент в список;
         * 2. Удалить элемент из списка;
         * 3. Показать содержимое списка;
         * 4. Проверить есть ли значение в списке;
         * 5. Заменить значение в списке.
         * В зависимости от выбора пользователя выполняется
         * действие, после чего меню отображается снова.
         * Для решения задачи используйте подходящий класс
         * из Java Collections Framework.
         */
        System.out.println("Input a string of numbers");
        String str = inputListNumber();//вводим строку набора чисел
        ArrayList<Integer> number;
        number = parsStringInInt(str); // метод выберает из строки числа и добавляет в ArrayList
        System.out.println(number); // проверка (выводим ArrayList в консоль)
        System.out.println("1. Добавить элемент в список;\n" +
                "2. Удалить элемент из списка;\n" +
                "3. Показать содержимое списка;\n" +
                "4. Проверить есть ли значение в списке;\n" +
                "5. Заменить значение в списке;\n" +
                "0. Закончить.");
        boolean flag = true;
        while (flag) {
            int numAction = actionChoice();// выбираем дейтвие из списка
            System.out.println("You choice is " + numAction);// просто для проверки!
            if (numAction == 1) {
                System.out.println("Добавить элемент в список");
                System.out.println("Вариант 1");
                number.add(inputElem()); //метод добавляет элемент в список
                System.out.println("Вариант 2");
                number = addElement(number); // метод принимает список добавляет лемент и возврю новый список
                System.out.println("Добавлен элемент " + number); // добавлено 2 элемента
            } else if (numAction == 2) {
                System.out.println("Удалить элемент из списка");
                int userElement = inputElem(); //метод принимает строку из цифр и парсит в число (без проверок)
                number.removeIf(x -> x.equals(userElement));//сравниваем и удаляем если есть совпадение(все совпадения)
                System.out.println(number); // выводим для проверки
            } else if (numAction == 3) {
                System.out.println("Показать содержимое списка");
                System.out.println("Array List is " + number);
            } else if (numAction == 4) {
                System.out.println("Проверить есть ли значение в списке");
                int num1 = inputElem(); //метод принимает строку из цифр и парсит в число (без проверок)
                if (number.contains(num1)) { // сравниваем есть ли введенное значение в списке
                    System.out.println("Совпадение найдено!!!");
                } else {
                    System.out.println("Совпадение не найдено!!!");
                }
            } else if (numAction == 5) {
                System.out.println("Заменить значение в списке");
                System.out.println("Вариант 1");
                number = setElementInList(number); //замена элемента по указанному индексу
                System.out.println("После изменения вариант 1 " + number);
                System.out.println("Вариант 2");
                number = setElementInList1(number); // замена введенного элемекнта
                System.out.println("После изменения вариант 2 " + number);
            } else if (numAction == 0) {
                System.out.println("The END!!!");
                flag = false;
            }
        }
    }
    // замена введенного элемекнта
    private static ArrayList<Integer> setElementInList1(ArrayList<Integer> num) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> numbers = num;
        System.out.println("Введите число которое хотите заменить");
        int elem = Integer.parseInt(reader.readLine());//парсим в int
        if (numbers.contains(elem)) {
            System.out.println("Введите новый элеммент");
            int newElement = Integer.parseInt(reader.readLine());
            //проверяем совпадение
            while (numbers.contains(elem)) {
                //меняем элементы под указанными индексами
                numbers.set(numbers.indexOf(elem), newElement);
            }
        }else{
            System.out.println("Такого элемента нет в списке");
        }
        return numbers;
    }
    //замена элемента по указанному индексу
    private static ArrayList<Integer> setElementInList(ArrayList<Integer> num) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> numb = num;
        System.out.println("Введите номер ячейки которую хотите заменить от 0 до " + (numb.size() - 1));
        int numberElem = Integer.parseInt(reader.readLine());
        System.out.println("Введите новый элеммент");
        int newElement = Integer.parseInt(reader.readLine());
        if (numberElem < 0 && numberElem > (numb.size() - 1)) {
            throw new IllegalArgumentException("Введено число вне диапазона!!!");
        } else {
            numb.set(numberElem, newElement);
        }
        return numb;
    }
    //метод принимает строку из цифр и парсит в число (без проверок)
    private static int inputElem() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userEl = reader.readLine();
        int remove = Integer.parseInt(userEl);
        return remove;
    }
    // метод добавляет элемент в список и возвращает новый список
    private static ArrayList<Integer> addElement(ArrayList<Integer> num) throws IOException {
        ArrayList<Integer> number = num;
        String temp = Task1.inputListNumber();
        ArrayList<Integer> newElement = parsStringInInt(temp);
        number.addAll(newElement);
        return number;
    }
    private static ArrayList<Integer> parsStringInInt(String str) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) { // если цифра создаем строку
                StringBuilder s = new StringBuilder();
                s.append(str.charAt(i));
                for (int j = i + 1; j < str.length(); j++) {
                    if (Character.isDigit(str.charAt(j))) { // если следующая тоже чифра добавляем в строку
                        s.append(str.charAt(j));
                        i = j;
                    } else {
                        i = j;
                        break;
                    }
                }
                int tempnum = Integer.parseInt(s.toString()); //парсим строку в число
                numbers.add(tempnum); // добавляем число в ArrayList
            }
        }
        return numbers;
    }
    // выбираем дейтвие из списка
    private static int actionChoice() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        System.out.println("Input number 0-5");
        String numAct = reader.readLine();
        try {
            if (numAct == null || numAct.isEmpty()) {
                throw new IOException();
            }
        } catch (IOException ex) {
            System.out.println("String = null or String is empty");
        }
        try {
            num = Integer.parseInt(numAct);
        } catch (NumberFormatException ex) {
            try {
                double d = Double.parseDouble(numAct);
                System.out.println("You input double number!!!");
            } catch (NumberFormatException exe) {
                System.out.println("You input String!!!");
            }
        }
        if (num < 1 || num > 5) {
            System.out.println("You entered a number out of range");
            num = 0;
        }
        return num;
    }
    //вводим строку набора чисел
    private static String inputListNumber() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        return str;
    }
}
