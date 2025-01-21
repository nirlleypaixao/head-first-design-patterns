package chapter1.theduckgame;

public class MuteQuack implements QuackComportamento {
    public void quack() {
        // do nothing - can't quack!
        System.out.println("Mute Quack");
    }

}
