import org.lwjgl.*;
import javax.swing.*;
import java.awt.*;
public class Main {
    public static void main(String[] args) {
        //Ғарыштық қорғаушылар

        //Ойынның мақсаты:ғарыш кемесімен алдына келген жауларды атып, өз кеңістігіңді қорғау.

        //Ойын ережесі:уақыт біткенше жаулардың бәрін атып сындырсақ,ұтыс табамыз.Егер бір жауды өткізіп алсақ , жеңіліс табамыз

            JFrame frame = new JFrame("Spaceship Interface");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);

            SpaceshipPanel panel = new SpaceshipPanel();

            frame.add(panel);
            frame.setVisible(true);
    }
}

class SpaceshipPanel extends JPanel {
    Spaceship spaceship = new Spaceship(50, 50);

    public SpaceshipPanel() {
        Timer timer = new Timer(10, e -> {
            spaceship.move();
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        spaceship.draw(g);
    }
}

class Spaceship {
    private int x;
    private int y;

    public Spaceship(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += 5;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 50, 20);
    }
}