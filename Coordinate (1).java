public class Coordinate implements Comparable<Coordinate> {
    private int row;
    private int col;

    public Coordinate(int r, int c) {
        row = r;
        col = c;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }
    @Override
      public boolean equals(Object other)
      {
          if (other != null && other instanceof Coordinate &&
                  row == ((Coordinate)other).row &&
                  col == ((Coordinate)other).col)
                return true;
          return false;
      }

    @Override
    public int compareTo(Coordinate o) {
        if (! (o instanceof Coordinate))
			throw new IllegalArgumentException("compareTo not Coordinate object");
		if (row > ((Coordinate)o).col || row < ((Coordinate)o).row)
			return row - ((Coordinate)o).row;
		if (col > ((Coordinate)o).col || col < ((Coordinate)o).col)
			return col - ((Coordinate)o).col;
		return 0;
    }

}
