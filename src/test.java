import java.awt.image.BufferedImage;

public class test{
    public static void main(String[] args){
        BufferedImage fontBuffer = FontToBmp.getBmp("ABCDEFGH");




        Display display = new Display(162,12);


        for(int i=0; i<12; i++){
            for(int j=0; j<162; j++){
                if((fontBuffer.getRGB(j, i)&0x00FFFFFF)!=0)display.setPixel(j,i,'#');
            }
        }



        display.drawDisplay();




    }
}
