package Store.StoreElements;

import Level.Level;

import static Store.Store.tableBought;

public class Table extends Accessories {
    public Table(int x, int y, int width, int height, int price) {
        super(x, y, width, height, price);
        if(Level.levelState < 2){
            image = getImage("/store/lockedproduct.png");

        }if(tableBought){
            image = getImage("");
        }else {
            image = getImage("/store/table.png");
        }
    }

    public Table(int x, int y, int width, int height) {
        super(x, y, width, height, 100);
        if(Level.levelState < 2 && !tableBought){
            image = getImage("/store/lockedproduct.png");

        }else {
            image = getImage("/store/table.png");
        }
    }
}
