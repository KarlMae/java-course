package sample;

import javafx.scene.shape.Rectangle;

public class Snake {
    private Rectangle head;
    private double size = 10;

    public Snake(double x, double y, double size){
        this.size = size;
        int speed = 0;
        double dx, dy;
        head = new Rectangle(x, y, size, size);
    }

    public void move() {
        //move snake
        head.setX(head.getX() + 1);
    }

    public Rectangle getHead() {
        return head;
    }

    public void setDeltas(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
