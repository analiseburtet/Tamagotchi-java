import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame implements ActionListener {
    Player player;
    JButton jButtonSleep;
    JButton jButtonSkip;
    JButton jButtonComerMuito;
    JButton jButtonNaoComer;
    JButton jButtonComerPouco;
    JButton jButtonRun;
    JButton jButtonWalk;
    JLabel imgLabel;
    JLabel cat;

    public void render(){
        jButtonComerMuito.setVisible(false);
        jButtonComerPouco.setVisible(false);
        jButtonNaoComer.setVisible(false);
        jButtonRun.setVisible(false);
        jButtonWalk.setVisible(false);
        jButtonSkip.setVisible(false);
        jButtonSleep.setVisible(false);
        switch (generateRandomNumber()) {
            case 0:
                sentirFome();
                jButtonComerMuito.setVisible(true);
                jButtonComerPouco.setVisible(true);
                jButtonNaoComer.setVisible(true);
                break;
            case 1:
                sentirSono();
                jButtonSleep.setVisible(true);
                jButtonSkip.setVisible(true);
                break;
            case 2:
                exercitar();
                jButtonRun.setVisible(true);
                jButtonWalk.setVisible(true);
                break;
        }
        add(imgLabel);
        add(cat);
        setTitle("Meu jogo");
        setSize(1024,728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }
    public Screen(){
        player = new Player();
        jButtonSleep = new JButton("Sleep");
        jButtonSleep.setBounds(200,600, 250, 70);

        add(jButtonSleep);

        jButtonSleep.addActionListener(this::actionPerformed);

        jButtonSkip = new JButton("Ignore");
        jButtonSkip.setBounds(600,600, 250, 70);

        add(jButtonSkip);

        jButtonSkip.addActionListener(this::skipAction);

        jButtonComerMuito = new JButton("Eat a lot");
        jButtonComerMuito.setBounds(150,600, 250, 70);

        add(jButtonComerMuito);

        jButtonComerMuito.addActionListener(this::eatALotAction);

        jButtonComerPouco = new JButton("Eat little");
        jButtonComerPouco.setBounds(450,600, 250, 70);

        add(jButtonComerPouco);

        jButtonComerPouco.addActionListener(this::eatLittleAction);

        jButtonNaoComer = new JButton("Don't eat");
        jButtonNaoComer.setBounds(800,600, 250, 70);

        add(jButtonNaoComer);

        jButtonNaoComer.addActionListener(this::notEatAction);

        jButtonRun = new JButton("Run");
        jButtonRun.setBounds(200,600, 250, 70);

        add(jButtonRun);

        jButtonRun.addActionListener(this::runAction);

        jButtonWalk = new JButton("Walk");
        jButtonWalk.setBounds(600,600, 250, 70);

        add(jButtonWalk);

        jButtonWalk.addActionListener(this::walkAction);

        imgLabel = new JLabel(new ImageIcon("res/background.jpg"));
        cat = new JLabel(new ImageIcon("res/cat.gif"));
        render();
    }

    public String sentirSono(){
        return "to com sono";
    }
    public String sentirFome(){
        return "to com fome";
    }
    public String exercitar(){
        return "exercicio";
    }

    public int generateRandomNumber(){
        return (int) (Math.random() * 3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Mensagem");
    }

    public void skipAction(ActionEvent e) {
        player.skipSleep();
        render();
    }
    private void walkAction(ActionEvent e) {
        render();
    }
    private void runAction(ActionEvent e) {
        render();
    }
    private void notEatAction(ActionEvent e) {
        render();
    }
    private void eatALotAction(ActionEvent e) {
        render();
    }
    private void eatLittleAction(ActionEvent e) {
        render();
    }
}
