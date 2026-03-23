import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class FontToBmp{
    String fontfile;
    FontMetrics fm;

    FontToBmp(String f){
        fontfile=f;
    }

    public BufferedImage getBmp(String input){
        BufferedImage bmpOut = new BufferedImage(1130,27,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bmpOut.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        try{
            Font customFont = Font.createFont(Font.TRUETYPE_FONT,new File(fontfile));
            Font font=customFont.deriveFont(20f);

            g2d.setFont(font);
            fm = g2d.getFontMetrics();

            g2d.setColor(Color.BLACK);
            g2d.fillRect(0,0,bmpOut.getWidth(),bmpOut.getHeight());

            g2d.setColor(Color.WHITE);
            g2d.drawString(input,0,fm.getAscent()-fm.getDescent());

        }catch(Exception e){
            e.printStackTrace();
        }
        g2d.dispose();
        return bmpOut;
    }


    public int[][] getSamples(BufferedImage image){
        int width=fm.charWidth(' ');
        int height=fm.getAscent();

        int[][] samples=new int[image.getWidth()/width][9];

        System.out.println(height);


        for(int i=0; i<samples.length; i++){//each char
            for(int j=0; j<width; j++){//x pixel
                for(int k=0; k<height; k++){//y pixel
                    int dimension = 3*Math.min(k/(height/3),2)+Math.min(j/(width/3),2);
                    if(image.getRGB(i*width+j,k)==-1){
                        samples[i][dimension]++;
                    }
                }
            }
        }
        return samples;
    }
}
