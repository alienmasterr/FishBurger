package Products;

public class Meat extends Product{
    public Meat (int x, int y, int width, int height){
        super(x, y, width, height);
        setImage("/meat/meat.png");
    }

    public Meat(){
        setImage("/meat/meat.png");
    }
}
