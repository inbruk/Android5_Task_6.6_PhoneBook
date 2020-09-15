public class PhoneBook {

    private int currBookSize = 5;
    private int currBookIncSize = 3;

    private String book[][] = new String[currBookSize][2];

    public static void main(String[] args) {
        //Добавить считывание ввода пользователя в цикле
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        return true;
    }

    public static boolean checkName(String name) {
        return true;
    }

    public static String formatName(String name) {
        return "";
    }

    public static String formatPhoneNumber(String number) {
        return "";
    }

    public static void add(String[][] book, String name, String number) {
        //add logic
    }

    public static void list(String[][] book) {
        //print phone book
        System.out.println();
        System.out.println("    Name:                   Phone:            ");
        for(int i=0;i<book.length;i++ )
            System.out.println(book[i][0] + "        " + book[i][1]);
    }
}
