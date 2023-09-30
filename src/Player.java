import javax.swing.*;
import java.awt.*;

public class Player {
    private String name;
    private int age = 0;
    private int weigth;
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
            // value == Permanecer acordado
            sleepCount++;
            if(sleepCount == 5) {
                sleep();
            }
    }

    private void sleep(){
        // reset quantity of skipped sleep
        sleepCount = 0;
        // update aga in one day
        age++;
        updateStatus();
        // change cat gif
    }

    public void exercise(String value){
        if(value == "run"){
            weigth = weigth - 4;
            eat("comer muito");
        } else {
            // walk
            weigth--;
            // call method to player select eat value
        }
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }
}
