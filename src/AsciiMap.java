public class AsciiMap{

    char[] instructionLookUp = new char[262144];//all possible lookup table inputs

    public AsciiMap(){

    }

    //packs 9D vectors into an 18-bit int value
    public int packVec(int[] v){
        int vec=v[0];
        for(int i=1; i<9; i++){
            vec<<=2;
            vec+=v[i];
        }
        return vec;
    }

    //unpacks 18-bit int value into a 9D vector (reversed)
    public int[] unPackVec(int v){
        int[] vec=new int[9];
        for(int i=8; i>=0; i--){
            vec[i]=v&3;
            v>>=2;
        }
        return vec;
    }

    //gives us distance squared in 9 dimensional euclidean space (avoids sqrt)
    private int getDistanceSquared(int[] pointa, int[] pointb){
        int distance=0;
        for(int i=0; i<9; i++) {
            distance+=(int)Math.pow(pointa[i]-pointb[i],2);
        }
        return distance;
    }

    //uses distanceSquared to find the closest reference point in 9 dimensional space
    private int findClosest(int[] v,int[][] masterVec){
        int index=0;
        int closest=Integer.MAX_VALUE;
        int dist;
        for(int i=0; i<masterVec.length; i++){
            dist=getDistanceSquared(v,masterVec[i]);
            if(dist<closest){
                closest=dist;
                index=i;
            }
        }
        return index;
    }

    //unpacks i at every possible index, into a 9D vector which finds its closest master vector
    public void fillMapToClosestChar(int[][] masterVec,String charValues){
        for(int i=0; i<instructionLookUp.length; i++){
            instructionLookUp[i]=charValues.charAt(findClosest(unPackVec(i),masterVec));
        }
    }

    //packs 9D vector into index, fetches char from LUT at index
    public char getChar(int[] v){
        return instructionLookUp[packVec(v)];
    }
}
