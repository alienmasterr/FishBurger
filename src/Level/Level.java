package Level;

public class Level {
    private int levelState = 1;

    public Level(int levelState) {
        this.levelState = levelState;
    }

    public void nextLevel() {
        if (levelState != 3)
            levelState++;
    }

    public int getBurgerSize(){
        return switch (levelState) {
            case 2 -> 9;
            case 3 -> 7;
            default -> 5;
        };
    }

    public int getTimesBeforeNextLevel(){
        return switch (levelState) {
            case 2 -> 7;
            case 3 -> 15;
            default -> 5;
        };
    }

    public int getTypesOfCustomers(){
        return switch (levelState) {
            case 2 -> 9;
            case 3 -> 6;
            default -> 3;
        };
    }

    public int getMistakeBuildFine(){
        return switch (levelState) {
            case 2 -> 5;
            case 3 -> 9;
            default -> 3;
        };
    }

    public int getWrongSizeFine(){
        return switch (levelState) {
            case 2 -> 50;
            case 3 -> 30;
            default -> 20;
        };
    }

    public int getNumberOfSauces() {
        return switch (levelState) {
            case 2 -> 2;
            case 3 -> 4;
            default -> 0;
        };
    }

    public int getMaxTimeForOrder() {
        return switch (levelState) {
            case 2 -> 60;
            case 3 -> 90;
            default -> 160;
        };
    }

    public int getMaxMoney() {
        return switch (levelState) {
            case 2 -> 25;
            case 3 -> 50;
            default -> 10;
        };
    }

    public int getLevelState() {
        return levelState;
    }

    public void setLevelState(int levelState) {
        this.levelState = levelState;
    }
}
