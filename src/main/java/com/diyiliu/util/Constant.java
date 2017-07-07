package com.diyiliu.util;

/**
 * Description: Constant
 * Author: DIYILIU
 * Update: 2017-07-04 14:08
 */
public final class Constant {

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
