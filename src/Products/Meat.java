package Products;

public class Meat extends Product{
    public Meat (int x, int y, int width, int height){
        super(x, y, width, height);
        setImage("/temp.png");
    }

    public Meat(){
        setImage("/temp.png");
    }
}
