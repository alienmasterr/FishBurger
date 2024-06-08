package BuildStation;

import BuildStation.BuildElements.*;
import Elements.Node;
import Enums.BuildState;
import Enums.OrderState;
import Enums.PanelState;
import GrillStation.GrillStation;
import GrillStation.GrillStationElements.Meat;
import Level.Level;
import Menu.*;
import Menu.MenuElements.Ticket;
import Products.Product;
import Store.Store;
import Store.StoreElements.IconK;
import Store.StoreElements.Painting;

import java.awt.*;
import java.io.File;
import java.util.*;

public class BuildStation {
    private GameMenu.GamePanel parent;
    private Product activeProduct;
    private Product lastActiveProduct;
    private Ticket activeTicket;
    private Ticket lastTicket;
    private SauceBottle activeBottle;
    private SauceBottle lastBottle;
    private ArrayList<Product> burgerProducts = new ArrayList<>();
    private ProductTray[] productTrays = new ProductTray[7];
    private SauceBottle[] sauceBottles = new SauceBottle[4];
    private BuildBackground background = new BuildBackground(0, 0, Game.WIDTH, Game.HEIGHT - 100);
    private TicketBackground ticketBackground = new TicketBackground(0, 0, Game.WIDTH, Game.HEIGHT - 100);
    private TicketHolder ticketHolder = new TicketHolder(260, 470, 170, 230);
    public BuildState buildState = BuildState.BUILDING;
    private int diffX = -1;
    private int diffY = -1;
    private ArrayList<Product> allMeat = new ArrayList<>();

    private IconK iconK = new IconK(400, 100, 100, 100);
    private Painting painting = new Painting(600,70, 100, 100);

    public BuildStation(GameMenu.GamePanel parent) {
        this.parent = parent;
        fillTrays();
        fillBottles();
    }

    public void getMeatFromGrill(Meat meat) {
        meat.setPosition(250, 580);
        meat.setInitialX(250);
        meat.setInitialY(580);
        burgerProducts.add(meat);
    }

    private void fillTrays() {
        File[] files = (new File("res/products")).listFiles();
        if (files == null)
            return;
        for (int i = 0; i < productTrays.length; i++) {
            productTrays[i] = new ProductTray(0, 580 - i * 80, 215, 160);
            productTrays[i].setProductSprite("/products/" + files[i].getName());
        }
    }

    private void fillBottles() {
        int counter = 0;
        File[] files = (new File("res/sauses")).listFiles();
        if (files == null)
            return;
        for (File file : files) {
            if (file.isDirectory())
                continue;
            if(counter > Level.getAmountOfSauces()-1)
                return;
            sauceBottles[counter] = new SauceBottle(920 - counter * 60, 460, 80, 180);
            sauceBottles[counter].setInitialX(920 - counter * 60);
            sauceBottles[counter].setInitialY(460);
            sauceBottles[counter].getImage("/sauses/" + file.getName());
            sauceBottles[counter].setSauceDripSrc("/sauses/drip/" + file.getName());
            sauceBottles[counter].setSauceSplashSrc("/sauses/splashes/" + file.getName());
            counter++;
        }
    }

    public void draw(Graphics2D g2d) {
        parent.pin.setDrawTicket(true);
        switch (buildState) {
            case BUILDING -> drawBase(g2d);
            case PUTTING_TICKET -> drawTicketBase(g2d);
        }
    }

    private void drawBase(Graphics2D g2d) {
        background.draw(g2d);

        if(Store.iconBought){
            iconK.draw(g2d);
        }
        if(Store.paintingBought){
            painting.draw(g2d);
        }

        drawMeat(g2d);
        for (int i = 0; i < 7; i++)
            productTrays[i].draw(g2d);
        for (Product product : burgerProducts)
            product.draw(g2d);
        drawBottles(g2d);
        if (lastActiveProduct != null)
            lastActiveProduct.draw(g2d);
        update();
    }

    private void drawBottles(Graphics2D g2d){
        for (Product product : sauceBottles)
           if(product != null)
               product.draw(g2d);
    }

    private void drawMeat(Graphics2D g2d) {
        for (Product meat : allMeat)
            meat.draw(g2d);
    }

    private void drawTicketBase(Graphics2D g2d) {
        ticketBackground.draw(g2d);
        ticketHolder.draw(g2d);
        try{
            for (Product product : burgerProducts) {
                if(product instanceof Meat && !product.isUsed()) {
                    burgerProducts.remove(product);
                    continue;
                }
                product.draw(g2d);
            }
        } catch (Exception ignored){}
        updateTicket();
    }

    private void updateTicket() {
        Ticket ticket = parent.pin.getTicket();
        if (ticket == null)
            return;
        if (Game.mouse.pressed && ticket.getX() <= Game.mouse.x && ticket.getX() + ticket.getWidth() >= Game.mouse.x && ticket.getY() <= Game.mouse.y && ticket.getY() + ticket.getHeight() >= Game.mouse.y)
            activeTicket = ticket;
        else if (!Game.mouse.pressed && activeTicket != null) {
            if (checkTicketLocation()) {
                activeTicket.setX(308);
                activeTicket.setY(517);
                parent.ratingStation.setReceipt(ticket.getReceipt());
                transferInfoForRating();
                return;
            }
            lastTicket = activeTicket;
            activeTicket = null;
        }
        if (lastTicket != null) {
            returnToHolder();
            ticket.increaseSize();
            return;
        }
        updateTicketMovement();
        ticket.updateReceiptPosition();
    }

    private void transferInfoForRating() {
        parent.orderState = OrderState.RATING_ORDER;
        parent.panelState = PanelState.RATING_STATION;
        parent.ratingStation.setBurgerResult(burgerProducts);
        parent.ratingStation.setCustomer(parent.orderStation.customer);
        parent.ratingStation.setTicketHolder(ticketHolder);
        parent.ratingStation.startRating();
    }

    private boolean checkTicketLocation() {
        if (activeTicket == null)
            return false;
        return activeTicket.getX() > 260 && activeTicket.getX() < 260 + 170 && activeTicket.getY() >= 470 && activeTicket.getX() <= 470 + 230;
    }

    private void returnToHolder() {
        Ticket ticket = parent.pin.getTicket();
        if (ticket == null)
            return;
        if (diffX == -1 && diffY == -1)
            calculateDiffs(parent.pin.getX(), parent.pin.getY(), ticket.getX(), ticket.getY());
        moveToHolder();
        ticket.updateReceiptPosition();
    }

    private void moveToHolder() {
        Ticket ticket = parent.pin.getTicket();
        if (ticket == null)
            return;
        if (ticket.getX() >= parent.pin.getX() - 30 && ticket.getX() <= ticket.getX() + parent.pin.getWidth() + 30 && ticket.getY() >= parent.pin.getY() - 30 && ticket.getY() <= parent.pin.getY() + parent.pin.getHeight() + 30) {
            diffX = -1;
            diffY = -1;
            ticket.setY(20);
            ticket.setX(740);
            ticket.setWidth(230);
            ticket.setHeight(420);
            lastTicket = null;
            return;
        }
        ticket.setX(ticket.getX() + diffX);
        ticket.setY(ticket.getY() + diffY);
    }

    private void updateTicketMovement() {
        if (activeTicket == null)
            return;
        activeTicket.setX(Game.mouse.x - activeTicket.getWidth() / 2);
        activeTicket.setY(Game.mouse.y - activeTicket.getHeight() / 2);
        activeTicket.decreaseSize();
    }

    private void update() {
        updateLastActiveProduct();
        updateLastProduct();
        updateActiveProduct();
        updateNonactiveProducts();
        updateBottle();
        updateActiveBottle();
        updateStateActiveBottle();
    }

    private void updateStateActiveBottle() {
        if (activeBottle != null && !Game.mouse.pressed) {
            lastBottle = activeBottle;
            createSauceDrip();
            activeBottle = null;
        } else if (lastBottle != null)
            returnToCup();
    }

    private void createSauceDrip() {
        if (!(activeBottle.getY() >= 560 || activeBottle.getX() < 300 || activeBottle.getX() > 600))
            burgerProducts.add(lastBottle.createSauce());
    }

    private void returnToCup() {
        if (diffX == -1 && diffY == -1)
            calculateDiffs(lastBottle.getInitialX(), lastBottle.getInitialY(), lastBottle.getX(), lastBottle.getY());
        moveToCup(lastBottle.getInitialX(), lastBottle.getInitialY(), lastBottle.getX(), lastBottle.getY());
    }

    private void moveToCup(int x1, int y1, int x, int y) {
        if (x1 - 30 <= x && x1 + 30 >= x && y1 - 30 < y && y1 + 30 > y) {
            diffX = -1;
            diffY = -1;
            lastBottle.setX(lastBottle.getInitialX());
            lastBottle.setY(lastBottle.getInitialY());
            lastBottle = null;
            activeBottle = null;
            return;
        }
        lastBottle.setX(lastBottle.getX() + diffX);
        lastBottle.setY(lastBottle.getY() + diffY);
    }

    private void updateActiveBottle() {
        if (activeBottle == null)
            return;
        activeBottle.setX(Game.mouse.x - activeBottle.getWidth() / 2);
        activeBottle.setY(Game.mouse.y - activeBottle.getHeight() / 2);
    }

    private void updateBottle() {
        if (activeProduct != null || activeBottle != null)
            return;
        for (SauceBottle bottle : sauceBottles) {
            if (Game.mouse.pressed && Game.mouse.x > bottle.getX() - 10 && Game.mouse.x < bottle.getX() + bottle.getWidth() + 10 && Game.mouse.y > bottle.getY() - 10 && Game.mouse.y < bottle.getY() + bottle.getHeight() + 10) {
                activeBottle = bottle;
                return;
            }
        }
    }

    private void updateLastProduct() {
        if (burgerProducts.isEmpty())
            return;
        if (updateLastMeat())
            return;
        if(activeBottle != null)
            return;
        Product lastProduct = burgerProducts.getLast();
        if (lastProduct instanceof Sauce)
            return;
        if (Game.mouse.pressed && Game.mouse.x >= lastProduct.getX() && Game.mouse.x <= lastProduct.getX() + 150 && Game.mouse.y >= lastProduct.getY() && Game.mouse.y <= lastProduct.getY() + 100) {
            activeProduct = lastProduct;
        }
    }

    private boolean updateLastMeat() {
        Product lastMeat = getLastMeat();
        if (lastMeat == null)
            return false;
        if (!lastMeat.isUsed() && Game.mouse.pressed && Game.mouse.x >= lastMeat.getX() && Game.mouse.x <= lastMeat.getX() + 150 && Game.mouse.y >= lastMeat.getY() && Game.mouse.y <= lastMeat.getY() + 100) {
            activeProduct = lastMeat;
            burgerProducts.remove(lastMeat);
            burgerProducts.add(lastMeat);
            return true;
        }
        return false;
    }

    private Product getLastMeat() {
        Product lastMeat = null;
        for (Product pr : burgerProducts) {
            if (pr instanceof Meat)
                lastMeat = pr;
        }
        return lastMeat;
    }

    private void updateNonactiveProducts() {
        for (Product product : burgerProducts)
            if (isFalling(product) && !isColliding(product)) {
                product.setY(product.getY() + 15);
                if (product instanceof Meat)
                    product.setUsed(true);
            } else {
                if (product instanceof Sauce) {
                    ((Sauce) product).turnIntoSplash();
                }
            }
        if (burgerProducts.isEmpty())
            return;
        if (isUpperBun(burgerProducts.getLast()) && (!isFalling(burgerProducts.getLast()) || isColliding(burgerProducts.getLast())))
            startTicketState();
    }

    private boolean isUpperBun(Product product) {
        if (product.getSrc() == null)
            return false;
        return product.getSrc().equals("/products/upperbun.png");
    }

    private void startTicketState() {
        buildState = BuildState.PUTTING_TICKET;
        parent.orderStation.stopTicketErasure();
        if(burgerProducts.getFirst().getX() < 447) {
            int diff = burgerProducts.getFirst().getX()-447;
            for(Product product: burgerProducts){
                product.setX(product.getX()-diff);
            }
        }
    }

    private boolean isColliding(Product product) {
        for (Product fallenProduct : burgerProducts) {
            if (fallenProduct == product)
                continue;
            if (fallenProduct.getY() - 27 <= product.getY() && fallenProduct.getX() - 150 <= product.getX() && fallenProduct.getX() + 150 >= product.getX())
                return true;
        }
        return false;
    }

    private boolean isFalling(Product product) {
        return product.getY() <= 560;
    }

    private void updateActiveProduct() {
        updateActiveProductState();
        updateActiveProductMovement();
        updateActiveProductCollision();
    }

    private void updateActiveProductState() {
        if (Game.mouse.pressed || activeProduct == null)
            return;
        checkFallingLocation();
        activeProduct = null;
    }

    private void checkFallingLocation() {
        if ((activeProduct.getY() >= 560 || activeProduct.getX() < 300 || activeProduct.getX() > 600)) {
            lastActiveProduct = activeProduct;
            burgerProducts.remove(activeProduct);
            activeProduct = null;
        }
    }

    private void updateLastActiveProduct() {
        if (lastActiveProduct == null)
            return;
        returnToTray();
    }

    private void returnToTray() {
        if (lastActiveProduct instanceof Meat) {
            returnToInitial();
            return;
        }
        Product trayProduct = getTrayProduct(lastActiveProduct);
        if (trayProduct == null)
            return;
        if (diffX == -1 && diffY == -1)
            calculateDiffs(trayProduct.getX(), trayProduct.getY(), lastActiveProduct.getX(), lastActiveProduct.getY());
        moveToTray(trayProduct, lastActiveProduct);
    }

    private void returnToInitial() {
        if (diffX == -1 && diffY == -1)
            calculateDiffs(lastActiveProduct.getInitialX(), lastActiveProduct.getInitialY(), lastActiveProduct.getX(), lastActiveProduct.getY());
        moveToInitial(lastActiveProduct.getInitialX(), lastActiveProduct.getInitialY(), lastActiveProduct.getX(), lastActiveProduct.getY());
    }

    private void moveToInitial(int x1, int y1, int x, int y) {
        if (x1 - 30 <= x && x1 + 30 >= x && y1 - 30 < y && y1 + 30 > y) {
            diffX = -1;
            diffY = -1;
            lastActiveProduct.setUsed(false);
            burgerProducts.add(lastActiveProduct);
            lastActiveProduct = null;
            return;
        }
        lastActiveProduct.setX(lastActiveProduct.getX() + diffX);
        lastActiveProduct.setY(lastActiveProduct.getY() + diffY);
    }

    //з (х.у) в (х1, у1)
    private void calculateDiffs(int x1, int y1, int x, int y) {
        int distX = x1 - x;
        int distY = y1 - y;
        double gDist = Math.sqrt(distX * distX + distY * distY);
        int steps = (int) ((gDist - 1) / 30);
        if (steps == 0)
            steps = 1;
        diffY = distY / steps;
        diffX = distX / steps;
    }

    private void moveToTray(Product trayProduct, Product product) {
        if (trayProduct.getX() - 30 <= product.getX() && trayProduct.getX() + 30 >= product.getX() && trayProduct.getY() - 30 < product.getY() && trayProduct.getY() + 30 > product.getY()) {
            diffX = -1;
            diffY = -1;
            lastActiveProduct = null;
            return;
        }
        lastActiveProduct.setX(lastActiveProduct.getX() + diffX);
        lastActiveProduct.setY(lastActiveProduct.getY() + diffY);
    }

    private Product getTrayProduct(Product original) {
        for (ProductTray tray : productTrays) {
            if (Objects.equals(tray.product.getSrc(), original.getSrc()))
                return tray.product;
        }
        return null;
    }

    private void updateActiveProductMovement() {
        if (activeProduct == null)
            return;
        activeProduct.setX(Game.mouse.x - 75);
        activeProduct.setY(Game.mouse.y - 50);
        if (activeProduct.getY() > 560)
            activeProduct.setY(560);
    }

    private void updateActiveProductCollision() {
        if (activeProduct == null)
            return;
        for (Product fallenPr : burgerProducts) {
            if (fallenPr == activeProduct)
                continue;
            if (fallenPr.getY() - 30 <= activeProduct.getY() && fallenPr.getY() + 100 >= activeProduct.getY() && fallenPr.getX() - 100 < activeProduct.getX() && fallenPr.getX() + 120 > activeProduct.getX())
                activeProduct.setY(fallenPr.getY() - 40);
        }
    }

    private class ProductTray extends Node {
        private final Product product = new Product();

        public ProductTray(int x, int y, int width, int height) {
            super(x, y, width, height);
            image = getImage("/buildmenu/buildtray.png");
        }

        public void setProductSprite(String src) {
            product.getImage(src);
            setProductLocation();
        }

        @Override
        public void draw(Graphics2D g2d) {
            super.draw(g2d);
            checkAction();
            product.draw(g2d);
        }

        public void checkAction() {
            if (Game.mouse.pressed && Game.mouse.x >= product.getX() && Game.mouse.x <= product.getX() + 150 && Game.mouse.y >= product.getY() && Game.mouse.y <= product.getY() + 100) {
                if (activeProduct != null || activeBottle != null)
                    return;
                activeProduct = createProduct(product);
                burgerProducts.add(activeProduct);
            }
        }

        private Product createProduct(Product original) {
            Product copy = new Product(original.getX(), original.getY(), original.getWidth(), original.getHeight());
            copy.getImage(original.getSrc());
            return copy;
        }

        private void setProductLocation() {
            product.setX(x + 20);
            product.setY(y + 12);
            product.setHeight(100);
            product.setWidth(150);
        }
    }
}
