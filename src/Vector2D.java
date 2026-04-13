public class Vector2D{
    double x;
    double y;

    public Vector2D(double x, double y){
        this.x=x;
        this.y=y;
    }

    public static double dotProduct2D(Vector2D a, Vector2D b){
        Vector2D normalizeda=a.getUnitVector();
        Vector2D normalizedb=b.getUnitVector();
        return normalizeda.x*normalizedb.x+normalizeda.y*normalizedb.y;
    };

    public static Vector2D perpendicular(Vector2D a){
        return new Vector2D(-1/a.x,-1/a.y);
    };

    public double getMagnitude(){
        return Math.sqrt(x*x+y*y);
    }

    public Vector2D getUnitVector(){
        double mag=getMagnitude();
        if(mag==0){
            return new Vector2D(0,0);
        }
        return new Vector2D(x/mag,y/mag);
    }

    public static Vector2D add(Vector2D a, Vector2D b){
        return new Vector2D(a.x+b.x,a.y+b.y);
    }

    public static Vector2D subtract(Vector2D a, Vector2D b){
        return new Vector2D(a.x-b.x,a.y-b.y);
    }



}
