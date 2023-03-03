import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

class MyWindow extends JFrame implements MouseWheelListener {
    ArrayList<MyCircle> circles = new ArrayList<>();
    Random rand = new Random();
    private MyPaintingArea area;
    private int wheelClicks = 0;

    public MyWindow() {
        // add mouse listener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int random = rand.nextInt(10) + 1;
                createRandomCircles(e.getX(), e.getY(), random);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse pressed at: " + e.getX() + ", " + e.getY());
            }
        });
        addMouseWheelListener((e) -> {
                wheelClicks++;
                System.out.println("Mouse wheel moved: " + wheelClicks);
        });
        addMouseWheelListener(this);

        // set window size
        setSize(500, 500);

        // exit on close
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // add painting area
        area = new MyPaintingArea(circles);
        add(area, BorderLayout.CENTER);

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    reverseOutOfBounds();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public int randomCircleSize() {
        return rand.nextInt(100) + 1;
    }

    // reverse circle size if out of bounds
    public void reverseOutOfBounds() {
        for (MyCircle circle : circles) {
            if (isOutOfBounds(circle, area)) {
                circle.setRadius(circle.getRadius() - 1);
            }
        }
    }

    // check if circle is out of bounds
    public boolean isOutOfBounds(MyCircle circle, MyPaintingArea area) {
        if (circle.getX() + circle.getDx() + circle.getRadius() -  + circle.getInitialRadius() > area.getWidth() ||
            circle.getY() + circle.getDy() + circle.getRadius()  +  + circle.getInitialRadius() > area.getHeight()) {
            return true;
        }
        return false;
    }
    // mouse wheel listener
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() <= 0) {
            for (MyCircle circle : circles) {
                if (!isOutOfBounds(circle, area)) {
                    circle.setRadius(circle.getRadius() + 5);
                } 
            }
        } else if (e.getWheelRotation() >= 0) {
            for (MyCircle circle : circles) {
                if (!isOutOfBounds(circle, area)) {
                    circle.setRadius(circle.getRadius() - 10);
                } 
            }
        }
    }
    // create random circles
    private void createRandomCircles(int x, int y, int random) {
        for (int i = 1; i <= random; i++) {
            circles.add(new MyCircle(x, y, randomCircleSize()));
        }
    }
    // get painting area
    public MyPaintingArea getPaintingArea() {
        return area;
    }
}
