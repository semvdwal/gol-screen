package kata.gameoflife;

import kata.gameoflife.game.GameOfLife;
import kata.gameoflife.screen.ScreenListener;
import kata.gameoflife.screen.WindowScreen;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    int rows = 10;
    int columns = 10;

    WindowScreen screen = new WindowScreen("Game of life", rows, columns);
    GameOfLife game = new GameOfLife(screen, rows, columns);
    ScreenListener screenListener = new ScreenListener() {
      @Override
      public void onTileClicked(int row, int column) {
        game.toggleCell(row, column);
      }

      @Override
      public void onStartStopClicked() {
        if(game.isGameRunning()) {
          game.pauseGame();
        } else {
          game.continueGame();
        }
      }
    };
    screen.setScreenListener(screenListener);
    game.startGame();
  }

}
