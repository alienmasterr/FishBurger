package Level;

public class Level {
    public static int levelState = 1;

    public static void nextLevel() {
        if (levelState != 3)
            levelState++;
    }

    public static int getZeroFine(){
        return switch (levelState) {
            case 2 -> 40;
            case 3 -> 30;
            default -> 25;
        };
    }

    public static int getAmountOfSauces(){
        return switch (levelState) {
            case 2 -> 2;
            case 3 -> 4;
            default -> 0;
        };
    }

    public static int getBurgerSize(){
        return switch (levelState) {
            case 2 -> 7;
            case 3 -> 9;
            default -> 5;
        };
    }

    public static int getTimesBeforeNextLevel(){
        return switch (levelState) {
            case 2 -> 7;
            case 3 -> 15;
            default -> 3;
        };
    }

    public static int getTypesOfCustomers(){
        return switch (levelState) {
            case 2 -> 6;
            case 3 -> 9;
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

    public static int getTimeFine() {
        return switch (levelState) {
            case 2 -> 20;
            case 3 -> 15;
            default -> 10;
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
