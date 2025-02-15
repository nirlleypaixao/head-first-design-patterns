package chapter3.starbuzzcoffee.main;

public abstract class CondimentDecorator extends Beverage {

    Beverage beverage;

    public abstract String getDescription();
}