import java.awt.*;
import java.awt.image.BufferedImage;

public class Main{
    public static void main(String[] args){
        FontToBmp ftb = new FontToBmp("font/JetBrainsMono-Regular.ttf");
        String asciiChars="!\\"+'"'+"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz{|}~ ";
        BufferedImage image = ftb.getBmp(asciiChars);
        AsciiMap map = new AsciiMap();
        map.fillMapToClosestChar(ftb.getSamples(image),asciiChars);
        int[] dimensions = {100,80};
        Display display = new Display(dimensions[0],dimensions[1]);

        ScreenBuffer buffer = new ScreenBuffer(dimensions[0],dimensions[1],display,map);
        buffer.fillBuffer();

        display.loadBuffer(buffer);
        display.drawDisplay();

        /*
        for(int j=0; j<6; j++){
            int[] a = map.unPackVec(buffer.placeChar(21, 15+j));
            for (int i = 0; i < 9; i++) {
                System.out.print(a[i] + " ");
                if ((i + 1) % 3 == 0) {
                    System.out.println();
                }
            }
        }


         */


        int[][] reference=ftb.getSamples(image);
        for(int i=0; i<reference.length; i++){
            System.out.println();
            System.out.println(asciiChars.charAt(i));
            for(int j=0; j<9; j++){
                if(j%3==0&&j>0){
                    System.out.println();
                }
                System.out.print(reference[i][j]+" ");
            }
        }

    }
}
