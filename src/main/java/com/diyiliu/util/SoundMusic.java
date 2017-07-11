package com.diyiliu.util;

import com.diyiliu.thread.MusicPlayer;

/**
 * Description: SoundMusic
 * Author: DIYILIU
 * Update: 2017-07-10 17:18
 */
public class SoundMusic {

    private static MusicPlayer backgroundMusic;


    public static void buildPreludeMusic(){

        new MusicPlayer(ClassLoader.getSystemResource("music/5170.wav")).start();
    }

    public static void buildBackgroundMusic(){

        if (backgroundMusic == null || !backgroundMusic.isAlive()){
            backgroundMusic =  new MusicPlayer(ClassLoader.getSystemResource("music/background.mid"), true);
            backgroundMusic.setDelay(5000);
            backgroundMusic.start();
        }
    }

    public static void buildHitMusic(){

        new MusicPlayer(ClassLoader.getSystemResource("music/explode.wav")).start();
    }

    public static void buildWinMusic(){

        new MusicPlayer(ClassLoader.getSystemResource("music/levelup.wav")).start();
        backgroundMusic.quit();
    }

    public static void buildLoseMusic(){

        new MusicPlayer(ClassLoader.getSystemResource("music/over.wav")).start();
        backgroundMusic.quit();
    }
}
