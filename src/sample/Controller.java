package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;


public class Controller {
    @FXML
    public Label helloWorld;
    public TextArea output;
    public ComboBox comboVal;
    public TextField sentence;
    public TextField length;
    int sentence1;
    int length1;
    String name;
    public Controller(){
        this.length1=0;
        this.sentence1=0;
    }

    @FXML
    public void btnHandler(ActionEvent actionEvent) throws IOException {
        if(comboVal.getValue().equals("Choose Poet")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("No Poet Chosen");
            alert.setContentText("Please choose a poet.");
            alert.showAndWait();
            return;
        }
        else{
            switch(comboVal.getValue().toString()){
                case "William Shakespeare":
                    System.out.println("William Shakespeare");
                    name = "res/shakespeare.txt";
                    break;
                case "J.R.R Tolkien":
                    System.out.println("J.R.R Tolkien");
                    name = "res/tolkien.txt";
                    break;
                case "Emily Elizabeth Dickinson":
                    name = "res/dickinson.txt";
                    break;

            }
        }
        try{
            length1 =  Integer.parseInt(length.getText());
            sentence1 =  Integer.parseInt(sentence.getText());
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Invalid Sentence Inputs");
            alert.setContentText("One of the sentence inputs was not an integer.");
            alert.showAndWait();
            return;
        }
        output.setText(createPoem());
    }
    @FXML
    public void setData(){
        output.setText("test");
    }

    public String createPoem() throws IOException {
        ArrayList<String> words = new ArrayList<>();
        HashSet<String> unique = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(name));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] wordSplit = line.split(" ");
            for(String string: wordSplit){
                string = string.replace(",","");
                string = string.replace(".","");
                string = string.toLowerCase();
                if(string.length()>0){
                    words.add(string);
                    unique.add(string);
                }
            }
        }
        ArrayList<Word> wordList = new ArrayList<>();
        for(String string: unique){
            ArrayList<String> follows = new ArrayList<>();
            for(int i=0;i<words.size()-1;i++){
                if(words.get(i).equals(string)){
                    follows.add(words.get(i+1));
                }
            }
            Word word = new Word(string, follows);
            wordList.add(word);
        }
        for(int i =0;i<wordList.size()-1;i++){
            if(wordList.get(i).getNext().size()==0){
                ArrayList<String> nextWord = new ArrayList<>();
                nextWord.add(wordList.get(ThreadLocalRandom.current().nextInt(0, wordList.size())).getWord());
                wordList.get(i).setNext(nextWord);
            }
        }
       String poem = generatePoem(wordList);
        return poem;
    }

    public String generatePoem(ArrayList<Word> wordList){
        String finalPoem ="";
        for(int l=0;l<sentence1;l++){
            int ran = ThreadLocalRandom.current().nextInt(0, wordList.size());
            Word initial = wordList.get(ran);
            String firstWord = initial.getWord().substring(0,1).toUpperCase()+ initial.getWord().substring(1);
            finalPoem+= firstWord + " ";
            int newran = 0;
            if(initial.getNext().size()<=1){
            }
            else{
                newran = ThreadLocalRandom.current().nextInt(0, initial.getNext().size());
            }
            ArrayList<String> children = initial.getNext();
            for(int i =0;i<(length1-1);i++){
                Word tempWord = null;
                for(Word word : wordList){
                    if(word.getWord().equals(children.get(newran))){
                        tempWord = word;
                    }
                }
                initial = tempWord;
                finalPoem += initial.getWord() +" ";
                children = initial.getNext();
                if(initial.getNext().size()<=1){
                    newran =0;
                }
                else{
                    newran = ThreadLocalRandom.current().nextInt(0, initial.getNext().size());
                }
            }
            finalPoem = finalPoem.substring(0,finalPoem.length()-1)+". ";

        }
        return finalPoem;
    }


}
