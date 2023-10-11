import javax.swing.*;
import java.awt.event.ActionEvent;

// author Ana Burtet
public class Screen extends JFrame {
    // main class of the program
    Player player;
    JButton jButtonSleep,
            jButtonSkip,
            jButtonEatALot,
            jButtonNotEat,
            jButtonEatLittle,
            jButtonRun,
            jButtonWalk;
    JLabel imgLabel,
            labelDays,
            numberOfDays,
            labelWeight,
            labelName,
            playerName,
            numberOfKilos;
    boolean shouldEat = false;
    int optionNumber;
    public void setShouldEat(boolean shouldEat) {
        this.shouldEat = shouldEat;
    }
    public static void main(String []args){
        // method that creates the window
        new Screen();
    }

    public void render(){
        // control what to render on the screen
        switch(player.getStatus()){
            case EXPLODED:
                //calls exploded method
                exploded();
                return;
            case DEAD:
                //calls dead method
                dead();
                return;
        }

        //if shouldEat is true optionNumber is 0, if shouldEat is false then generate random number
        optionNumber = shouldEat ? 0 : generateRandomNumber();

        hideButtons();
        switch (optionNumber) {
            case 0:
                // when optionNumber is 0, calls eatView method to render eat view
                eatView();
                break;
            case 1:
                // when optionNumber is 1, calls sleepView to render it
                sleepView();
                break;
            case 2:
                // when optionNumber is 2, calls exerciseView to render it
                exerciseView();
                break;
        }
    }
    public Screen(){
        // class constructor, set all initial states
        player = new Player();
        jButtonSleep = new JButton("Sleep");
        jButtonSleep.setBounds(200,600, 250, 70);

        add(jButtonSleep);

        jButtonSleep.addActionListener(this::sleepAction);

        jButtonSkip = new JButton("Ignore");
        jButtonSkip.setBounds(600,600, 250, 70);

        add(jButtonSkip);

        jButtonSkip.addActionListener(this::skipAction);

        jButtonEatALot = new JButton("Eat a lot");
        jButtonEatALot.setBounds(100,600, 250, 70);

        add(jButtonEatALot);

        jButtonEatALot.addActionListener(this::eatALotAction);

        jButtonEatLittle = new JButton("Eat little");
        jButtonEatLittle.setBounds(400,600, 250, 70);

        add(jButtonEatLittle);

        jButtonEatLittle.addActionListener(this::eatLittleAction);

        jButtonNotEat = new JButton("Don't eat");
        jButtonNotEat.setBounds(700,600, 250, 70);

        add(jButtonNotEat);

        jButtonNotEat.addActionListener(this::notEatAction);

        jButtonRun = new JButton("Run");
        jButtonRun.setBounds(200,600, 250, 70);

        add(jButtonRun);

        jButtonRun.addActionListener(this::runAction);

        jButtonWalk = new JButton("Walk");
        jButtonWalk.setBounds(600,600, 250, 70);

        add(jButtonWalk);

        jButtonWalk.addActionListener(this::walkAction);

        imgLabel = new JLabel(new ImageIcon("res/background.jpg"));

        //creates labelName
        labelName = new JLabel("Name");
        labelName.setBounds(10, 10, 200, 20); // set position and length
        labelName.setHorizontalAlignment(JLabel.LEFT); // Align text to left
        labelName.setVerticalAlignment(JLabel.TOP); // Align text to top

        // create and set text on the top left
        playerName = new JLabel(player.getName());
        playerName.setBounds(10, 30, 200, 20); // set position and length
        playerName.setHorizontalAlignment(JLabel.LEFT); // Align text to left
        playerName.setVerticalAlignment(JLabel.TOP); // Align text to top

        // adds labelName and playerName to the frame
        add(labelName);
        add(playerName);

        // create labelDays
        labelDays = new JLabel("Days");
        labelDays.setBounds(200, 10, 200, 20); // set position and length
        labelDays.setHorizontalAlignment(JLabel.LEFT); // Align text to left
        labelDays.setVerticalAlignment(JLabel.TOP); // Align text to top

        // create and set text on the top left
        numberOfDays = new JLabel("0");
        numberOfDays.setBounds(200, 30, 200, 20); // set position and length
        numberOfDays.setHorizontalAlignment(JLabel.LEFT); // Align text to left
        numberOfDays.setVerticalAlignment(JLabel.TOP); // Align text to top

        // creates labelWeight
        labelWeight = new JLabel("Weight");
        labelWeight.setBounds(400, 10, 200, 20);
        labelWeight.setHorizontalAlignment(JLabel.LEFT);
        labelWeight.setVerticalAlignment(JLabel.TOP);

        numberOfKilos = new JLabel(String.valueOf(player.getWeight()));
        numberOfKilos.setBounds(400, 30, 200, 20);
        numberOfKilos.setHorizontalAlignment(JLabel.LEFT);
        numberOfKilos.setVerticalAlignment(JLabel.TOP);

        add(labelDays); // add objects to the frame
        add(numberOfDays);
        add(labelWeight);
        add(numberOfKilos);
        add(imgLabel);
        add(player.getImage());

        setTitle("Tamagotchi");
        setSize(1024,728);
        // make it possible to close the program on the x button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // center the program on screen
        setLocationRelativeTo(null);
        // set the resize of the screen to false
        this.setResizable(false);
        // display the screen
        setVisible(true);

        render();
    }

    private void exploded(){
        // show exploded gif then gameover gif
        player.setImage("res/exploded.gif");
        setTimeout(() -> {
            player.setImage("res/gameover.gif");
        },4000);
    }

    private void dead(){
        // hide buttons and then show gameover gif
        hideButtons();
        player.setImage("res/gameover.gif");
    }
    public int generateRandomNumber(){
        // generates a random number from 0 to 2
        // cast int is ignoring the number that comes after the dot
        return (int) (Math.random() * 3);
    }
    private void eatView(){
        // show eat buttons
        jButtonEatALot.setVisible(true);
        jButtonEatLittle.setVisible(true);
        jButtonNotEat.setVisible(true);
        setShouldEat(false);
    }

    private void sleepView(){
        // show sleep buttons
        jButtonSleep.setVisible(true);
        jButtonSkip.setVisible(true);
    }
    private void exerciseView(){
        //show exercise buttons
        jButtonRun.setVisible(true);
        jButtonWalk.setVisible(true);
    }
    public void sleepAction(ActionEvent e) {
        // method that is called on sleep button click
        hideButtons();
        player.sleep();
        player.setImage("res/sleepcat.gif");
        setTimeout(() -> {
            player.setImage("res/cat.gif");
            numberOfDays.setText(String.valueOf(player.getAge()));
            render();
            },2000);
    }
    public void skipAction(ActionEvent e) {
        // method that is called on skip button click
        player.skipSleep();
        if(player.getStatus() == PlayerStatus.SLEEPY){
            player.setImage("res/sleepcat.gif");
        }
        render();
    }
    private void walkAction(ActionEvent e) {
        // method that is called on walk button click
        hideButtons();
        player.setImage("res/walk.gif");
        // weight -1 kilos and ask for food
        handleWeight(-1);
        setTimeout(() -> {
            player.setImage("res/cat.gif");
            setShouldEat(true);
            render();
        },10000);
    }
    private void runAction(ActionEvent e) {
        // method that is called on run button click
        hideButtons();
        player.setImage("res/run.gif");
        setTimeout(() -> {
            // weight - 4 kilos and eat a lot
            handleWeight(-4);
        },1000);
        // show eating a lot image and go back to default cat image
        setTimeout(() -> {
            if(player.getStatus() == PlayerStatus.DEAD) return;
            player.setImage("res/eatingpasta.gif");
            // gain 5kilos due to eat a lot after running
            handleWeight(5);
        },2000);
        setTimeout(() -> {
            if(player.getStatus() == PlayerStatus.DEAD) return;
            player.setImage("res/cat.gif");
            render();
        },4000);
    }
    private void notEatAction(ActionEvent e) {
        //method that is called on notEat button click
        // less 2 kilos each time it does not eat
        handleWeight(-2);
    }
    private void eatALotAction(ActionEvent e) {
        // method that is called on eat a lot button click
        hideButtons();
        // gain 5 kilos
        handleWeight(5);
        player.setImage("res/eatalot.gif");
        setTimeout(() -> {
            // sleep after eating a lot
            sleepAction(e);
        },2000);
    }
    private void eatLittleAction(ActionEvent e) {
        // method that is called on eat a little button click
        hideButtons();
        // gain 1 kilo
        handleWeight(1);
        player.setImage("res/eatsushi.gif");
        setTimeout(() -> {
            // go back to initial cat image
            player.setImage("res/cat.gif");
            render();
        },2000);
    }

    public void handleWeight(int kilos){
        // method that handles the player weight
        var playerWeight = player.getWeight();
        player.setWeight(playerWeight + kilos);
        if(player.getWeight() <= 0){
            player.setStatus(PlayerStatus.DEAD);
            render();
        } else if (player.getWeight() > 20) {
            player.setStatus(PlayerStatus.EXPLODED);
            render();
        }
        numberOfKilos.setText(String.valueOf(player.getWeight()));
    }

    public void hideButtons(){
        // hide all buttons
        jButtonEatALot.setVisible(false);
        jButtonEatLittle.setVisible(false);
        jButtonNotEat.setVisible(false);
        jButtonRun.setVisible(false);
        jButtonWalk.setVisible(false);
        jButtonSkip.setVisible(false);
        jButtonSleep.setVisible(false);
    }
    public static void setTimeout(Runnable runnable, int delay){
        // method that waits for a time(delay) to run something(runnable)
        new Thread(() -> {
            // creates a subdivision of the program that runs in parallel to the program
            try {
                // will wait the delay time until running the runnable
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}
