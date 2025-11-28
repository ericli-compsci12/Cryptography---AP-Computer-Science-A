import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class VigenereCipher {
    //user interaction
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
    
    
    //encode it by applying the key
    public static String encode(String plainText, String keyword) {
        String rtn = "";
        keyword = keyword.toUpperCase();
        for(int i = 0; i < plainText.length(); i++) {
            int base = 0;
            if(plainText.charAt(i) >= 65 && plainText.charAt(i) <= 90) base = 65;
            else if(plainText.charAt(i) >= 97 && plainText.charAt(i) <= 122 ) base = 97;
            if (base != 0) rtn = rtn + (char)((plainText.charAt(i) - base + keyword.charAt(i%keyword.length()) - 65)%26 + base);
            else rtn = rtn + plainText.charAt(i);
        }
        return rtn;
    }

    //subtract key to decode it
    public static String decode(String cipherText, String keyword) {
        String rtn = "";
        keyword = keyword.toUpperCase();
        for(int i = 0; i < cipherText.length(); i++) {
            int base = 0;
            if(cipherText.charAt(i) >= 65 && cipherText.charAt(i) <= 90) base = 65;
            else if(cipherText.charAt(i) >= 97 && cipherText.charAt(i) <= 122 ) base = 97;
            if (base != 0) rtn = rtn + (char)((cipherText.charAt(i) - base + 26*(keyword.charAt(i%keyword.length()) - 65) - keyword.charAt(i%keyword.length()) + 65)%26 + base);
            else rtn = rtn + cipherText.charAt(i);
        }
        return rtn;
    }
    
    //tries to find key(unfinished)
    public static String findKey(String cipherText) {
        String key = "";
        String word = "";
        ArrayList<String> words = new ArrayList();
        try {
            File nameFile = new File("google-10000-english-no-swears.txt");
            Scanner reader = new Scanner(nameFile);
            while(reader.hasNextLine()) {
                words.add(reader.nextLine());
            }
            reader.close();
        }
        catch(FileNotFoundException e) {
            System.out.print("Error, file not found.");
            e.printStackTrace();
        }
        
        a:
        for(int i = 0;  i < words.size(); i++) {
            System.out.println(i);
            key = words.get(i);
            for(int n = 0; n < words.size(); n++) {
                if(words.get(n).length() == 12) word = words.get(n);
                //System.out.println(word);
                word = word.toUpperCase();
                if(word.length()==12 && decode(cipherText,key).toUpperCase().contains(word)) {
                    System.out.println(word);
                    break a;
                }
            }
            if(i == words.size() - 1) System.out.println("keyNotFound");
        }
        return key;
    }

}