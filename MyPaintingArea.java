import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Graphics;

class MyPaintingArea extends JPanel {
    private ArrayList<MyCircle> circles;
    private int height;
    private int width;

    public MyPaintingArea(ArrayList<MyCircle> circles, int height, int width) {
        this.circles = circles;
        this.height = height;
        this.width = width;
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.WHITE);

    }
    // change circle location
    public void changeCircleDirection() {
        for (MyCircle circle : circles) {
            int newX = circle.getX() + circle.getDx();
            int newY = circle.getY() + circle.getDy();
            if(newX + circle.getRadius() > width || newX < 0) {
                circle.setDx(-circle.getDx());
            }
            if(newY + circle.getRadius() > height || newY < 0) {
                circle.setDy(-circle.getDy());
            }
            circle.setX(newX);
            circle.setY(newY);
        }
        repaint();
        revalidate();
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