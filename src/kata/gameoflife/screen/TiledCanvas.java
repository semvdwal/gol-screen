package kata.gameoflife.screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

/**
 * This class draws the game of life on a canvas
 */
class TiledCanvas extends Canvas {

  private int padding = 1;
  private int lineWidth = 2;

  private int columns;
  private int rows;

  private int[][] buffer;

  private Map<Integer, Color> colorMap;

  /**
   * Create a new canvas
   * @param columns The width in available cells to draw
   * @param rows The height in available cells to draw
   */
  TiledCanvas(int columns, int rows) {
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
   * Set new color map to be used when drawing tiles
   * @param colorMap
   */
  void setColorMap(Map<Integer, Color> colorMap) {
    this.colorMap = colorMap;
  }

  /**
   * Set a new value for a single cell
   * @param column The column (x) position of the cell
   * @param row The row (y) position of the cell
   * @param value The value of the cell: 0 - EMPTY, -1 - DEAD, 1 - ALIVE
   */
  void setCell(int column, int row, int value) {
    if(column < columns && row < rows) {
      buffer[column][row] = value;
    }
  }

  int getRowNumber(int y) {
    return (y - padding - lineWidth - padding) / (cellHeight() + padding);
  }

  int getColumnNumber(int x) {
    return (x - padding - lineWidth - padding) / (cellWidth() + padding);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    clear(g);
    drawBorder(g);
    drawCells(g);
  }

  @Override
  public void update(Graphics g) {
    super.update(g);
  }

  private void clear(Graphics g) {
    g.setPaintMode();
    g.setColor(Color.GRAY);
    g.fillRect(0,0, getSize().width, getSize().height);
  }

  private void drawCells(Graphics g) {
    for (int c = 0; c < columns; c++) {
      for (int r = 0; r < rows; r++) {
        drawCell(g, c, r, buffer[c][r]);
      }
    }
  }

  private void drawCell(Graphics g, int c, int r, int value) {
    int x = cellPosX(c);
    int y = cellPosY(r);
    Color color = colorMap.getOrDefault(value, Color.GRAY);
    drawSquare(g, x, y, x + cellWidth(), y + cellHeight(), color);
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
