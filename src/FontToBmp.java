import java.awt.*;
import java.awt.image.BufferedImage;


public class FontToBmp{

    public static BufferedImage getBmp(String input){
        BufferedImage bmpOut = new BufferedImage(162,12,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bmpOut.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        try{
            Font font = new Font("Monospaced",Font.TRUETYPE_FONT,12);
            g2d.setFont(font);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(0,0,bmpOut.getWidth(),bmpOut.getHeight());

            g2d.setColor(Color.WHITE);

            g2d.drawString(input,0,10);


        }catch(Exception e){
            e.printStackTrace();
        }


        g2d.dispose();
        return bmpOut;
    }
}
