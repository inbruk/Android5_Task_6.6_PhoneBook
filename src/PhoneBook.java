import java.util.Comparator;
import java.util.Scanner;
import java.util.Arrays;

public class PhoneBook {

    private static int currBookSize = 5;
    private static int currBookIncSize = 3;
    private static int currBookFillSize = 0;

    private static String book[][] = new String[currBookSize][2];

    public static void main(String[] args) {
        System.out.println();
        System.out.println("Курс: разработчик Андроид. Группа android-5. Раздел: Java Core.");
        System.out.println("Задание 6.6 - Реализуем свой проект и публикуем его на GitHub.");
        System.out.println("Исполнитель: inbruk (Шилов Дмитрий). Дата: 15.09.2020");
        System.out.println();

        System.out.print("Выполняется предварительное заполнение справочника");
        addToBook("Иванов Иван Иванович", checkAndFormatPhoneNumber("8 911 999 99 99"));
        System.out.print(".");
        addToBook("Сергеева Мария Анатольевна", checkAndFormatPhoneNumber("8 888 454 33 33"));
        System.out.print(".");
        addToBook("Яшкин Валентин Петрович", checkAndFormatPhoneNumber("8 788 567 44 44"));
        System.out.print(".");
        addToBook("Бормотуха Иван Петрович", checkAndFormatPhoneNumber("+7(808)-333-67-56"));
        System.out.print(".");
        addToBook("Лумумба Патрис Пьерович", checkAndFormatPhoneNumber("+7 905 777 35 45"));
        System.out.println("!");

        Scanner scanner = new Scanner(System.in);

        while ( true ) {
            System.out.println("Использование: введите Фамилия Имя Отчество, или команду: list, quit");
            System.out.print("=> ");
            String fioRawStr = scanner.nextLine();

            if( fioRawStr.equals("list") ) {
                enlistTheBookWithSorting();
                continue;
            }

            if( fioRawStr.equals("quit") ) {
                return;
            }

            if( checkNameFormat(fioRawStr)==false ) {
                System.out.println("Вы ввели ФИО неправильного формата. Нужно вводить: Фамилия Имя Отчество.");
                System.out.println("Повторите попытку !");
                System.out.println();
                continue;
            }

            String phoneNumber = getPhoneFromBookByName(fioRawStr);
            if( phoneNumber==null) {
                System.out.println("Абонента с таким ФИО в справочнике нет.");
                System.out.println("Введите его/ее телефон для добавления: ");
                String newRawPhoneStr = scanner.nextLine();

                String newPhoneStr = checkAndFormatPhoneNumber(newRawPhoneStr);
                if( newPhoneStr==null ) { // если ввели некорректный номер телефона
                    System.out.println("Вы ввели номер телефона неправильного формата. Нужно вводить 11 цифр.");
                    System.out.println("Повторите попытку добавления записи в справочник сначала !");
                    System.out.println();
                    continue;
                } else {
                    addToBook(fioRawStr, newPhoneStr);
                }
            } else {
                System.out.println("Телефон абонента с такими ФИО: " + phoneNumber);
            }
        }
    }

    public static boolean checkFirstCharIsUppercase(String word) {
        char firstChar = word.charAt(0);
        return Character.isUpperCase(firstChar);
    }

    public static boolean checkNameFormat(String name) {
        if ( name.matches("[a-zA-Zа-яА-Я\\s]+")==false )
            return false;

        String[] words = name.trim().split(" ");
        if( words.length!=3 )
            return false;

        for( String word : words)
            if (checkFirstCharIsUppercase(word) == false)
                return false;

        return true;
    }

    public static String checkAndFormatPhoneNumber(String phoneNumber) {
        String result;

        try {
            String clean = phoneNumber.replaceAll("[^0-9]", "");
            result = "+7" + " " + clean.substring(1, 4) + " " +
                    clean.substring(4, 7) + " " + clean.substring(7, 9) + " " + clean.substring(9);
        }
        catch(Exception e) {
            result = null; // в случае ввода некорректного номера телефона
        }

        return result;
    }

    public static String getPhoneFromBookByName(String name) {
        for(int i=0;i<currBookFillSize;i++) {
            if( book[i][0].equals(name) )
                return book[i][1];
        }
        return null;
    }

    public static void addToBook(String name, String number) {
        if( currBookFillSize==currBookSize ){
            String[][] tempBook = book;
            book = new String[currBookSize + currBookIncSize][2];
            for(int i=0;i<currBookSize;i++) {
                book[i][0] = tempBook[i][0];
                book[i][1] = tempBook[i][1];
            }
            currBookSize = book.length;
        }
        book[currBookFillSize][0] = name;
        book[currBookFillSize][1] = number;
        currBookFillSize++;
    }

    // Used for sorting in ascending order by FIO
    private static class SortByFIO implements Comparator< String[] > {
        public int compare(String[] a, String[] b) {
            return a[0].compareTo( b[0] );
        }
    }

    public static void enlistTheBookWithSorting() {
        // сортируем заполненную часть массива
        String[][] tempBook = Arrays.copyOf(book, currBookFillSize);
        Arrays.sort(tempBook, new SortByFIO());
        for(int i=0;i<currBookFillSize;i++ )
            book[i] = tempBook[i];

        // собственно выводим заполненную часть в консоль
        System.out.println();
        for(int i=0;i<currBookFillSize;i++ )
            System.out.println(book[i][0] + ": " + book[i][1]);
        System.out.println();
    }
}
