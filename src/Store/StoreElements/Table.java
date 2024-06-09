package Store.StoreElements;

public class Table extends Accessories {
    public Table(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        image = getImage("/store/table.png");
    }

    public Table(int x, int y, int width, int height) {
        super(x, y, width, height, 100);
        image = getImage("/store/table.png");
    }
}
