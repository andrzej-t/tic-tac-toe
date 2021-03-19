package tic_tac_toe;

public class Button extends javafx.scene.control.Button {

    private int value;

    public Button() {
        this.setState(0);
    }

    public void setState(int state) {
        if (state == 0) {
            this.setText("");
            this.value = 0;
        } else if (state == 1) {
            this.setText("X");
            this.value = 1;
        } else  if (state == -1) {
            this.setText("O");
            this.value = -1;
        } else {
            System.out.println("setState() called illegal state");
        }
    }

    public int getValue() {
        return value;
    }

    public void changeState() {
        if (this.value == -1) {
            this.setState(0);
        } else if (this.value == 0) {
            this.setState(1);
        } else if (this.value == 1) {
            this.setState(-1);
        }
    }
}
