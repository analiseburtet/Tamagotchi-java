import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.event.ActionEvent;

public class Screen extends JFrame {
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
    JLabel labelDays;
    JLabel numberOfDays;
    JLabel labelWeight;
    JLabel numberOfKilos;
    boolean shouldEat = false;

    int aux;

    public void setShouldEat(boolean shouldEat) {
        this.shouldEat = shouldEat;
    }

    public void render(){
        if(player.getStatus() == PlayerStatus.EXPLODED){
            cat.setIcon(new ImageIcon("res/exploded.gif"));
            setTimeout(() -> {
                cat.setIcon(new ImageIcon("res/gameover.gif"));
            },4000);
            return;
        } else if(player.getStatus() == PlayerStatus.DEAD){
            hideButtons();
            cat.setIcon(new ImageIcon("res/gameover.gif"));
            return;
        }

        if(shouldEat) {
            aux = 0;
        } else {
            aux = generateRandomNumber();
        }
        hideButtons();
        switch (aux) {
            case 0:
                jButtonComerMuito.setVisible(true);
                jButtonComerPouco.setVisible(true);
                jButtonNaoComer.setVisible(true);
                setShouldEat(false);
                break;
            case 1:
                jButtonSleep.setVisible(true);
                jButtonSkip.setVisible(true);
                break;
            case 2:
                jButtonRun.setVisible(true);
                jButtonWalk.setVisible(true);
                break;
        }

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

        jButtonSleep.addActionListener(this::sleepAction);

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

        labelDays = new JLabel("Days");
        labelDays.setBounds(10, 10, 200, 20); // Configurar posição e tamanho
        labelDays.setHorizontalAlignment(JLabel.LEFT); // Alinhar o texto à esquerda
        labelDays.setVerticalAlignment(JLabel.TOP); // Alinhar o texto na parte superior

        // Criar e configurar o segundo JLabel para o texto na parte superior à esquerda
        numberOfDays = new JLabel("0");
        numberOfDays.setBounds(10, 30, 200, 20); // Configurar posição e tamanho
        numberOfDays.setHorizontalAlignment(JLabel.LEFT); // Alinhar o texto à esquerda
        numberOfDays.setVerticalAlignment(JLabel.TOP);

        labelWeight = new JLabel("Weight");
        labelWeight.setBounds(200, 10, 200, 20); // Configurar posição e tamanho
        labelWeight.setHorizontalAlignment(JLabel.LEFT); // Alinhar o texto à esquerda
        labelWeight.setVerticalAlignment(JLabel.TOP); // Alinhar o texto na parte superior

        numberOfKilos = new JLabel(String.valueOf(player.getWeigth()));
        numberOfKilos.setBounds(200, 30, 200, 20); // Configurar posição e tamanho
        numberOfKilos.setHorizontalAlignment(JLabel.LEFT); // Alinhar o texto à esquerda
        numberOfKilos.setVerticalAlignment(JLabel.TOP);

        add(labelDays); // Adicionar o primeiro JLabel ao JFrame
        add(numberOfDays);
        add(labelWeight);
        add(numberOfKilos);
        add(imgLabel);
        add(cat);
        render();
    }
    public int generateRandomNumber(){
        return (int) (Math.random() * 3);
    }
    public void sleepAction(ActionEvent e) {
        hideButtons();
        player.sleep();
        cat.setIcon(new ImageIcon("res/sleepcat.gif"));
        setTimeout(() -> {
            cat.setIcon(new ImageIcon("res/cat.gif"));
            numberOfDays.setText(String.valueOf(player.getAge()));
            render();
            },2000);
    }
    public void skipAction(ActionEvent e) {
        player.skipSleep();
        if(player.getStatus() == PlayerStatus.SLEEPY){
            cat.setIcon(new ImageIcon("res/sleepcat.gif"));
        }
        render();
    }
    private void walkAction(ActionEvent e) {
        hideButtons();
        cat.setIcon(new ImageIcon("res/walk.gif"));
        // weight -1 kilos and ask for food
        handleWeight(-1);
        setTimeout(() -> {
            cat.setIcon(new ImageIcon("res/cat.gif"));
            setShouldEat(true);
            render();
        },10000);
    }
    private void runAction(ActionEvent e) {
        hideButtons();
        cat.setIcon(new ImageIcon("res/run.gif"));
        // weight - 4 kilos and eat a lot
        handleWeight(-4);
        // show eating a lot image and go back to default cat image
        setTimeout(() -> {
            cat.setIcon(new ImageIcon("res/eatingpasta.gif"));
            // gain 5kilos due to eat a lot after running
            handleWeight(5);
            },2000);
        setTimeout(() -> {
            cat.setIcon(new ImageIcon("res/cat.gif"));
            render();
        },4000);

    }
    private void notEatAction(ActionEvent e) {
        // less 2 kilos each time it does not eat
        handleWeight(-2);
    }
    private void eatALotAction(ActionEvent e) {
        hideButtons();
        // gain 5 kilos
        handleWeight(5);
        cat.setIcon(new ImageIcon("res/eatalot.gif"));
        setTimeout(() -> {
            // sleep after eating a lot
            sleepAction(e);
        },2000);
    }
    private void eatLittleAction(ActionEvent e) {
        hideButtons();
        // gain 1 kilo
        handleWeight(1);
        cat.setIcon(new ImageIcon("res/eatsushi.gif"));
        setTimeout(() -> {
            // go back to initial cat image
            cat.setIcon(new ImageIcon("res/cat.gif"));
        },2000);
        render();
    }

    public void handleWeight(int kilos){
        var playerWeight = player.getWeigth();
        player.setWeigth(playerWeight + kilos);
        if(player.getWeigth() <= 0){
            player.setStatus(PlayerStatus.DEAD);
            render();
        } else if (player.getWeigth() > 20) {
            player.setStatus(PlayerStatus.EXPLODED);
            render();
        }
        numberOfKilos.setText(String.valueOf(player.getWeigth()));
    }

    public void hideButtons(){
        jButtonComerMuito.setVisible(false);
        jButtonComerPouco.setVisible(false);
        jButtonNaoComer.setVisible(false);
        jButtonRun.setVisible(false);
        jButtonWalk.setVisible(false);
        jButtonSkip.setVisible(false);
        jButtonSleep.setVisible(false);
    }
    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}
