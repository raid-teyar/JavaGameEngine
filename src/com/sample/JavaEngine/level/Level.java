package com.sample.JavaEngine.level;

import com.sample.JavaEngine.graphics.Screen;
import com.sample.JavaEngine.level.tile.Tile;

public class Level {
    protected int width, height;
    protected int[] tiles;

    public Level(int width, int height ){
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateRandomLevel();
    }

    public Level(String path){
        loadLevel(path);
    }

    private void loadLevel(String path) {

    }

    protected void generateRandomLevel() {

    }

    public void update(){

    }

    private void time(int time ){

    }

    public void render( int xScroll, int yScroll, Screen screen ){
        screen.setOffset(xScroll, yScroll);
        //corner pins
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 15) >> 4;
        //render
        for (int y = y0; y < y1; y++){
            for (int x = x0; x < x1; x++){
                getTile(x, y).render(x, y, screen);
            }
        }
    }

    public void setTile(int x, int y, int tile){
        if(x < 0 || y < 0 || x >= width || y >= height) return;
        tiles[x + y * width] = tile;
    }

    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        if (tiles[x + y * width] == 0) return Tile.grass;
        return Tile.voidTile;
    }
}
