package hungman;

import java.io.BufferedReader;
import java.io.FileReader;

class Lexion {
    String[] lexionStrings;
    int wordCount;
    Lexion () throws Exception {
        String[] words = new String[3011];
        FileReader fr = new FileReader("./hungman/words.txt");
        BufferedReader wr = new BufferedReader(fr);

        int i = 0;
        String line = null;
        while ((line = wr.readLine()) != null) {
           words[i] = line.toUpperCase();
           i++;
        }
        this.lexionStrings = words;
        this.wordCount = i;

        wr.close();
        fr.close();
    }

    public int getWordCount() {
        return wordCount;
    }

    public String getWord(int index) {
        if (index < wordCount) return lexionStrings[index];
        else return "";
    };
}