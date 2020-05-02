package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        Word word = new Word();
        System.out.println(word.getWord());
        ArrayList<String> ch = main.getWords();
        launch(args);
    }

    public ArrayList<String> getWords() throws IOException {
       ArrayList<String> words = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader("C:\\Users\\hashi\\Documents\\work\\cc\\FakePoet\\res\\tolkein.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] wordSplit = line.split(" ");
            for(String string: wordSplit){
                if(string.length()>0){
                    words.add(string);
                }
            }
        }
        System.out.println(words.toString());
       return words;

    }
}
