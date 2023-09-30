import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Test {

    private Image fundo;
    private Player player;

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private double angle;

        public TestPane() {
            setLayout(new GridBagLayout());
            JButton jButton = new JButton("Dormir");
            jButton.setBounds(0,0, 250, 70);
            add(jButton);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            //Graphics2D g2d = (Graphics2D) g;
            g2d.translate(getWidth(), getHeight());

            AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
            at.rotate(Math.toRadians(90), 0, 0);
            g2d.drawRect(-50, -50, 100, 100);
            g2d.setColor(Color.RED);
            g2d.rotate(angle, 0, 0);
            g2d.drawRect(-100, -100, 200, 200);
            g2d.dispose();
        }

//        public void paintComponent(Graphics g){
//            super.paintComponent(g);
//            //subscreve methodo paint do awt
//            Graphics2D graficos = (Graphics2D) g.create();
//
//            graficos.translate(getWidth() / 2, getHeight() / 2);
//
//            AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
//            graficos.drawImage(fundo, 0, 0, null);
//            graficos.drawImage(player.getImagem(), 100, 100, this);
//
//            g.dispose();
//        }

    }

}