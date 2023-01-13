package kata.gameoflife.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * A window which displays Screen data
 */
public class WindowScreen extends JFrame implements Screen {

  private TiledCanvas canvas;
  private ScreenListener screenListener;
  private CanvasMouseListener mouseListener;

  /**
   * Creates a new Window
   * @param title The title of the window
   * @throws HeadlessException When java is running in console mode (no access to window manager)
   */
  public WindowScreen(String title) throws HeadlessException {
    this(title, 10, 10);
  }

  /**
   * Creates a screen with specified dimensions
   * @param title The title of the window to create
   * @param columns The number of rows to display
   * @param rows The number of columns to display
   * @throws HeadlessException When java is running in console mode (no access to window manager)
   */
  public WindowScreen(String title, int columns, int rows) throws HeadlessException {
    this(title, columns, rows, new ScreenListener() {
      @Override
      public void onTileClicked(int row, int column) {
        System.out.println("Tile clicked, row: " + row + ", column: " + column);
      }

      @Override
      public void onStartStopClicked() {
        System.out.println("Start / stop clicked");
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
  public WindowScreen(String title, int columns, int rows, ScreenListener screenListener) throws HeadlessException {
    super(title);
    this.screenListener = screenListener;

    // Create and add canvas
    canvas = new TiledCanvas(columns, rows);
    mouseListener = new CanvasMouseListener(this.screenListener, canvas);
    canvas.addMouseListener(mouseListener);
    add(canvas);

    // Create and add start / stop button
    getContentPane().add(BorderLayout.SOUTH, createStartStopButton());

    // Set window size and make visible
    setSize(600, 500);
    setVisible(true);

    setColors(getDefaultColors());
  }

  /**
   * Sets the screen listener
   * @param listener A new screen listener
   */
  public void setScreenListener(ScreenListener listener) {
    canvas.removeMouseListener(mouseListener);
    screenListener = listener;
    mouseListener = new CanvasMouseListener(this.screenListener, canvas);
    canvas.addMouseListener(mouseListener);
  }

  /**
   * Set which colors to use when drawing tiles on the canvas
   * @param colors
   */
  public void setColors(Map<Integer, Color> colors) {
    canvas.setColorMap(colors);
  }

  /**
   * Draws the given data on the raster, uses colors to represent data values
   * @param data The data to draw, rows containing cells with integer values
   */
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

  private JButton createStartStopButton() {
    JButton button = new JButton();
    button.addActionListener(e -> {
      screenListener.onStartStopClicked();
      if (button.getText().equals("Start")) {
        button.setText("Stop");
      } else {
        button.setText("Start");
      }
    });
    button.setText("Start");
    return button;
  }

  private Map<Integer, Color> getDefaultColors() {
    Map<Integer, Color> defaultColors = new HashMap<>();
    defaultColors.put(-1, Color.BLACK);
    defaultColors.put(0, Color.WHITE);
    defaultColors.put(1, Color.GREEN);
    return defaultColors;
  }

}
