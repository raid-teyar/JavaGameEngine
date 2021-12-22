package com.sample.JavaEngine;

import com.sample.JavaEngine.graphics.Screen;
import com.sample.JavaEngine.inputs.Keyboard;
import com.sample.JavaEngine.level.Level;
import com.sample.JavaEngine.level.RandomLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.Serial;

public class Game extends Canvas implements Runnable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static int width = 300;
    private static int height = width / 16 * 9;
    private static int scale = 3;


    //creating the image we're going to edit pixels on
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    private Thread thread;
    public boolean running = false;
    public JFrame frame;

    private Screen screen;
    private Keyboard keyboard;
    private Level level;

    //constructor
    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        keyboard = new Keyboard();
        level = new RandomLevel(64, 64);

        frame = new JFrame("Game");
        frame.setResizable(false);
        //adding the canvas "game" to the frame with a border layout
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //listening for key presses
        addKeyListener(keyboard);
        //start the game
        start();
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //run method
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000.0 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frame.setTitle("Game | " + updates + " updates, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    //rendering the image
    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        level.render(x, y, screen);


        //copy the pixels from the screen to the image
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    int x = 0 , y = 0;

    private void update() {
        keyboard.update();
        if (keyboard.up) y--;
        if (keyboard.down) y++;
        if (keyboard.left) x--;
        if (keyboard.right) x++;
    }

    //main method
    public static void main(String[] args) {
        new Game();
    }
}
