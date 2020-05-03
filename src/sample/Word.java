package sample;

import java.util.ArrayList;

public class Word {
    String word;
    ArrayList<String> next;
    public Word(String word, ArrayList<String> list){
        this.word = word;
        this.next = list;
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
    public void setNext(ArrayList<String> next){this.next = next;}
}
