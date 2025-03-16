package org.example.btap_cuoi_khoa_1.view;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;


public class AlarmSound {
    private static MediaPlayer mediaPlayer;
    public static void playAlarmSound() {
        Media media = new Media(new File("src/main/resources/audio/sound_cut.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.8);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
    }

    public static void stopAlarmSound() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
