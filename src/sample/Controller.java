package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;


public class Controller {
    @FXML
    public Label helloWorld;
    public Label output;

    public Controller(){

    }

    @FXML
    public void sayHelloWorld(ActionEvent actionEvent) throws IOException {
        output.setText(createPoem());
        System.out.println(output);
        helloWorld.setText("rand");


    }
    @FXML
    public void setData(){
        output.setText("test");
    }

    public String createPoem() throws IOException {
        ArrayList<String> words = new ArrayList<>();
        HashSet<String> unique = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader("res/tolkein.txt"));
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
        for(int i =0;i<100;i++){
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
        finalPoem += finalPoem.substring(0,finalPoem.length()-1)+".";
        return finalPoem;
    }


}
