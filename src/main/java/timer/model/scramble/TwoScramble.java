package timer.model.scramble;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import timer.model.Moves;

import java.util.Random;

/**
 * Created by Jakub Janusz on 2016-02-04.
 */
public class TwoScramble implements IScramble {

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass().toString());
    private Random random;
    private Moves[] moves;
    private int length = 10;
    private final Moves[] availableMoves = {
            Moves.R, Moves.U, Moves.F
    };
    private final String[] availablePostfixes = {
            "", "'", "2"
    };

    public TwoScramble() {
        this.random = new Random(System.currentTimeMillis());
        this.moves = new Moves[length];
        generate();
    }

    @Override
    public String getMoves() {
        String result = "";
        for (Moves move : moves) {
            result += move.toString();
            result += " ";
        }
        return result;
    }

    private void generate() {
        logger.debug("Generating scramble with size 2");
        for(int i = 0; i < length; i++) {
            getMove(i);
        }
    }

    private void getMove(int i) {
        //start with any move
        if(i == 0) {
            moves[i] = availableMoves[random.nextInt(availableMoves.length)];
        }
        //any further move - check for R R
        else {
            do {
                moves[i] = availableMoves[random.nextInt(availableMoves.length)];
            } while (moves[i].getIndex() == moves[i - 1].getIndex());
        }

        //add postfix (including no postfix)
        moves[i].setPostfix(availablePostfixes[random.nextInt(availablePostfixes.length)]);
    }

}
