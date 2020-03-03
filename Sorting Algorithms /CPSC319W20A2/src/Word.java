public class Word {
    private String word;
    private String sortedWord;

    public Word(String word) {
        this.word = word;
        sortWord();
    }

    public void printWordAndSortedWord() {
        System.out.println(word + " " + sortedWord);
    }

    public String getWord() {
        return word;
    }

    public String getSortedWord() {
        return sortedWord;
    }

    private void sortWord() {
        this.sortedWord = word;
        //Implementing insertion sort
        int len = this.sortedWord.length();
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                for (int j = i; j > 0 && this.sortedWord.charAt(j - 1) > this.sortedWord.charAt(j); j--) {
                    swapChars(j, j - 1);
                }
            }
        }

    }

    private void swapChars(int a, int b) {
        char temp = this.sortedWord.charAt(a);
        char[] stringArray = sortedWord.toCharArray();
        stringArray[a] = this.sortedWord.charAt(b);
        stringArray[b] = temp;
        sortedWord = String.valueOf(stringArray);
    }

    @Override
    public String toString(){
        return this.word;
    }
}
