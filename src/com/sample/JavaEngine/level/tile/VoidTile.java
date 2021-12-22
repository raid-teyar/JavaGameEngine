package com.sample.JavaEngine.level.tile;

import com.sample.JavaEngine.graphics.Screen;
import com.sample.JavaEngine.graphics.Sprite;

public class VoidTile extends Tile {
    public VoidTile(Sprite voidSprite) {
        super( voidSprite );
    }

    public void render(int x, int y, Screen screen) {
        //do render stuff here!
        screen.renderTile(x << 4, y << 4, this);
    }
}
