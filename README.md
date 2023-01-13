## Game of life kata

See: https://kata-log.rocks/game-of-life-kata

This project provides a rudimentary api for displaying a window with a number of tiles. Use the Screen interface within
your main game loop and call the draw method to update the screen for every new cycle in the game of life.

The size of the grid should be given when using the WindowScreen implementation, and should have a correct setting which is also used when calling the draw method (data should not exceed grid size). Tiles outside of bounds will not be drawn.

The number of possible values are arbitrary, if using the WindowScreen you will get by default colors for -1, 0 and 1, but you can set any values with associated colors you wish with the setColors method.