package Menu;

import Elements.Node;
import Enums.FrameState;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel {
    private Game parent;
    public MainMenu(Game parent){
        super();
        this.parent = parent;
        setStaticSize();
        setLayout(null);
        setButtons();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Node mainMenu = new Node(0, 0, Game.WIDTH, Game.HEIGHT);
        mainMenu.getImage("/mainmenu.png");
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
    //тестові фрейм тести
    //потім треба змінити фрейм стейти на -
    //вибір рівня
    //туторіал
    //вийти
    private void setPlayButton(){
        EmptyButton playButton = new EmptyButton(FrameState.CHOOSE_LEVEL);
        playButton.setBounds(377, 471, 235, 75);
        add(playButton);
    }

    private void setTutorialButton(){
        EmptyButton tutorialButton = new EmptyButton(FrameState.CHOOSE_LEVEL);
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
        public EmptyButton(FrameState state){
            super();
            this.state = state;
            hideVisibility();
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    parent.setVisiblePanel(state);
                }
            });
        }

        private void hideVisibility(){
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
        }
    }
}
