package com.sample.JavaEngine.level.tile;

import com.sample.JavaEngine.graphics.Screen;
import com.sample.JavaEngine.graphics.Sprite;

public class Tile {

    public static Tile voidTile = new VoidTile(Sprite.voidSprite);
    public int x, y;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void update() {

    }

    public void render(int x, int y, Screen screen) {

    }

    public boolean solid() {
        return false;
    }
}
