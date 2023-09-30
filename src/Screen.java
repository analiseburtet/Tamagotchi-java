import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame implements ActionListener {
    Player player;
//    int randomNumber = generateRandomNumber();
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
        jButtonSleep = new JButton("Dormir");
        jButtonSleep.setBounds(200,600, 250, 70);

        add(jButtonSleep);

        jButtonSleep.addActionListener(this::actionPerformed);

        jButtonSkip = new JButton("Ignorar");
        jButtonSkip.setBounds(600,600, 250, 70);

        add(jButtonSkip);

        jButtonSkip.addActionListener(this::skipAction);

        jButtonComerMuito = new JButton("Comer muito");
        jButtonComerMuito.setBounds(700,600, 50, 70);

        add(jButtonComerMuito);

        jButtonComerPouco = new JButton("Comer pouco");
        jButtonComerPouco.setBounds(700,600, 50, 70);

        add(jButtonComerPouco);

        jButtonNaoComer = new JButton("Não comer");
        jButtonNaoComer.setBounds(700,600, 50, 70);

        add(jButtonNaoComer);

        jButtonRun = new JButton("Run");
        jButtonRun.setBounds(700,600, 50, 70);

        add(jButtonRun);

        jButtonWalk = new JButton("Walk");
        jButtonWalk.setBounds(700,600, 50, 70);

        add(jButtonWalk);

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

//    public void setRandomNumber(int number){
//        this.randomNumber = number;
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Mensagem");
    }

    public void skipAction(ActionEvent e) {
        player.skipSleep();
        render();
    }
}
