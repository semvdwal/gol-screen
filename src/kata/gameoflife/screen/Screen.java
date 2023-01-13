package kata.gameoflife.screen;

import java.util.List;

/**
 * Represents a screen for displaying a game of life status
 */
public interface Screen {

  /**
   * Draws the given data on the raster
   * @param data The data to draw
   */
  void draw(List<List<Integer>> data);

}
