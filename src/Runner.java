import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Runner {
    public static void menu() {
        FourSquareCipher f = new FourSquareCipher();
        Scanner s = new Scanner(System.in);
        
        //stores the option for writing the keyword
        int num1 = 0;
        
        //stores the option for execution the methods
        int num2 = 0;

        //stores the user`s keyword
        String keyword = "";
        
        //stores the file name that contains the keyword
        String file = "";

        //stores the content from the txt file
        String fileText = "";
        
        
        BufferedReader br = null;
        String line;

        do {
            System.out.println(
                "Type 1 to enter the keyword manually\n" +
                "Type 2 to load the keyword from a file" +
                "");
            num1 = s.nextInt();
            s.nextLine();
            if (num1 == 1) {
                System.out.print("Enter the keyword: ");
                keyword = s.nextLine();
                System.out.println("--------------------------------\n");
            } else if (num1 == 2) {
                System.out.print("Enter the path for the file: ");
                file = s.nextLine();
                System.out.println("--------------------------------\n");
            } else {
                System.out.println("Wrong number. Please, try again.");
            }
        }while(num1 != 1 && num1 != 2);
        
        if(num1 == 1) {
            do {
                System.out.println(
                        "Enter the method to execute: \n" +
            
                        "1 - Execute the encryption and decryption methods\n" +
                        
                        "2 - Print on the screen the execution time (in seconds) for the encryption method\n" +
                        
                        "3 - Print the alphabet and keyword squares\n" +
                        "(remember that the values from the keyword squares will be different on each time\n" + 
                        "you execute the program, since they are generated in a random order)\n" +
                        
                        "0 - Quit");
                
                num2 = s.nextInt();

                switch (num2) {
                    case 1:
                        f.convertToBigram(keyword);
                        f.Encrypt();
                        f.Decrypt();
                        f.arrayToString();
                        
                        try {
                            BufferedWriter wr = new BufferedWriter(new FileWriter("ciphertextOutput.txt"));
                            wr.write(f.encryptedMsgString);
                            wr.close();
                        } catch(IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            BufferedWriter wr = new BufferedWriter(new FileWriter("plaintextOutput.txt"));
                            wr.write(f.decryptedMsgString);
                            wr.close();
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    
                    case 2:
                        f.convertToBigram(keyword);
                        final long start = System.nanoTime();
                        f.Encrypt();
                        final long end = System.nanoTime();
                        System.out.println("Time (in milliseconds): " + ((end - start) / 1000) + "ms");
                        break;

                    case 3:
                        f.print();
                        break;

                    case 0:
                        System.out.println("Exiting the program...");
                        break;

                    default:
                        System.out.println("Please, try again.");
                        break;
                }
            }while(num2 != 0);
        } else if(num1 == 2) {
            try {
                br = new BufferedReader(new FileReader(file));
                
                while((line = br.readLine()) != null) {
                    fileText += line;
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
            do {
                System.out.println(
                        "Enter the method to execute: \n" +
            
                        "1 - Execute the encryption and decryption methods\n" +
                        
                        "2 - Print on the screen the execution time (in seconds) for the encryption method\n" +
                        
                        "3 - Print the alphabet and keyword squares\n" +
                        "(remember that the values will be different on each time you execute the program,\n" +
                        "since they are generated in a random order)\n" +
                        
                        "0 - Quit");
                
                num2 = s.nextInt();

                switch (num2) {
                    case 1:
                        f.convertToBigram(fileText);
                        f.Encrypt();
                        f.Decrypt();
                        f.arrayToString();
                        try {
                            BufferedWriter wr = new BufferedWriter(new FileWriter("ciphertextOutput.txt"));
                            wr.write(f.encryptedMsgString);
                            wr.close();
                        } catch(IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            BufferedWriter wr = new BufferedWriter(new FileWriter("plaintextOutput.txt"));
                            wr.write(f.decryptedMsgString);
                            wr.close();
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    
                    case 2:
                        final long start = System.nanoTime();
                        f.convertToBigram(fileText);
                        f.Encrypt();
                        final long end = System.nanoTime();
                        System.out.println("Time (in milliseconds): " + ((end - start) / 1000) + "ms");
                        break;

                    case 3:
                        f.print();
                        break;

                    case 0:
                        System.out.println("Exiting the program...");
                        break;

                    default:
                        System.out.println("Please, try again.");
                        break;
                }
            }while(num2 != 0);
        }
           
    }

    public static void main(String[] args) {
		menu();
	}
}