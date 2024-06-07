package Level;

public class Level {
    public static int levelState = 1;

    public static void nextLevel() {
        if (levelState != 3)
            levelState++;
    }

    public static int getBurgerSize(){
        return switch (levelState) {
            case 2 -> 9;
            case 3 -> 7;
            default -> 5;
        };
    }

    public static int getTimesBeforeNextLevel(){
        return switch (levelState) {
            case 2 -> 7;
            case 3 -> 15;
            default -> 5;
        };
    }

    public static int getTypesOfCustomers(){
        return switch (levelState) {
            case 2 -> 9;
            case 3 -> 6;
            default -> 3;
        };
    }

    public static int getMistakeBuildFine(){
        return switch (levelState) {
            case 2 -> 5;
            case 3 -> 9;
            default -> 3;
        };
    }

    public static int getWrongSizeFine(){
        return switch (levelState) {
            case 2 -> 50;
            case 3 -> 30;
            default -> 20;
        };
    }

    public static int getNumberOfSauces() {
        return switch (levelState) {
            case 2 -> 2;
            case 3 -> 4;
            default -> 0;
        };
    }

    public static int getMaxTimeForOrder() {
        return switch (levelState) {
            case 2 -> 60;
            case 3 -> 90;
            default -> 160;
        };
    }

    public static int getMaxMoney() {
        return switch (levelState) {
            case 2 -> 25;
            case 3 -> 50;
            default -> 10;
        };
    }

    public static int getLevelState() {
        return levelState;
    }

}
