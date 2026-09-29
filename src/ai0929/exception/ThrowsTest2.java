package ai0929.exception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ThrowsTest2 {
    static void main(String[] args) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader("myData.txt"));

        while (true) {
            String line = br.readLine();

            if(line == null){
                break;
            }

            System.out.println(line);
        }
    }
}