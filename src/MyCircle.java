import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;

class MyCircle extends JFrame {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int radius;
    private Color color;
    private int initialRadius;
    private Random rand = new Random();

    public MyCircle(int x, int y, int radius) {
        setX(x);
        setY(y);
        setDx(setInitialXDirection());
        setDy(setInitialYDirection());
        setRadius(radius);
        setColor(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        setInitialRadius(initialRadius);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }

    public int getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    public int setInitialXDirection() {
        int randomXDirection = rand.nextInt(10) + 1;
        if(randomXDirection % 2 == 0) {
            return randomXDirection;
        } else {
            return -randomXDirection;
        }
    }

    public int setInitialYDirection() {
        int randomYDirection = rand.nextInt(10) + 1;
        if(randomYDirection % 2 == 0) {
            return randomYDirection;
        } else {
            return -randomYDirection;
        }
    }

    public void setColor(int r, int g, int b) {
        this.color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }

    public void setInitialRadius(int initialSize) {
        this.initialRadius = initialSize;
    }

    public int getInitialRadius() {
        return initialRadius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, radius, radius);
    }
}
