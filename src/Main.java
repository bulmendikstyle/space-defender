import org.lwjgl.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Spaceship Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        SpaceshipPanel panel = new SpaceshipPanel();

        frame.add(panel);
        frame.setVisible(true);
    }
}

class SpaceshipPanel extends JPanel {
    private int panelWidth = 500;

    Spaceship spaceship = new Spaceship(50, 50, panelWidth);
    List<Bullet> bullets = new ArrayList<>();

    public SpaceshipPanel() {
        Timer timer = new Timer(10, e -> {
            spaceship.move();
            for (Bullet bullet : bullets) {
                bullet.move();
            }
            checkCollisions();
            repaint();
        });
        timer.start();

        Timer shootingTimer = new Timer(500, e -> {
            Bullet newBullet = new Bullet(spaceship.getX() + 25, spaceship.getY(), panelWidth);
            bullets.add(newBullet);
        });
        shootingTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        spaceship.draw(g);
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    private void checkCollisions() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (spaceship.intersects(bullet)) {
                bullets.remove(i);
                i--;
            }
        }
    }
}

class Spaceship {
    private int x;
    private int y;
    private int direction = 1;
    private int panelWidth;

    public Spaceship(int x, int y, int panelWidth) {
        this.x = x;
        this.y = y;
        this.panelWidth = panelWidth;
    }

    public void move() {
        x += direction * 5;
        if (x >= panelWidth - 50) {
            direction = -1;
        } else if (x <= 0) {
            direction = 1;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 50, 20);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean intersects(Bullet bullet) {
        Rectangle spaceshipRect = new Rectangle(x, y, 50, 20);
        Rectangle bulletRect = new Rectangle(bullet.getX(), bullet.getY(), 5, 10);
        return spaceshipRect.intersects(bulletRect);
    }
}

class Bullet {
    private int x;
    private int y;
    private int panelWidth;

    public Bullet(int x, int y, int panelWidth) {
        this.x = x;
        this.y = y;
        this.panelWidth = panelWidth;
    }

    public void move() {
        y -= 10;
        if (y < 0) {
            y = 500;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 5, 10);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
