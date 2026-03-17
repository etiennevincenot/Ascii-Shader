public class upscaler{
    public upscaler(){


    }


    public static void applyUpscale3x3(ScreenBuffer buffer, Display display){

        int w = buffer.w/3;
        int h = buffer.h/3;



        //9D vector to plug into hashmap for my font bitmap weights to convert to Chars.

        double[][][] vector9D = new double[w][h][9];



        display.setPixel(3,3,' ');



    }
}
