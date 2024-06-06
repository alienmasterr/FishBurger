package Enums;

public enum PanelState {
    ORDER_STATION(1),
    GRILL_STATION(2),
    BUILD_STATION(3),
    RATING_STATION(4),
    GAME_MENU(5),
    STORE(6);
    private int state;
    PanelState(int state) {
        this.state = state;
    }
    public int getState() {
        return state;
    }
}
