// Brian Brenner
// Aug 1 2021
// Program for counting words in a text file

import java.util.*;
import java.io.*;

public class WordCount {
    public static void main(String args[]){

        File file = new File("Coding_Challenge.txt");

        try {
            Scanner fileScanner = new Scanner(file);

            String all = "";

            // concatinate each line in file to all
            while (fileScanner.hasNextLine()) {
                all += fileScanner.nextLine() + " ";
            }

            ArrayList<String> sentences = new ArrayList<String>();

            // split sentences by period, and add to ArrayList sentences with whitespaces stripped
            String[] splitPeriod = all.split("\\."); 
            for (String v: splitPeriod) {
                sentences.add(v.strip());
            }

            ArrayList<String> words = new ArrayList<String>();

            // maybe not the best looking way to seperate words, but works to remove characters not in the alphabet, make them lowercase, and split by space. 
            String[] onlyWords = all.replaceAll("-", " ").replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
            for (String v: onlyWords) {
                words.add(v);
            }
            
            // compound words are treated as seperate words
            System.out.println("Total words: " + words.size());

            ArrayList<Word> wordCount = new ArrayList<Word>();

            // compare strings at indexes and increment if found, add if it didn't exist in WordCount prior
            for (String v : words){
                boolean noExist = true;
                for (int i = 0; i < wordCount.size(); i++){
                    if (v.equals(wordCount.get(i).getWord())){
                        wordCount.get(i).increment();
                        noExist = false;
                    }
                }
                if (noExist){
                    wordCount.add(new Word(v));
                }
            }
            
            Collections.sort(wordCount); 

            System.out.println();
            System.out.println("Most used words: ");
            for (int i = 0; i < 10; i++){
                Word cWord = wordCount.get(i);
                System.out.println(cWord.getWord() + " " + cWord.getCount() + " times");
            }

            System.out.println();
            System.out.println("Final sentence with " + "\"" + wordCount.get(0).getWord()+ "\"" + " in it: ");
            for(int i = sentences.size()-1; i >= 0; i--){
                if (sentences.get(i).contains(wordCount.get(0).getWord())){
                    System.out.println(sentences.get(i) + ".");
                    break;
                }
            }

            fileScanner.close();

        }
        catch(FileNotFoundException e) {
            System.out.println("File not found.");
        }
        
    }
    
}
