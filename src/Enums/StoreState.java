package Enums;

public enum StoreState {
    SAVED(1),
    CHOOSING(2);

    private int state;

    StoreState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
