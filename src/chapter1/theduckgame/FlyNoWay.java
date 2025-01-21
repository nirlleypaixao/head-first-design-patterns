package chapter1.theduckgame;

public class FlyNoWay implements FlyBehavior {
    public void fly() {
        //do nothing - can't fly!
        System.out.println("I'm can't fly");
    }
}
