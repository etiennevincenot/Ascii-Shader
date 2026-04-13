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
        BufferedImage bmpOut = new BufferedImage(1140,27,BufferedImage.TYPE_INT_RGB);
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
            /*
            for(int i=0; i<fm.getAscent()-2; i++){
                for(int j=0; j<bmpOut.getWidth()/10; j++){
                    if(bmpOut.getRGB(j,i)==-1){
                        System.out.print("#");
                    }else{
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            for(int i=0; i<bmpOut.getWidth()/10; i++){
                System.out.print("@");
            }
            System.out.println();
            */
        }catch(Exception e){
            e.printStackTrace();
        }
        g2d.dispose();
        return bmpOut;
    }


    public int[][] getSamples(BufferedImage image){
        int width=fm.charWidth(' ');
        int height=fm.getAscent()-2;

        int[][] samples=new int[image.getWidth()/width][9];


        for(int i=0; i<samples.length; i++){//each char
            for(int j=0; j<width; j++){//x pixel
                for(int k=0; k<height; k++){//y pixel
                    int dimension = 3*Math.min(k/(height/3),2)+Math.min(j/(width/3),2);
                    if(image.getRGB(i*width+j,k)==-1){
                        samples[i][dimension]++;
                    }
                }
            }
            //samples[i]=normalizeVector(samples[i]);
            //samples[i]=normalizeVector(samples[i],getMaxMagnitude(samples));
            samples[i]=localContrastNormalizeVector(samples[i]);
        }
        return samples;
    }


    public int[] normalizeVector(int[] v){
        double magnitude=0;
        for(int d:v){
            magnitude+=Math.pow(d,2);
        }
        magnitude=Math.sqrt(magnitude);
        if(magnitude!=0){
            for(int i=0; i<9; i++){
                v[i]=((4*v[i])/(int)magnitude);
            }
        }
        return v;
    }

    public int[] normalizeVector(int[] v,double magnitude){
        if(magnitude!=0){
            for(int i=0; i<9; i++){
                v[i]=((4*v[i])/(int)magnitude);
            }
        }
        return v;
    }


    public double getMaxMagnitude(int[][] v){
        int m=0;
        int n=0;
        for(int[] vec:v){
            for(int d:vec){
                n+=(int)Math.pow(d,2);
            }
            if(m<n){
                m=n;
            }
        }
        return Math.sqrt(m);
    }

    public int[] localContrastNormalizeVector(int[] v){
        int min=0;
        int max=5;

        for(int i=0; i<9; i++){
            min=(v[i]>min)?min:v[i];
            max=(v[i]<max)?max:v[i];
        }

        for(int i=0; i<9; i++){
            v[i]=(3*(v[i]-min))/(max-min);
        }
        return v;
    }

}

