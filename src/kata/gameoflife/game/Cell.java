package kata.gameoflife.game;

public class Cell {

  private final int row;
  private final int column;
  private boolean alive = false;
  private boolean aliveNextTick = false;

  public Cell(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.aliveNextTick = alive;
  }

  public void nextTick() {
    alive = aliveNextTick;
  }

}
