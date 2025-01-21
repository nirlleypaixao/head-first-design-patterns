package chapter1.theduckgame;

public class MallardDuck extends Duck {

    public MallardDuck() {
        quackComportamento = new Quack();
        voaComportamento = new VoaComAsas();
    }

    public void display() {
        System.out.println("I'm a real Mallard Duck");
    }
}

