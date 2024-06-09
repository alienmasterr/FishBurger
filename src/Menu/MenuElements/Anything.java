package Menu.MenuElements;

import Level.Level;
import Products.Product;
//цей клас є ускладненням починаючи з другого рівня
//кастомеру байдуже що покладуть
public class Anything extends Product {
    public Anything(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("/higherlevels/anything.png");
    }

    public Anything(){
            setImage("/higherlevels/anything.png");
    }
}
