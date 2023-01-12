package kata.gameoflife.screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 * This class draws the game of life on a canvas
 */
public class GameOfLifeCanvas extends Canvas {

  private int padding = 5;
  private int lineWidth = 5;

  private int columns;
  private int rows;

  int[][] buffer;

  /**
   * Create a new canvas
   * @param columns The width in available cells to draw
   * @param rows The height in available cells to draw
   */
  public GameOfLifeCanvas(int columns, int rows) {
    this.columns = columns;
    this.rows = rows;

    // Initialize buffer
    buffer = new int[columns][rows];
    for (int c = 0; c < columns; c++) {
      for (int r = 0; r < rows; r++) {
        buffer[c][r] = 0;
      }
    }
  }

  /**
   * Set a new value for a single cell
   * @param column The column (x) position of the cell
   * @param row The row (y) position of the cell
   * @param value The value of the cell: 0 - EMPTY, -1 - DEAD, 1 - ALIVE
   */
  public void setCell(int column, int row, int value) {
    buffer[column][row] = value;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    System.out.println("paint");
    clear(g);
    drawBorder(g);
  }

  @Override
  public void update(Graphics g) {
    super.update(g);
    System.out.println("update");
    for (int c = 0; c < columns; c++) {
      for (int r = 0; r < rows; r++) {
        drawCell(g, c, r, buffer[c][r]);
      }
    }
  }

  private void clear(Graphics g) {
    g.setPaintMode();
    g.setColor(Color.GRAY);
    g.fillRect(0,0, getSize().width, getSize().height);
  }

  private void drawCell(Graphics g, int c, int r, int value) {
    int x = cellPosX(c);
    int y = cellPosY(r);
    // TODO: Investigate why these borders are acting weird
//    drawBorder(g, x, y, x + cellWidth(), y + cellHeight(), 2, new Color(0, 0, 0, 100));
    drawSquare(g, x + 2, y + 2, x + cellWidth() - 2, y + cellHeight() - 2, value < 0 ? Color.BLACK : value > 0 ? Color.GREEN : Color.WHITE);
  }

  private int cellPosX(int c) {
    return (padding * 2) + lineWidth + (c * (cellWidth() + padding));
  }

  private int cellPosY(int r) {
    return (padding * 2) + lineWidth + (r * (cellHeight() + padding));
  }

  private int cellHeight() {
    return (getSize().height - ((padding + lineWidth) * 2) - padding - ((rows - 1) * padding)) / rows;
  }

  private int cellWidth() {
    return (getSize().width - ((padding + lineWidth) * 2) - padding - ((columns - 1) * padding)) / columns;
  }

  private void drawBorder(Graphics g) {

    drawBorder(g, padding, padding, getSize().width - padding, getSize().height - padding, lineWidth, Color.BLACK);

  }

  private void drawBorder(Graphics g, int x1, int y1, int x2, int y2, int lineWidth, Color color) {
    g.setColor(color);
    for (int i = 0; i < lineWidth; i++) {
      int top = x1 + i;
      int right = x2 - i;
      int bottom = y2 - i;
      int left = y1 + i;

      g.drawLine(left, top, right, top);
      g.drawLine(right, top, right, bottom);
      g.drawLine(right, bottom, left, bottom);
      g.drawLine(left, bottom, left, top);
      g.drawRect(x1, y1, x2 - x1, y2 - y1);
    }
  }

  private void drawSquare(Graphics graphics, int x1, int y1, int x2, int y2, Color color) {
    graphics.setColor(color);
    graphics.fillRect(x1, y1, x2 - x1, y2 - y1);
  }

}
