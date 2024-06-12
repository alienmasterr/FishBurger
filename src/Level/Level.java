package Level;

public class Level {
    public static int levelState = 1;


    public static void nextLevel() {
        if (levelState != 3)
            levelState++;
    }

    public static int getMeatSidesAmount(){
        return switch (levelState) {
            case 2 -> 10;
            case 3 -> 5;
            default -> 15;
        };
    }

    public static int getMeatSidesFine(){
        return switch (levelState) {
            case 2 -> 10;
            case 3 -> 15;
            default -> 5;
        };
    }

    public static int getZeroFine(){
        return switch (levelState) {
            case 2 -> 30;
            case 3 -> 40;
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
            case 3 -> 50;
            default -> 3;
        };
    }

    public static int getTypesOfCustomers(){
        return switch (levelState) {
            case 2 -> 6;
            case 3 -> 13;
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
            case 2 -> 30;
            case 3 -> 40;
            default -> 20;
        };
    }

    public static int getTimeFine() {
        return switch (levelState) {
            case 2 -> 15;
            case 3 -> 20;
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
