import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
    	String content = readFile(inputFilePath);             
        String encryptedMessage = "";

        for (int i = 0; i < content.length(); i++) 
        {
            char c = content.charAt(i);
            if (Character.isLetter(c))    //checks i pointing at char c is letter or not
            {
            	char base;
            	if (Character.isLowerCase(c))    //if letter, whether lowercase or not
            	{
            	    base = 'a';
            	} 
            	else                              //if uppercase
            	{
            	    base = 'A';
            	}
                encryptedMessage += (char) ((c - base + shift) % 26 + base); // +shift for encryption and 26 is to check if the letters are still within 26 alphabets count.
            } 
            else 
            {
                encryptedMessage += c;        //if not a letter
            }
        }

        writeFile(encryptedMessage, encryptedFilePath);
    
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
    	String content = readFile(messageFilePath);
        String decryptedMessage = "";

        for (int i = 0; i < content.length(); i++) 
        {
            char c = content.charAt(i);
            if (Character.isLetter(c)) 
            {
            	char base;
            	if (Character.isLowerCase(c)) 
            	{
            	    base = 'a';
            	} 
            	else 
            	{
            	    base = 'A';
            	}
                decryptedMessage += (char) ((c - base - shift + 26) % 26 + base);  //-shift for decryption
            }
            else 
            {
                decryptedMessage += c;
            }
        }

        writeFile(decryptedMessage, decryptedFilePath);
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        String message = "";
        //TODO: Read file from filePath
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) 
        {
            message += scanner.nextLine() + "\n";  //reading line by line
        }
        scanner.close();

        return message.trim();
        //return message;
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        //TODO: Write to filePath
    	try
   	 {
            FileWriter writer = new FileWriter(filePath);
            writer.write(data);
            writer.close();
        } 
   	 catch (Exception e) 
   	 {
            e.printStackTrace();
        }
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
