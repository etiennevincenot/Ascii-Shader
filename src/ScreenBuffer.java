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


    Graphics2D g2d;

    public ScreenBuffer(int w, int h,AsciiMap m){
        map=m;

        this.w=(w*9)/4;
        this.h=h;


        screenPixel = new char[this.w][this.h];
        image = new BufferedImage(this.w*6,this.h*6, BufferedImage.TYPE_INT_RGB);

        g2d=image.createGraphics();
        clear(0);
    }

    public void set(int x, int y, char c){
        screenPixel[x][y] = c;
    }

    public char get(int x, int y){
        return screenPixel[x][y];
    }

    public int placeChar(int x,int y){
        int vec=0;

        for(int g=0; g<3; g++){//y
            for(int i=0; i<3; i++){//x
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

    /// Drawing Methods

    public void clear(int color){
        if(color==1){
            g2d.setColor(Color.WHITE);
        }else{
            g2d.setColor(Color.BLACK);
        }
        try{
            g2d.fillRect(0,0,w*9,h*9);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void drawCircle(int x, int y, int radius, int color){
        if(color==1){
            g2d.setColor(Color.WHITE);
        }else{
            g2d.setColor(Color.BLACK);
        }
        try{
            g2d.fillOval(((x-radius)*9)/4,y-radius,(radius*18)/4,radius*2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void drawRect(int x, int y, int width, int height, int color){
        if(color==1){
            g2d.setColor(Color.WHITE);
        }else{
            g2d.setColor(Color.BLACK);
        }
        try{
            g2d.fillRect((x*9)/4,y,(width*9)/4,height);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void drawTri(int x1, int y1, int x2, int y2, int x3, int y3, int color){
        if(color==1){
            g2d.setColor(Color.WHITE);
        }else{
            g2d.setColor(Color.BLACK);
        }
        try{
            g2d.fillPolygon(new int[]{(x1*9)/4,(x2*9)/4,(x3*9)/4},new int[]{y1,y2,y3},3);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void drawLine(int x1, int y1, int x2, int y2, int color){
        if(color==1){
            g2d.setColor(Color.WHITE);
        }else{
            g2d.setColor(Color.BLACK);
        }
        try{
            g2d.drawLine((x1*9)/4, y1, (x2*9)/4, y2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void drawImage(String filePath){
        try{
            g2d.drawImage(ImageIO.read(new File(filePath)),0,0, w*6, h*6,null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void drawImage(BufferedImage img){
        try{
            for(int i=0; i<Math.min(image.getWidth(),img.getWidth()); i++){
                for(int j=0; j<Math.min(image.getHeight(),img.getHeight()); j++){
                    if(image.getRGB(i,j)==1){
                        image.setRGB(i,j,1);
                        System.out.println(i+" "+j);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
