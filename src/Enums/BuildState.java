package Enums;

public enum BuildState {
    BUILDING(1),
    PUTTING_TICKET(2);

    private int state;
    BuildState(int state) {
        this.state = state;
    }
    public int getState() {
        return state;
    }
}
