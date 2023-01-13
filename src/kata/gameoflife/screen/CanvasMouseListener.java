package kata.gameoflife.screen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class CanvasMouseListener implements MouseListener {

  private ScreenListener screenListener;
  private TiledCanvas canvas;

  public CanvasMouseListener(ScreenListener screenListener, TiledCanvas canvas) {
    this.screenListener = screenListener;
    this.canvas = canvas;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    screenListener.onTileClicked(canvas.getRowNumber(e.getY()), canvas.getColumnNumber(e.getX()));
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

}
