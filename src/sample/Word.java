package sample;

import java.util.ArrayList;

public class Word {
    String word;
    ArrayList<String> next;
    public Word(){
        word="test";
    }

    public String getWord() {
        return word;
    }

    public ArrayList<String> getNext() {
        return next;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
