# Four Square Cipher

This project is a Rapid Encryption using the Four-Square cipher algorithm in Java.

# Description

The four-square cipher was invented by the French cryptographer Felix Delastelle (1840-1902) and uses four 5x5 matrices arranged in a square to encrypt pairs of characters in a message. Each of the 5x5 matrices contains 25 letters (for this project, the letter j is merged with the letter i). The upper-left and lower-right quadrants are plaintext squares containing a standard alphabet. The upper-right and lower-left quadrants are the ciphertext squares that contain a mixed random alphabetic sequence. The four-square cipher is a polygraphic substitution cipher that uses the same keyword to encrypt plain-text and decrypt cipher-text, i.e. the key is symmetric.

## Main Features

The algorithm works as the following: 

1) A single plaintext matrix (it will work as if there were two) and the two keyword matrices are created and populated with their respectives values
2) Next, the keyword is trimmed, converted to uppercase and then appended into a bigram (array with two letters on each position). The user can choose either enter the keyword from prompt or read the data from a .txt file
3) Thirdly, both the Encryption and Decryption methods are executed, and the results, which are the ciphertext and the plaintext, are sent to output .txt files
    3.1) Encryption process: Find the first character in the top-right square, at the intersection of the row of the plaintext character in the top-left square, with the column of the plaintext character in the bottom-right square
    3.2) Decryption process: Find the second character in the bottom-left square, at the intersection of the column of the plaintext character in the top-left square, with the row of the plaintext character in the bottom right square

## Running the Application

To run and test the application, first type
```
javac Runner.java
```
The above code will compile the code and check for bugs. Secondly, type
```
java Runner
```
Now the application is running, and you can follow the instructions described in the UI
