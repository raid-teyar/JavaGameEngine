package com.sample.JavaEngine.graphics;

public class Sprite {
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(16, 0x1B87E0);

    public Sprite( int size, int x, int y, SpriteSheet sheet ) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }

    public Sprite(int size, int color){
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    private void setColor(int color) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            pixels[i] = color;
        }
    }

    //copy sprite from sheet
    private void load(){
        for (int y = 0; y < SIZE; y++){
            System.arraycopy(sheet.pixels, (x + this.x) + (y + this.y) * sheet.SIZE, pixels, y * SIZE, SIZE);
        }
    }
}
