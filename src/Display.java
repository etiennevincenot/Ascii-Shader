public class Display{
    int w,h;


    char[][] pixelArray;

    ScreenBuffer buffer;

    public Display(int w, int h, AsciiMap map){
        this.w=(w*9)/24;
        this.h=(h*4)/24;
        pixelArray = new char[this.w][this.h];
        buffer=new ScreenBuffer(w,h,map);
        emptyArray();
    }

    public Rasterizer createRasterizer(){
        return new Rasterizer(this);
    }

    public void emptyArray(){
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

    public void clear(){
        buffer.clear(0);
    }

    public void print(){
        buffer.fillBuffer();
        loadBuffer(buffer);
        drawDisplay();
    }

    public void drawCircle(int x, int y,int radius, int color){
        buffer.drawCircle(x,y,radius,color);
    }

    public void drawRect(int x, int y,int width, int height, int color){
        buffer.drawRect(x,y,width,height,color);
    }

    public void drawTri(int x1,int y1,int x2, int y2, int x3, int y3, int color){
        buffer.drawTri(x1,y1,x2,y2,x3,y3,color);
    }

    public void drawLine(int x1, int y1, int x2, int y2, int color){
        buffer.drawLine(x1,y1,x2,y2,color);
    }

    public void drawImage(String filePath){
        buffer.drawImage(filePath);
    }
}
