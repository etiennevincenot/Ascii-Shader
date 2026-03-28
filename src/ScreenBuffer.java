import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ScreenBuffer{
    int w;
    int h;
    BufferedImage image;

    AsciiMap map;

    char[][] screenPixel;

    Display display;

    public ScreenBuffer(int w, int h, Display d,AsciiMap m){
        map=m;

        this.w=w;
        this.h=h;
        this.display = d;
        screenPixel = new char[w][h];
        image = new BufferedImage(w*6,h*6, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d=image.createGraphics();


        try{
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0,0,w*6,h*6);
            g2d.setColor(Color.WHITE);
            g2d.fillOval(w/2,h/2,270,120);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void set(int x, int y, char c){
        screenPixel[x][y] = c;
    }

    public char get(int x, int y){
        return screenPixel[x][y];
    }

    public int placeChar(int x,int y){
        int vec=0;

        for(int g=0; g<3; g++){
            for(int i=0; i<3; i++){
                for(int j=0; j<2; j++){
                    for(int k=0; k<2; k++){
                        if(image.getRGB(x*6+i*2+j,y*6+g*2+k)==-1){
                            vec++;
                        }
                        if((vec&3)==3){
                            break;
                        }
                    }
                    if((vec&3)==3){
                        break;
                    }
                }
                vec<<=2;
            }

        }
        vec>>=2;
        set(x,y,map.instructionLookUp[vec]);
        return vec;
    }

    public void fillBuffer(){
        for(int i=0; i<screenPixel.length; i++){
            for(int j=0; j<screenPixel[i].length; j++){
                placeChar(i,j);
            }
        }
    }




}
