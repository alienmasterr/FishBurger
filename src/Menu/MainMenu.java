package Menu;

import Elements.Node;
import Enums.FrameState;
import Menu.MenuElements.SoundPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;
import javax.sound.sampled.*;


public class MainMenu extends JPanel {
    private Clip clip;
    private Game parent;
    public MainMenu(Game parent){
        super();
        this.parent = parent;
        setStaticSize();
        setLayout(null);
        setButtons();
        startMusic();
    }

    public void turnOffTheMusic(){
        clip.stop();
    }

    public void startMusic(){
        if(clip != null){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            return;
        }
        File file = new File("res/Music/mainmenu.wav");
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ignored) {}
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Node mainMenu = new Node(0, 0, Game.WIDTH, Game.HEIGHT);
        mainMenu.getImage("/mainmenu/mainmenu.png");
        mainMenu.draw(g2d);
    }

    private void setStaticSize(){
        setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMinimumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
    }

    private void setButtons(){
        setPlayButton();
        setTutorialButton();
        setExitButton();
    }
    private void setPlayButton(){
        EmptyButton playButton = new EmptyButton(FrameState.CHOOSE_LEVEL);
        playButton.setBounds(377, 471, 235, 75);
        add(playButton);
    }

    private void setTutorialButton(){
        EmptyButton tutorialButton = new EmptyButton(FrameState.TUTORIAL);
        tutorialButton.setBounds(377, 471+92, 235, 75);
        add(tutorialButton);
    }

    private void setExitButton() {
        EmptyButton exitButton = new EmptyButton(FrameState.EXIT);
        exitButton.setBounds(377, 471+92*2, 235, 75);
        add(exitButton);
    }

    private class EmptyButton extends JButton{
        private FrameState state;
        private boolean active = true;
        public EmptyButton(FrameState state){
            super();
            this.state = state;
            hideVisibility();
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!active)
                        return;
                    SoundPlayer.playButtonSound();
                    parent.setVisiblePanel(state);
                    turnOffTheMusic();
                }
            });
        }

        public void toggle(){
            this.active = !active;
        }

        private void hideVisibility(){
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
        }
    }
}
