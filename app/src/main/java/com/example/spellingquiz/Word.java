package com.example.spellingquiz;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Word {
    private Boolean correct;
    private String word;

    private ArrayList<String> trueWords = new ArrayList<String>(
            Arrays.asList("Octopi", "Ornery", "Literally","Inflammable","Hopefully"
            ,"Irregardless", "Peruse", "Fulsome", "Deprecate", "Poisonous"));

    private ArrayList<String> falseWords = new ArrayList<String>(
            Arrays.asList("Octpi", "Ornary", "Literaly","Imflammable","Hopefuly"
                    ,"Iregardless", "Persue", "Fulsame", "Deprecete", "Poisonuos"));


    public Word(){
        Random ran = new Random();
        correct = ran.nextBoolean();
        if(correct){
            word = trueWords.remove(ran.nextInt(trueWords.size()));
        }else{
            word = falseWords.remove(ran.nextInt(falseWords.size()));
        }
    }
    public Boolean returnBoolean(){
        return correct;
    }

    public String returnWord(){
        return  word;
    }
}
