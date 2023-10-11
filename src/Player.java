import javax.swing.*;
import java.awt.*;

public class Player {
    private String name;
    private int age = 0;
    private int weigth = 1;
    private int x,y;
    private Image imagem;
    private PlayerStatus status = PlayerStatus.ALIVE;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int sleepCount = 0;
    public void load(){
        ImageIcon referencia = new ImageIcon("res/cat.gif");
        imagem = referencia.getImage();
    }

    private void updateStatus(){
        if(weigth > 20){
            status = PlayerStatus.EXPLODED;
        } else if (weigth <= 0) {
            status = PlayerStatus.DEAD;
        }
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    public int getWeigth() {
        return weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    public void eat(String value){
        // do something
        switch (value){
            case "nao comer":
                weigth = weigth - 2;
            case "comer pouco":
                weigth = weigth + 5;
            default:
                weigth = weigth + 1;
        }
        updateStatus();
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
        // update aga in one day
        age++;
        updateStatus();
        // change cat gif
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }
}
