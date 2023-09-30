import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    public Screen(){
//        JLabel base = new JLabel();
//        setLayout(new BoxLayout(base, BoxLayout.X_AXIS));
        JButton jButton = new JButton("Dormir");
        jButton.setBounds(0,0, 250, 70);
        JLabel imgLabel = new JLabel(new ImageIcon("res/background.jpg"));
        JLabel cat = new JLabel(new ImageIcon("res/cat.gif"));

        add(jButton);
        add(imgLabel);
        add(cat);
        setTitle("Meu jogo");
        setSize(1024,728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }
}
