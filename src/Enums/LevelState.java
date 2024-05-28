package Enums;

public enum LevelState {
    LEVEL1(1),
    LEVEL2(2),
    LEVEL3(3);
    private int state;
    LevelState(int state) {
        this.state = state;
    }
    public int getState() {
        return state;
    }
}
