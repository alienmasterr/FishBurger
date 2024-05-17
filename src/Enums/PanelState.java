package Enums;

public enum PanelState {
    MAIN_MENU(0),
    ORDER_STATION(1),
    GRILL_STATION(2),
    BUILD_STATION(3);
    private int state;
    PanelState(int state) {
        this.state = state;
    }
    public int getState() {
        return state;
    }
}
