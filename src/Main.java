import java.awt.*;
import java.awt.image.BufferedImage;

public class Main{
    public static void main(String[] args){
        String asciiChars="!\\"+'"'+"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz{|}~ ";
        Display display=new Display(200, 200, new AsciiMap("font/JetBrainsMono-Regular.ttf", asciiChars));
        Rasterizer r=display.createRasterizer();

        for(int i=0; i<5; i++){
            r.drawTriangle(i*40, 0, 40+i*40, 0, i*40, 100, i);
        }
        display.print();
    }
}
