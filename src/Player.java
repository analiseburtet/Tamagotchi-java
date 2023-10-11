import javax.swing.*;

//author Ana Burtet
public class Player {
    private String name;
    private int age = 0, weight = 1;
    private JLabel image;
    private PlayerStatus status = PlayerStatus.ALIVE;
    private int sleepCount = 0;

    public String getName() {
        return name;
    }
    public Player() {
        // constructor, Jett is my cat's name :)
        this.name = "Jett";
        this.image = new JLabel(new ImageIcon("res/cat.gif"));
    }
    public int getAge() {
        return age;
    }
    public JLabel getImage() {
        return image;
    }
    public void setImage(String url) {
        this.image.setIcon(new ImageIcon(url));
    }
    private void updateStatus(){
        if(weight > 20){
            status = PlayerStatus.EXPLODED;
        } else if (weight <= 0) {
            status = PlayerStatus.DEAD;
        }
        if(age >= 15) status = PlayerStatus.DEAD;
    }
    public PlayerStatus getStatus() {
        return status;
    }
    public void setStatus(PlayerStatus status) {
        this.status = status;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void skipSleep(){
        // TO CHECK IF CAT SHOULD BE FORCED TO SLEEP
        sleepCount++;
        if(sleepCount == 5) {
            status = PlayerStatus.SLEEPY;
        }
    }

    public void sleep(){
        // reset quantity of skipped sleep
        sleepCount = 0;
        // update age in one day
        age++;
        updateStatus();
    }
}
