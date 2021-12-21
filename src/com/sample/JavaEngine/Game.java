package com.sample.JavaEngine;

import com.sample.JavaEngine.graphics.Screen;

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

    private Screen screen;

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);

        JFrame frame = new JFrame("Game");
        frame.setResizable(false);
        //adding the canvas "game" to the frame with a border layout
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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

    @Override
    public void run() {
        while (running) {
            //update
            update();
            //render
            render();
        }
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.render();

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.dispose();
        bs.show();
    }

    private void update() {
    }

    public static void main(String[] args) {
        new Game();
    }
}
