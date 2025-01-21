package chapter1.theduckgame;

public abstract class Duck {

    QuackComportamento quackComportamento;
    VoaComportamento voaComportamento;

    public abstract void display();

    public void fazQuack() {
        quackComportamento.quack();
    }

    public void podeVoar() {
        voaComportamento.voa();
    }

    public void nada() {
        System.out.println("All ducks float, even decoys!");
    }
}
