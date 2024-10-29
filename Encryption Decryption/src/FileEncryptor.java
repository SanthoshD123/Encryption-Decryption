import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileEncryptor {
    public static void main(String[] args) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            Files.write(Paths.get("encryption.key"), secretKey.getEncoded());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("\nFile Encryption/Decryption Tool");
                System.out.println("1. Encrypt a file");
                System.out.println("2. Decrypt a file");
                System.out.println("3. Exit");
                System.out.print("Choose an option (1-3): ");

                String choice = reader.readLine();

                if (choice.equals("3")) {
                    break;
                }

                System.out.print("Enter input file path: ");
                String inputFile = reader.readLine();
                System.out.print("Enter output file path: ");
                String outputFile = reader.readLine();

                if (choice.equals("1")) {
                    encryptFile(inputFile, outputFile, secretKey);
                    System.out.println("File encrypted successfully!");
                } else if (choice.equals("2")) {
                    decryptFile(inputFile, outputFile, secretKey);
                    System.out.println("File decrypted successfully!");
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void encryptFile(String inputFile, String outputFile, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                byte[] output = cipher.update(buffer, 0, bytesRead);
                if (output != null) {
                    fos.write(output);
                }
            }

            byte[] finalOutput = cipher.doFinal();
            if (finalOutput != null) {
                fos.write(finalOutput);
            }
        }
    }

    private static void decryptFile(String inputFile, String outputFile, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                byte[] output = cipher.update(buffer, 0, bytesRead);
                if (output != null) {
                    fos.write(output);
                }
            }

            byte[] finalOutput = cipher.doFinal();
            if (finalOutput != null) {
                fos.write(finalOutput);
            }
        }
    }
}