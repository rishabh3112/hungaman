package hungman;
import java.util.*;

class Hungman {

    String word;
    StringBuilder current;
    public char[] inputs;
    public int guessLeft;
    public int guessDone;
    
    Hungman () throws Exception{
        Random random = new Random();
        Lexion lexion = new Lexion();
        
        int length = lexion.getWordCount();
        this.word = lexion.getWord(random.nextInt(length));
        
        char[] chars = new char[this.word.length()];
        Arrays.fill(chars, '_');
        
        this.current = new StringBuilder(new String(chars));
        this.guessLeft = 8;
        this.inputs = new char[60];
        this.guessDone = 0;
    }

    public void move(char guess) {
        boolean isUpdated = false;
        inputs[guessDone] = guess;
        guessDone++;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                isUpdated = true;
                this.current.setCharAt(i, guess);
            }
        }
        if (!isUpdated) this.guessLeft--;
    }

    public String getCurrent() {
        return current.toString();
    }
    public String getWord() {
        return word;
    }
    public static void main(String[] args) throws Exception{
        Hungman hungman = new Hungman();
        Scanner user = new Scanner(System.in);

        while (hungman.guessLeft > 0) {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.print("Current state of word:\t".concat(hungman.getCurrent()).concat("\nGuesses made by you:\t"));
            for (int i = 0; i<hungman.guessDone; i++) {
                System.out.print(Character.toString(hungman.inputs[i]).concat(" "));
            }
            System.out.println("\nGuesses left to win:\t" + Integer.toString(hungman.guessLeft));
            System.out.print("Enter your guess:\t");
            
            char guess = user.next().toUpperCase().charAt(0);
            hungman.move(guess);
            
            if (hungman.isWinner()) {
                System.out.println("\n ----------------\nYes!! Word is indeed " + hungman.getWord());
                System.out.println("You just saved a life!!");
                user.close();
                return;
            }
            if (hungman.isLoser()) {
                System.out.println("\n ----------------\nBooo!! Word to be guessed was " + hungman.getWord());
                System.out.println("You should be in a JAIL now :(");
                user.close();
                return;
            }
        }
        user.close();
        return;
    }

    private boolean isLoser() {
        if (this.guessLeft <= 0) return true;
        return false;
    }

    private boolean isWinner() {
        if (word.equals(current.toString())) {
            return true;
        }
        return false;
    }
}
