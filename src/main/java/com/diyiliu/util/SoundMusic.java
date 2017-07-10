package com.diyiliu.util;

import com.diyiliu.thread.MusicPlayer;

/**
 * Description: SoundMusic
 * Author: DIYILIU
 * Update: 2017-07-10 17:18
 */
public class SoundMusic {


    public static void buildPreludeMusic(){

        new MusicPlayer(ClassLoader.getSystemResource("music/5170.wav").getPath()).start();
    }

    public static void buildBackgroudMusic(){

        MusicPlayer background = new MusicPlayer(ClassLoader.getSystemResource("music/background.mid").getPath(), true);
        background.setDelay(5000);
        background.start();
    }

    public static void buildHitMusic(){

        new MusicPlayer(ClassLoader.getSystemResource("music/explode.wav").getPath()).start();
    }

    public static void buildWinMusic(){

        new MusicPlayer(ClassLoader.getSystemResource("music/levelup.wav").getPath()).start();
    }

    public static void buildLoseMusic(){

        new MusicPlayer(ClassLoader.getSystemResource("music/over.wav").getPath()).start();
    }
}
