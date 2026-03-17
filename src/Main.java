public class Main{
    public static void main(String[] args){









        BrightnessMap bm = new BrightnessMap("bmp/JetBrainsMono.bmp",'!'+'"'+"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~");


        int[] size = bm.getCharSize();
        Display display = new Display(150,size[1]);


        display.clearDisplay();
        for(int j=0; j<150; j++){
            if(j%(size[0])==0){
                System.out.print(j%(size[0]));
            }else{
                System.out.print(" ");
            }
        }
        System.out.println();
        char c = '0';
        for(int i=bm.charToGlyph(c)[1]; i<bm.charToGlyph(c)[1]+bm.getCharSize()[1]; i++){
            for(int j=bm.charToGlyph(c)[0]; j<bm.charToGlyph(c)[0]+2*bm.getCharSize()[0]; j++){
                if(bm.pixelOn(j-2,i))display.setPixel(j-bm.charToGlyph(c)[0],i-bm.charToGlyph(c)[1],'#');
            }
        }



        display.drawDisplay();

        Vector9D vec = bm.getCharGlyphInkDensity(c);



    }
}