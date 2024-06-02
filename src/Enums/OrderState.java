package Enums;

public enum OrderState {
    WAITING_CUSTOMER(0),
    CUSTOMER_ORDERING(1),
    MAKING_ORDER(2),
    RATING_ORDER(3),
    GETTING_MONEY(4);

    private int state;
    OrderState(int state) {
        this.state = state;
    }
    public int getState() {
        return state;
    }
}
