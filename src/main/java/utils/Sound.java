package utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class Sound {
    private MediaPlayer mediaPlayer;
    private int soundNumber;

    private static final URL[] soundtracks = new URL[7];

    static {
        //sample
        //soundtracks[0] = Sound.class.getResource("assets/media/sth.mp3");
    }

    public Sound() {

    }

    public Sound(int i) {
        setFile(i);
    }

    public void setFile(int i) {
        Media media = new Media(soundtracks[i].toString());
        mediaPlayer = new MediaPlayer(media);
        soundNumber = i;
    }

    public void play() {
        mediaPlayer.play();
    }

    public void loop() {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public int getSoundNumber() {
        return soundNumber;
    }
}
