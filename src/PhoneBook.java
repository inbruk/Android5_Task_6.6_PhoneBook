/*
 Учебное задание.
 Курс: разработчик Android.
 Группа: Android-5.
 Раздел: Java Core.
 Задание: 6.6.* Практика — пишем калькулятор.
 Исполнитель: inbruk (Шилов Дмитрий).
 Дата: 15.09.2020.
*/

import java.util.Arrays;
import java.util.Scanner;

public class PhoneBook {

    private static int currBookSize = 5;
    private static int currBookIncSize = 3;
    private static int currBookFillSize = 0;

    private static String book[][] = new String[currBookSize][2];

    public static void main(String[] args) {
        System.out.println("Курс: разработчик Андроид. Группа android-5. Раздел: Java Core.");
        System.out.println("Задание 6.6. - Реализуем свой проект и публикуем его на GitHub.");
        System.out.println("Исполнитель: inbruk (Шилов Дмитрий). Дата: 15.09.2020");
        Scanner scanner = new Scanner(System.in);

        while ( true ) {
            System.out.println("Курс: разработчик Андроид. Группа android-5. Раздел: Java Core.");
            System.out.println("Задание 6.6. - Реализуем свой проект и публикуем его на GitHub.");
            System.out.println("Исполнитель: inbruk (Шилов Дмитрий). Дата: 08.09.2020");
            System.out.println();
            System.out.println("Использование: введите Фамилию Имя Отчество, или команду: list, quit");
            String fioRawStr = scanner.nextLine();

            if( fioRawStr.equals("list") ) {
                list();
                return;
            }

            if( fioRawStr.equals("quit") ) {
                return;
            }

            if( checkNameFormat(fioRawStr)==false ) {
                System.out.println("Вы ввели ФИО неправильного формата. Нужно вводить: Фамилия Имя Отчество");
                continue;
            }

            String phoneNumber = getPhoneFromBookByName(fioRawStr);
            if( phoneNumber==null) {
                System.out.println("Абонента с таким именем в справочнике нет.");
                System.out.println("Введите его телефон для добавления: ");
                String newRawPhoneStr = scanner.nextLine();
                String newPhoneStr = formatPhoneNumber(newRawPhoneStr);
                add(fioRawStr, newPhoneStr);
            } else {
                System.out.println("Телефон абонента с такими ФИО:" + phoneNumber);
            }
        }
    }

    public static boolean checkFirstCharIsUppercase(String word) {
        char firstChar = word.charAt(0);
        return Character.isUpperCase(firstChar);
    }

    public static boolean checkNameFormat(String name) {
        if ( name.matches("[^a-zA-Zа-яА-Я]")==true )
            return false;

        String[] words = name.trim().split(" ");
        if( words.length!=3 )
            return false;

        for( String word : words)
            if (checkFirstCharIsUppercase(word) == false)
                return false;

        return true;
    }

    public static String formatPhoneNumber(String phoneNumber) {
        String clean = phoneNumber.replaceAll("[^0-9]", "");
        String result = "+7" + " " + clean.substring(1, 4) + " " +
                clean.substring(4, 7) + " " + clean.substring(7, 9) + " " + clean.substring(9);

        return result;
    }

    public static String getPhoneFromBookByName(String name) {
        for(int i=0;i<book.length;i++) {
            if( book[i][0].equals(name) )
                return book[i][1];
        }
        return null;
    }

    public static void add(String name, String number) {
        if( currBookFillSize==currBookSize ){
            String[][] tempBook = Arrays.copyOf(book, currBookSize + currBookIncSize);
            book = Arrays.copyOf(tempBook, currBookSize + currBookIncSize);
        }
        currBookSize = book.length;
        currBookFillSize++;
    }

    public static void list() {
        System.out.println();
        for(int i=0;i<book.length;i++ )
            System.out.println(book[i][0] + ": " + book[i][1]);
    }
}
