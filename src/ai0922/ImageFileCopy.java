package ai0922;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageFileCopy {
    static void main() {
        try {
            FileInputStream fIn = new FileInputStream("image.jpg");
            FileOutputStream fOut = new FileOutputStream("imageCopy.jpg");

            int data;
            while ((data = fIn.read()) != -1){
                fOut.write((byte)data);
            }

            System.out.println("이미지 복사 완료");

            fIn.close();
            fOut.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
