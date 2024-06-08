package Enums;

public enum FrameState {
    MAIN_MENU(1),
    CHOOSE_LEVEL(2),
    GAME(3),
    TUTORIAL(4),
    EXIT(5),
    GAME_OVER(6);
    private int state;
    FrameState(int state) {
        this.state = state;
    }
    public int getState() {
        return state;
    }
}
