package com.diyiliu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Description: Constant
 * Author: DIYILIU
 * Update: 2017-07-04 14:08
 */
public final class Constant {

    // 当前状态
    public static boolean STATE = false;

    // 等级信息
    public final static ConcurrentLinkedQueue LEVEL_QUEUE = new ConcurrentLinkedQueue();

    static {
       initData();
    }

    /**
     * 装载配置
     */
    public static void initData(){
        Properties config = new Properties();
        InputStream in = null;
        try {
            in = ClassLoader.getSystemResourceAsStream("config.properties");
            config.load(in);

            String stage1 = config.getProperty("stage1");
            String stage2 = config.getProperty("stage2");
            String stage3 = config.getProperty("stage3");

            LEVEL_QUEUE.add(stage1.split(","));
            LEVEL_QUEUE.add(stage2.split(","));
            LEVEL_QUEUE.add(stage3.split(","));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public enum Config {
        ;
        public final static int FRAME_WIDTH = 800;
        public final static int FRAME_HEIGHT = 600;

        public final static int PANEL_WIDTH = 600;
        public final static int PANEL_HEIGHT = 600;

        public final static int DOWN_OFFSET = 90;
        public final static int RIGHT_OFFSET = 35;
    }

    public enum Command {
        ;
        public final static String START = "S";
        public final static String PAUSE = "P";
        public final static String EXIT = "E";
    }

    public enum Direct {
        ;
        public final static int DIRECT_UP = 0;
        public final static int DIRECT_LEFT = 1;
        public final static int DIRECT_DOWN = 2;
        public final static int DIRECT_RIGHT = 3;
    }


    public enum Army {
        ;
        public final static int ARMY_HERO = 1;
        public final static int ARMY_ENEMY = 0;
    }

}
