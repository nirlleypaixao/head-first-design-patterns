package chapter1.theduckgame;

public class MuteQuack implements QuackBehavior {
    public void quack() {
        // do nothing - can't quack!
        System.out.println("Mute Quack");
    }

}
