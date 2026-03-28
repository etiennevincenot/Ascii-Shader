public class Display{
    int w,h;


    char[][] pixelArray;


    public Display(int w, int h){
        this.w=w;
        this.h=h;
        pixelArray = new char[w][h];
        clearDisplay();
    }

    public void clearDisplay(){
        for(int i=0; i<w; i++){
            for(int j=0; j<h; j++) {
                pixelArray[i][j] = ' ';
            }
        }
    }

    public void drawDisplay(){

        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                System.out.print("\033[37m\033[40m"+pixelArray[j][i]);
            }

            System.out.print("\033[0m");
            System.out.println();

        }
    }


    public void setPixel(int x,int y,char c){
        pixelArray[x][y]=c;
    }


    public void loadBuffer(ScreenBuffer buffer){
        for(int i=0; i<pixelArray.length; i++){
            for(int j=0; j<pixelArray[i].length; j++){
                pixelArray[i][j]=buffer.get(i,j);
            }
        }
    }

}
