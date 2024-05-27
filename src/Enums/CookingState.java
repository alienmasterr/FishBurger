package Enums;

public enum CookingState {
    MEAT_GRILLING(0),
    MEAT_BURNING(1),
    MEAT_NOT_READY(2),
    MEAT_READY(3);

    private int state;
    CookingState(int state) {
        this.state = state;
    }
    public int getState() {
        return state;
    }
}
