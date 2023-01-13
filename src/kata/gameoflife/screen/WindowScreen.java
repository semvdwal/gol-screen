package kata.gameoflife.screen;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;

/**
 * A window which displays Screen data
 */
public class WindowScreen extends JFrame implements Screen {

  private TiledCanvas canvas;
  private TileClickedListener tileClickedListener;

  /**
   * Creates a new Window
   * @param title The title of the window
   * @throws HeadlessException When java is running in console mode (no access to window manager)
   */
  public WindowScreen(String title) throws HeadlessException {
    this(title, 10, 10);
  }

  public WindowScreen(String title, int columns, int rows) throws HeadlessException {
    this(title, columns, rows, new TileClickedListener() {
      @Override
      public void onClicked(int row, int column) {
        System.out.println("Tile clicked, row: " + row + ", column: " + column);
      }
    });
  }

  /**
   * Creates a new Window
   * @param title The title of the window
   * @param columns The number of columns in the game
   * @param rows The number of rows in the game
   * @throws HeadlessException When java is running in console mode (no access to window manager)
   */
  public WindowScreen(String title, int columns, int rows, TileClickedListener tileClickedListener) throws HeadlessException {
    super(title);
    canvas = new TiledCanvas(columns, rows);
    add(canvas);
    setSize(600, 500);
    setVisible(true);

    this.tileClickedListener = tileClickedListener;
    canvas.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        tileClickedListener.onClicked(canvas.getRowNumber(e.getY()), canvas.getColumnNumber(e.getX()));
      }

      @Override
      public void mousePressed(MouseEvent e) {

      }

      @Override
      public void mouseReleased(MouseEvent e) {

      }

      @Override
      public void mouseEntered(MouseEvent e) {

      }

      @Override
      public void mouseExited(MouseEvent e) {

      }
    });

    Map<Integer, Color> defaultColors = new HashMap<>();
    defaultColors.put(-1, Color.BLACK);
    defaultColors.put(0, Color.WHITE);
    defaultColors.put(1, Color.GREEN);
    setColors(defaultColors);
  }

  /**
   * Set which colors to use when drawing tiles on the canvas
   * @param colors
   */
  public void setColors(Map<Integer, Color> colors) {
    canvas.setColorMap(colors);
  }

  @Override
  public void draw(List<List<Integer>> data) {

    for(int r=0; r < data.size(); r++) {
      List<Integer> column = data.get(r);
      for(int c=0; c < column.size(); c++) {
        canvas.setCell(c, r, column.get(c));
      }
    }
    canvas.repaint();

  }

}
