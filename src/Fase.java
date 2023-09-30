import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.concurrent.TimeUnit;

public class Fase extends JPanel {
    private Image fundo;
    private Player player;
    private Timer timer;

    public Fase(){
        ImageIcon referencia = new ImageIcon("res/background.jpg");
        fundo = referencia.getImage();

        player = new Player();
        player.load();
    }

//    public void paint(Graphics g){
//        super.paint(g);
//        //subscreve methodo paint do awt
//        Graphics2D graficos = (Graphics2D) g;
//        graficos.drawImage(fundo, 0, 0, null);
//        graficos.drawImage(player.getImagem(), 100, 100, this);
//
//        g.dispose();
//    }
}
