import java.awt.*;
import java.awt.image.BufferedImage;

public class Rasterizer{
    Display display;

    public Rasterizer(Display d){
        display=d;
    }

    private BufferedImage getShadeRaster(int w, int h,int shade){
        int pos=0xFFFFFFFF;
        int neg=0x000000;

        BufferedImage raster = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);

        if(shade<4){
            for(int a=0; a<w-6; a+=6){
                for(int b=0; b<h-6; b+=6){

                    for(int i=0; i<3; i+=2){
                        for(int j=0; j<3; j+=2){

                            for(int k=0; k<2; k++){
                                for(int l=0; l<2; l++){
                                    raster.setRGB(a+i+k, b+j+l, neg);
                                    if(shade-((i+j)/2+k+l)>=0){
                                        raster.setRGB(a+i+k, b+j+l, pos);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }else{
            for(int i=0; i<raster.getWidth(); i++){
                for(int j=0; j<raster.getHeight(); j++){
                    raster.setRGB(i,j, pos);
                }
            }
        }
        return raster;
    }

    public void drawTriangle(int x1,int y1,int x2,int y2,int x3,int y3,int shade){
        x1=(x1*9)/4;
        x2=(x2*9)/4;
        x3=(x3*9)/4;


        int min[]=new int[]{
                Math.min(Math.min(x1,x2),x3),
                Math.min(Math.min(y1,y2),y3)
        };
        int max[]=new int[]{
                Math.max(Math.max(x1,x2),x3),
                Math.max(Math.max(y1,y2),y3)
        };

        int pos=0xFFFFFFFF;
        int neg=0x000000;

        BufferedImage raster = getShadeRaster(max[0],max[1],shade);

        for(int i=min[0]; i<max[0]; i++){
            for(int j=min[1]; j<max[1]; j++){
                if(i<display.buffer.w&&j<display.buffer.h&&i>0&&j>0&&i<raster.getWidth()&&j<raster.getHeight()){
                    if(raster.getRGB(i, j)==pos){
                        if(getDoubleTriangleArea(x1, y1, x2, y2, x3, y3)==getDoubleTriangleArea(i, j, x2, y2, x3, y3)+getDoubleTriangleArea(x1, y1, i, j, x3, y3)+getDoubleTriangleArea(x1, y1, x2, y2, i, j)){
                            display.buffer.image.setRGB(i, j, pos);
                        }
                    }
                }
            }
        }
    }

    private int getDoubleTriangleArea(int x1,int y1,int x2,int y2,int x3,int y3){
        return Math.abs(x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2));
    }

    private int getPointLineDist(int pointX,int pointY,int lineX1, int lineY1,int lineX2, int lineY2){
        int distX=lineX2-lineX1;
        int distY=lineY2-lineY1;
        return ((distY*pointX-distX*pointY)+(lineX2*lineY1-lineY2*lineX1))/(distX*distX+distY*distY);
    }
    private int getPointLineDistConst(int pointX,int pointY,int lineX1, int lineY1,int lineX2, int lineY2){
        int distX=lineX2-lineX1;
        int distY=lineY2-lineY1;
        return ((distY*pointX-distX*pointY)+(lineX2*lineY1-lineY2*lineX1))/(distX*distX+distY*distY);
    }
}
