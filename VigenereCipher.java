import java.util.Scanner;
public class VigenereCipher {
    public static void main() {
        Scanner in = new Scanner(System.in);
        System.out.println("1. Encode 2. Decode");
        int input = in.nextInt();
        if(input == 1) {
            in.nextLine();
            System.out.print("Input text: ");
            String str = in.nextLine();
            System.out.print("Input keyword: ");
            String key = in.nextLine();
            System.out.println(encode(str,key));
        }
        if (input == 2) {
            in.nextLine();
            System.out.print("Input cypher: ");
            String str = in.nextLine();
            System.out.print("Input keyword: ");
            String key = in.nextLine();
            System.out.println(decode(str,key));
        }
    }
    
    public static String encode(String plainText, String keyword) {
        String rtn = "";
        keyword = keyword.toUpperCase();
        for(int i = 0; i < plainText.length(); i++) {
            int base = 0;
            if(plainText.charAt(i) >= 65 && plainText.charAt(i) <= 90) base = 65;
            else if(plainText.charAt(i) >= 97 && plainText.charAt(i) <= 123 ) base = 97;
            if (base != 0) rtn = rtn + (char)((plainText.charAt(i) - base + keyword.charAt(i%keyword.length()) - 65)%26 + base);
            else rtn = rtn + plainText.charAt(i);
        }
        return rtn;
    }

    public static String decode(String cipherText, String keyword) {
        String rtn = "";
        keyword = keyword.toUpperCase();
        for(int i = 0; i < cipherText.length(); i++) {
            int base = 0;
            if(cipherText.charAt(i) >= 65 && cipherText.charAt(i) <= 90) base = 65;
            else if(cipherText.charAt(i) >= 97 && cipherText.charAt(i) <= 123 ) base = 97;
            if (base != 0) rtn = rtn + (char)((cipherText.charAt(i) - base + 26*(keyword.charAt(i%keyword.length()) - 65) - keyword.charAt(i%keyword.length()) + 65)%26 + base);
            else rtn = rtn + cipherText.charAt(i);
        }
        return rtn;
    }

}