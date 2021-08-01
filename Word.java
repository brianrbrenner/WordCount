public class Word implements Comparable<Word>{

    private String word;

    private int count;

    public Word(String word){
        this.word = word;
        this.count = 1;
    }

    public void increment(){
        this.count++;
    }

    public String getWord(){
        return this.word;
    }

    public int getCount(){
        return this.count;
    } 
    // Overide compareTo function to sort ArrayList order by count in main method 
    @Override
    public int compareTo(Word w){
        if (w.getCount() > this.getCount()){
            return 1;
        }
        else if (w.getCount() < this.getCount()){
            return -1;
        }
        else{
            if (w.getWord().compareTo(this.getWord()) > 0){ // will also sort alphanumerically if count is the same
                return -1;
            }
            else{
                return 1;
            }
        }
    }
}
