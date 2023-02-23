import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class Main {
    public static void main(String [] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread started");
                MyWindow window = new MyWindow();
                MyPaintingArea area = window.getPaintingArea();
                ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
                executor.scheduleAtFixedRate(() -> {
                    // change direction of circles
                    area.changeCircleDirection();
                    area.repaint();
                }, 0, 10, TimeUnit.MILLISECONDS);
            }
        });
        t.start();
    }
}