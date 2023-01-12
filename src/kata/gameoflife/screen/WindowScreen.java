package kata.gameoflife.screen;

import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JFrame;
import kata.gameoflife.Row;
import kata.gameoflife.Square;

/**
 * A window which displays Screen data
 */
public class WindowScreen extends JFrame implements Screen {

  private GameOfLifeCanvas canvas;

  /**
   * Creates a new Window
   * @param title The title of the window
   * @throws HeadlessException When java is running in console mode (no access to window manager)
   */
  public WindowScreen(String title) throws HeadlessException {
    this(title, 10, 10);
  }

  /**
   * Creates a new Window
   * @param title The title of the window
   * @param columns The number of columns in the game
   * @param rows The number of rows in the game
   * @throws HeadlessException When java is running in console mode (no access to window manager)
   */
  public WindowScreen(String title, int columns, int rows) throws HeadlessException {
    super(title);
    canvas = new GameOfLifeCanvas(columns, rows);
    add(canvas);
    setSize(600, 500);
    setVisible(true);
  }

  @Override
  public void draw(List<Row> rows) {

    for(int l=0; l < rows.size(); l++) {
      Row row = rows.get(l);
      for(int s=0; s < row.getSquares().size(); s++) {
        Square square = row.getSquares().get(s);
        canvas.setCell(s, l, square.hasCreature() ? square.getCreature().isAlive() ? -1 : 1 : 0);
      }
    }
    canvas.repaint();

  }

}
