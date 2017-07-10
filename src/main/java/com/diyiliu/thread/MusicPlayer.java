package com.diyiliu.thread;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import java.io.File;
import java.io.IOException;

/**
 * Description: MusicPlayer
 * Author: DIYILIU
 * Update: 2017-07-10 15:33
 */
public class MusicPlayer extends Thread {

    private String path;
    private boolean loop = false;

    private int delay;

    private SourceDataLine dataLine;
    private AudioInputStream audioInputStream;

    public MusicPlayer(String path) {

        this.path = path;
    }

    public MusicPlayer(String path, boolean loop) {

        this.path = path;
        this.loop = loop;
    }


    @Override
    public void run() {

        if (delay > 0) {

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        do {
            play();
        } while (loop);
    }

    /**
     * 开始
     */
    public void play() {

        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(path));
            AudioFormat audioFormat = audioInputStream.getFormat();

            dataLine = AudioSystem.getSourceDataLine(audioFormat);
            dataLine.open(audioFormat);

            dataLine.start();
            int length;
            byte[] buf = new byte[512];

            try {
                while ((length = audioInputStream.read(buf)) > -1) {

                    dataLine.write(buf, 0, length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            dataLine.drain();
            dataLine.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (audioInputStream != null) {
                try {
                    audioInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 停止
     */
    public void quit() {

        if (dataLine != null && dataLine.isRunning()) {

            dataLine.drain();
            dataLine.close();
        }

        close();
    }

    /**
     * 重新播放
     */
    public void replay() {

        this.start();
    }

    public void close() {

        if (audioInputStream != null) {
            try {
                audioInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
