import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class CaesarCipher {
    //main method, deals with user interaction
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
            System.out.print("Do you have a key:(Y/N) ");
            String yn = in.nextLine();
            if(yn.equals("Y")) {
            System.out.print("Input key: ");
            int inp = in.nextInt();
            System.out.println(decode(str,inp));
        }
            else if(yn.equals("N")) {
            decode(str);
            }
        }
    }
    
    //encode a text
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
    
    //decode the text
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
    
    
    //traverses the dictionary to find the key
    public static String decode(String cipherText) {
        String rtn = "";
        int key = 0;
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
            System.out.print("Error! File not found.");
            e.printStackTrace();
        }
        
        a:
        for(int i = 1; i < 26; i++) {
            for(int a = 0; a < words.size(); a++) {
            if(words.get(a).length() == 9) word = words.get(a).toUpperCase();
            if(word.length() == 9 && decode(cipherText,i).toUpperCase().contains(word)) {
                key = i;
                break a;
            }
        }
        if(i==25) return"Key Not Found";
    }
    rtn = "key is " + key + ". Decoded text is " + decode(cipherText,key);
    return rtn;
    }
}