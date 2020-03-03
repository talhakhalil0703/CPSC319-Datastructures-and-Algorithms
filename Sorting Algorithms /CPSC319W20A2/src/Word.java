public class Word {
    private String Word;
    private String sortedWord;

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    public String getSortedWord() {
        return sortedWord;
    }

    public void setSortedWord(String sortedWord) {
        this.sortedWord = sortedWord;
    }

    public Word(String word){
        this.Word = word;
    }

}
