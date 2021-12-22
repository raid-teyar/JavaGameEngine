package com.sample.JavaEngine.entity;

import com.sample.JavaEngine.graphics.Screen;
import com.sample.JavaEngine.level.Level;

import java.util.Random;

public class Entity {
    public int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();


    public void update() {

    }

    public void render(Screen screen) {

    }

    public void remove() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
