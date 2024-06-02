package Enums;

public enum RatingState {
    RATING(1),
    SHOWING_RESULT(2),
    GETTING_MONEY(3),
    WALKING_AWAY(4);
    private int state;
    RatingState(int state) {
        this.state = state;
    }
    public int getState() {
        return state;
    }
}
