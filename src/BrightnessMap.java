import javax.imageio.ImageIO;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;


public class BrightnessMap{

    HashMap<Vector9D, Character> map = new HashMap<>();
    BufferedImage fontMap = null;
    String charMapping;

    public BrightnessMap(String filePath, String mapping){

        charMapping = mapping;

        try{
            File fontBmp = new File(filePath);
            fontMap = ImageIO.read(fontBmp);
            if(fontMap==null){
                System.out.println("File does not exist");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public BrightnessMap(BufferedImage image, String mapping){

        charMapping = mapping;
        fontMap = image;
        if(fontMap==null){
            System.out.println("File does not exist");
        }
    }

    public char getChar(Vector9D vec9){
        return map.get(vec9);
    }


    public boolean pixelOn(int x, int y){
        return fontMap.getRGB(x,y)!=0;
    }

    /// returns the first glyphs x and y pixel
    public int[] getStartingPixel(){
        for(int i=0; i<fontMap.getHeight(); i++){
            for(int j=0; j<fontMap.getWidth(); j++){
                if(pixelOn(j,i)){
                    return new int[]{j,i};
                }
            }
        }
        return new int[0];
    }

    /// returns the glyph char size in pixel width and height
    public int[] getCharSize(){

        int[] start = getStartingPixel();

        int w=0;
        int h=0;

        //width
        for(int i=start[0]; i<fontMap.getWidth(); i++){
            if(!pixelOn(i,start[1])){
                w=i-start[0];
                break;
            }
        }

        //height
        for(int i=start[1]; i<fontMap.getHeight(); i++){
            if(!pixelOn(start[0],i)){
                h=i-start[1];
                break;
            }
        }
        return new int[]{w+2,h};
    }

    /// returns the spacing in between each glyph char in pixel width and height
    public int[] findCharSpacing(){

        int[] start = getStartingPixel();
        int[] size = getCharSize();

        start[0]+=size[0];
        start[1]+=size[1];

        int w=0;
        int h=0;

        //width
        for(int i=start[0]; i<fontMap.getWidth(); i++){
            if(fontMap.getRGB(i,start[1])==0){
                w=i-start[0];
                break;
            }
        }


        //height
        for(int i=start[1]; i<fontMap.getHeight(); i++){
            if(fontMap.getRGB(start[0],i)==0){
                h=i-start[1];
                break;
            }
        }
        return new int[]{w,h};
    }

    /// returns a glyphs starting pixel x and y, from its char input
    public int[] charToGlyph(char c){

        int index = charMapping.indexOf(c)+1;
        //plus one as to not include the sample char

        int nestedCounter=0;

        int[] space = getStartingPixel();
        int[] size = getCharSize();
        int[] displacement = new int[]{space[0]+size[0],space[1]+size[1]};


        for(int i=0; i<fontMap.getHeight(); i+=displacement[1]){
            for(int j = 0; j<fontMap.getWidth(); j+=displacement[0]){
                if(nestedCounter==index){
                    return new int[]{j,i};
                }
                nestedCounter++;
            }
        }
        return new int[0];
    }

    /// returns a 9 dimensional vector that corresponds with a given char
    public Vector9D getCharGlyphInkDensity(char c){
        int[] start = charToGlyph(c);
        int[] size = getCharSize();
        int[] subsize = new int[]{size[0]/3,size[1]/3};

        int nestedCounter=0;

        int[] vec9 = new int[9];

        for(int i=0; i<3; i++){
            int y=start[1]+(subsize[1]*i);
            //starts at glyph start and subsection Y

            for(int j=0; j<3; j++){
                int x=start[0]+(subsize[0]*j);
                //starts at glyph start and subsection X

                for(int k=0; k<subsize[1]; k++){
                    //from start to range in Y
                    for(int l=0; l<subsize[0]; l++){
                        //from start to range in X
                        vec9[nestedCounter]+=(fontMap.getRGB(x+l,y+k)==0)?0:1;
                    }
                }

                System.out.print(vec9[nestedCounter]+"    ");
                nestedCounter++;
            }
            System.out.println();
        }

        float[] avg = new float[9];
        int subArea = subsize[0]*subsize[1];
        for(int i=0; i<9; i++){
            avg[i]=(float)vec9[i]/subArea;
        }


        return new Vector9D(avg);
    }





}
