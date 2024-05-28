package Menu;

import Elements.Node;
import Enums.FrameState;
import Enums.LevelState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelMenu extends JPanel {
    private Game parent;
    public LevelMenu(Game parent){
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
        mainMenu.getImage("/levelmenu.png");
        mainMenu.draw(g2d);
    }

    private void setStaticSize(){
        setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMinimumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
    }

    private void setButtons(){
        setLevel1Button();
        setLevel2Button();
        setLevel3Button();
    }

    private void setLevel1Button(){
        EmptyButton level1 = new EmptyButton(LevelState.LEVEL1);
        level1.setBounds(81, 639, 240, 75);
        add(level1);
    }

    private void setLevel2Button(){
        EmptyButton level2 = new EmptyButton(LevelState.LEVEL2);
        level2.setBounds(81+240+70, 639, 240, 75);
        add(level2);
    }
    private void setLevel3Button(){
        EmptyButton level2 = new EmptyButton(LevelState.LEVEL3);
        level2.setBounds(81+480+140, 639, 240, 75);
        add(level2);
    }

    private class EmptyButton extends JButton{
        private LevelState state;
        public EmptyButton(LevelState state){
            super();
            this.state = state;
            hideVisibility();
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    parent.startGame(state);
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
