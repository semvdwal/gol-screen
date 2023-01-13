package kata.gameoflife.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kata.gameoflife.screen.Screen;

public class GameOfLife {

  private final Screen screen;
  private final List<Cell> cells;
  private final ScreenConverter converter;
  private boolean gameRunning = false;

  public GameOfLife(Screen screen, int rows, int columns) {
    this.screen = screen;
    this.converter = new ScreenConverter(rows, columns);

    cells = new ArrayList<>();
    for(int r=0; r<rows; r++) {
      for(int c=0; c<columns; c++) {
        cells.add(new Cell(r, c));
      }
    }
  }

  public void startGame() throws InterruptedException {
    while(true) {
      if(gameRunning) {
        updateCells();
        screen.draw(converter.convert(cells));
      }
      Thread.sleep(250);
    }
  }

  public boolean isGameRunning() {
    return gameRunning;
  }

  public void pauseGame() {
    gameRunning = false;
  }

  public void continueGame() {
    gameRunning = true;
  }

  public void toggleCell(int row, int column) {
    cells.stream().filter(c -> c.getRow() == row && c.getColumn() == column).findFirst().ifPresent(cell -> {
      cell.setAlive(!cell.isAlive());
      cell.nextTick();
    });
    screen.draw(converter.convert(cells));
  }

  private void updateCells() {
    for (Cell cell : cells) {
      List<Cell> neighbours = getNeighbours(cell);
      long aliveCount = neighbours.stream().filter(Cell::isAlive).count();
      if(cell.isAlive()) {
        if(aliveCount <= 1 || aliveCount >= 4) cell.setAlive(false);
      } else {
        if(aliveCount == 3) cell.setAlive(true);
      }
    }
    for (Cell cell : cells) {
      cell.nextTick();
    }
  }

  private List<Cell> getNeighbours(Cell cell) {
    return cells.stream().filter(candidate -> isNeighbour(cell, candidate)).collect(Collectors.toList());
  }

  private boolean isNeighbour(Cell origin, Cell candidate) {
    int dx = candidate.getColumn() - origin.getColumn();
    int dy = candidate.getRow() - origin.getRow();

    if(origin.getRow() == candidate.getRow() && origin.getColumn() == candidate.getColumn()) return false;
    return dx >= -1 && dx <= 1 && dy >= -1 && dy <= 1;
  }

}
