public class Location {
    private int row;
    private int col;


    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public int getRow() {
        return row;
    }


    public int getCol() {
        return col;
    }



    public Location deepCopy(){
        return new Location(getRow(), getCol());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Location))
            return false;

        Location otherLoc = (Location) other;
        return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
    }

    @Override
    public int hashCode(){
        return getRow() * 3737 + getCol();
    }

    @Override
    public String toString(){
        return "[" + row + ", " + col + "]";
    }
}
