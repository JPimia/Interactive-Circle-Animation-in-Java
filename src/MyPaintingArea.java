import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Graphics;

class MyPaintingArea extends JPanel {
    private ArrayList<MyCircle> circles;

    public MyPaintingArea(ArrayList<MyCircle> circles) {
        this.circles = circles;
        setSize(500,500);
        setBackground(Color.WHITE);
    }
    // change circle location
    public void changeCircleDirection() {
        if (!circles.isEmpty()) {
            for (MyCircle circle : circles) {
                circle.setX(circle.getX() + circle.getDx());
                circle.setY(circle.getY() + circle.getDy());
                if(circle.getX() + circle.getDx() <= 0 || circle.getX() + circle.getRadius() >= getWidth()) {
                    circle.setDx(-circle.getDx());
                }
                if(circle.getY() + circle.getDy() <= 0 || circle.getY() + circle.getRadius() >= getHeight()) {
                    circle.setDy(-circle.getDy());
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // use g to draw stuff
        for (MyCircle circle : circles) {
            circle.draw(g);
        }
    }
}