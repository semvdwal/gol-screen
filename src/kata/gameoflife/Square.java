package kata.gameoflife;

import kata.gameoflife.Creature;

public interface Square {

  boolean hasCreature();
  Creature getCreature();

}
