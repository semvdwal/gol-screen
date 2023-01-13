package kata.gameoflife.screen;

/**
 * An interface to use when listening to {@link Screen} events
 */
public interface ScreenListener {

  /**
   * Called when a tile is clicked
   * @param row The row number of the clicked tile
   * @param column The column number of the clicked tile
   */
  void onTileClicked(int row, int column);

  /**
   * Called when the start / stop button is clicked
   */
  void onStartStopClicked();

}
