package com.diyiliu.model;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Description: Bomb
 * Author: DIYILIU
 * Update: 2017-07-05 14:54
 */
public class Bomb {

    private int x;
    private int y;

    private Queue<ImageIcon> queue = new LinkedList();

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;

        ImageIcon bombImg1 = new ImageIcon(ClassLoader.getSystemResource("bomb-1.png"));
        ImageIcon bombImg21 = new ImageIcon(ClassLoader.getSystemResource("bomb-21.png"));
        ImageIcon bombImg22 = new ImageIcon(ClassLoader.getSystemResource("bomb-22.png"));
        ImageIcon bombImg3 = new ImageIcon(ClassLoader.getSystemResource("bomb-3.png"));

        queue.add(bombImg1);
        queue.add(bombImg21);
        queue.add(bombImg22);
        queue.add(bombImg3);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }
}
