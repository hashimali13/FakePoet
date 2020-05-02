package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class Controller {
    public Label helloWorld;
    Word word;

    public Controller(){
        word = new Word();
    }

    public void sayHelloWorld(ActionEvent actionEvent) {
        System.out.println("aaaaa");
        helloWorld.setText(word.getWord());

    }
}
