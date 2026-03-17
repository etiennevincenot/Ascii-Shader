public class ScreenBuffer{
    int w;
    int h;

    int[][] screenPixel;

    Display display;

    public ScreenBuffer(int w, int h, Display d){
        this.w=w;
        this.h=h;
        this.display = d;
        screenPixel = new int[w][h];
    }

    public void set(int x, int y, int color){
        screenPixel[x][y] = color;
    }



}
