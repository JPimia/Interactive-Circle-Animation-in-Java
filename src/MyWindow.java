import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

class MyWindow extends JFrame implements MouseWheelListener {
    ArrayList<MyCircle> circles = new ArrayList<>();
    Random rand = new Random();
    private MyPaintingArea area;
    int wheelClicks = 0;
    private int circleSize = 50; // default circle size

    public MyWindow() {
        // add mouse listener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int random = rand.nextInt(10) + 1;
                createRandomCircles(e.getX(), e.getY(), random);
                repaint();
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
        area = new MyPaintingArea(circles, getHeight(), getWidth());
        add(area, BorderLayout.CENTER);
        
        // resize window to fit all components
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    // mouse wheel listener
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation() <= 0) {
            circleSize -= 5;
            repaint();
        } else {
            circleSize += 5;
            repaint();
        }
    }
    // create random circles
    private void createRandomCircles(int x, int y, int random) {
        for (int i = 1; i <= random; i++) {
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            circles.add(new MyCircle(x, y, rand.nextInt(6), rand.nextInt(6), circleSize, color));
        }
    }
    // get painting area
    public MyPaintingArea getPaintingArea() {
        return area;
    }
}