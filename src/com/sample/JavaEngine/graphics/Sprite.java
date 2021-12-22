package com.sample.JavaEngine.graphics;

public class Sprite {
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);

    public Sprite( int size, int x, int y, SpriteSheet sheet ) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }

    //copy sprite from sheet
    private void load(){
        for (int y = 0; y < SIZE; y++){
            System.arraycopy(sheet.pixels, (x + this.x) + (y + this.y) * sheet.SIZE, pixels, y * SIZE, SIZE);
        }
    }
}
