import java.util.Arrays;
import java.util.Scanner;

public class PhoneBook {

    private static int currBookSize = 5;
    private static int currBookIncSize = 3;
    private static int currBookFillSize = 0;

    private static String book[][] = new String[currBookSize][2];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while ( true ) {
            System.out.println("Курс: разработчик Андроид. Группа android-5. ");
            String fioRawStr = scanner.nextLine();
           // String fioStr =
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

    public static void add(String[][] book, String name, String number) {
        if( currBookFillSize==currBookSize ){
            String[][] tempBook = Arrays.copyOf(book, currBookSize + currBookIncSize);
            book = Arrays.copyOf(tempBook, currBookSize + currBookIncSize);
        }
        currBookSize = book.length;
        currBookFillSize++;
    }

    public static void list(String[][] book) {
        System.out.println();
        for(int i=0;i<book.length;i++ )
            System.out.println(book[i][0] + ": " + book[i][1]);
    }
}
