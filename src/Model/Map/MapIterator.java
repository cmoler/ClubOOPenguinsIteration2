package Model.Map;

public class MapIterator{

    private Map map = null;
    private int i = 0;
    private int j = 0;
    private int numRows = 0;
    private int numCols = 0;

    public MapIterator(Map map){
        this.map = map;
        numRows = map.getRows();
        numCols = map.getCols();
    }

    public Location currentItem() {
        return map.getLocationIJ(i,j);
    }

    public boolean isValid() {
        if (i >= numRows || j >= numCols) {
            return false;
        }
        return true;
    }

    public void next() {
        if (j == numCols-1)
        {
            i++;
            j = 0;
        }
        else
        {
            j++;
        }
    }

    public void reset() {
        i = 0;
        j = 0;
    }

    public int getI() {
        return i;
    }
    public int getJ(){
        return j;
    }

}



