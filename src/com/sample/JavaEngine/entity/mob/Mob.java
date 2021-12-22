package com.sample.JavaEngine.entity.mob;

import com.sample.JavaEngine.entity.Entity;
import com.sample.JavaEngine.level.Level;
import com.sample.JavaEngine.level.tile.VoidTile;

public abstract class Mob extends Entity {

    protected String sprite;
    protected int dir = 0;
    protected boolean moving = false;


    public void move( int xa, int ya ) {

    }

    public void render(){

    }

    public void update() {

    }

    private boolean collision(){
        return false;
    }
}
