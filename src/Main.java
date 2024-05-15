import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;


public class Main {


    public static void main(String[] args) {



        JFrame frame = new JFrame();
        MyPanel panel = new MyPanel();


        frame.setSize(1600,900);
        frame.add(panel);
        frame.setVisible(true);


        File soundFile = new File("C:\\Users\\Peter\\IdeaProjects\\funnygame3\\.idea\\music\\y2mate.com - DM DOKURO  9.wav");

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

                clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the sound continuously

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }


    }//end of main method
}