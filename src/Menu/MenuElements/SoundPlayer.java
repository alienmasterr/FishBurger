package Menu.MenuElements;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundPlayer {

    public static void playDeathScream() {
        playMusic("res/Music/deathscream.wav");
    }
    public static void playHappySound() {
        playMusic("res/Music/happy.wav");
    }
    public static void playAngrySound() {
        playMusic("res/Music/angry.wav");
    }
    public static void playCoinSound() {
        playMusic("res/Music/coin.wav");
    }
    public static void playGameOverSound() {
        playMusic("res/Music/gameover.wav");
    }
    public static void playPickSound() {
        playMusic("res/Music/pick.wav");
    }

    public static void playSqueezeBottleSound() {
        playMusic("res/Music/squeeze.wav");
    }

    //"res/Music/gamemenu.wav"
    public static void playButtonSound() {
        playMusic("res/Music/button.wav");
    }

    public static void playBubbleSound() {
        playMusic("res/Music/bubble.wav");
    }

    public static void playMeatGrillingSound(){playMusic("res/Music/grilling-meat.wav");}

    public static void playSomEatsMeatSound(){playMusic("res/Music/someats.wav");}

    private static void playMusic(String music) {
        File file = new File(music);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception ignored) {
        }
    }
}
