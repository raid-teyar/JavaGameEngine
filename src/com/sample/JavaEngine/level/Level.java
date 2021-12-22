package com.sample.JavaEngine.level;

import com.sample.JavaEngine.graphics.Screen;

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

    }
}
