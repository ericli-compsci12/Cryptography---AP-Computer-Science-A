import java.util.Scanner;
public class CaesarCipher {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("1. Encode 2. Decode");
        int input = in.nextInt();
        if(input == 1) {
            in.nextLine();
            System.out.print("Input text: ");
            String str = in.nextLine();
            System.out.print("Input key: ");
            int inp = in.nextInt();
            System.out.println(encode(str,inp));
        }
        if (input == 2) {
            in.nextLine();
            System.out.print("Input cypher: ");
            String str = in.nextLine();
            System.out.print("Input key: ");
            int inp = in.nextInt();
            System.out.println(decode(str,inp));
        }
    }
    
    public static String encode(String plainText, int key) {
        String rtn = "";
        
        for(int i = 0; i < plainText.length(); i++) {
            int base = 0;
            if(plainText.charAt(i) >= 65 && plainText.charAt(i) <= 90) base = 65;
            else if(plainText.charAt(i) >= 97 && plainText.charAt(i) <= 123 ) base = 97;
            if (base != 0) rtn = rtn + (char)((plainText.charAt(i) - base + key)%26 + base);
            else rtn = rtn + plainText.charAt(i);
        }
        return rtn;
    }
    
    public static String decode(String cipherText, int key) {
        String rtn = "";
        for(int i = 0; i < cipherText.length(); i++) {
            int base = 0;
            if(cipherText.charAt(i) >= 65 && cipherText.charAt(i) <= 90) base = 65;
            else if(cipherText.charAt(i) >= 97 && cipherText.charAt(i) <= 123 ) base = 97;
            if (base != 0) rtn = rtn + (char)((cipherText.charAt(i) + 26*key - base - key)%26 + base);
            else rtn = rtn + cipherText.charAt(i);
        }
        return rtn;
    }
}