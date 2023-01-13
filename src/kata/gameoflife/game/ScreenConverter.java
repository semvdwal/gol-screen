package kata.gameoflife.game;

import java.util.ArrayList;
import java.util.List;

public class ScreenConverter {

  private final int rows;
  private final int columns;

  public ScreenConverter(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
  }

  public List<List<Integer>> convert(List<Cell> cells) {
    List<List<Integer>> data = new ArrayList<>(rows);
    for(int i=0; i < rows; i++) {
      List<Integer> row = new ArrayList<>(columns);
      for(int j=0; j < columns; j++) {
        row.add(0);
      }
      data.add(row);
    }
    for (Cell cell : cells) {
      data.get(cell.getRow()).set(cell.getColumn(), cell.isAlive() ? 1 : 0);
    }
    return data;
  }

}
