import org.junit.Test;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Description: TestSound
 * Author: DIYILIU
 * Update: 2017-07-10 14:36
 */

public class TestSound {


    @Test
    public void test1() {

        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(ClassLoader.getSystemResource("music/8617.wav")));
            clip.start();

            clip.setLoopPoints(0, clip.getFrameLength() - 1);
            while (true) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2() {
        AudioInputStream ain = null;
        try {
            ain = AudioSystem.getAudioInputStream(ClassLoader.getSystemResource("music/music.mid"));

            AudioFormat format = ain.getFormat();
            SourceDataLine dataLine = AudioSystem.getSourceDataLine(format);

            dataLine.open(format);
            dataLine.start();

            int length;
            byte[] buf = new byte[512];
            while ((length = ain.read(buf)) > -1) {

                dataLine.write(buf, 0, length);
            }

            dataLine.drain();
            dataLine.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ain != null) {
                try {
                    ain.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
