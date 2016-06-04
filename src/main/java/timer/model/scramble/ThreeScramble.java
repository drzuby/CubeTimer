package timer.model.scramble;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import timer.model.Moves;
import java.util.Random;

/**
 * Created by Jakub Janusz on 2016-02-04.
 */
public class ThreeScramble implements IScramble {

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass().toString());
    private Random random;
    private Moves[] moves;
    private int length = 25;
    private final Moves[] availableMoves = {
            Moves.R, Moves.U, Moves.F,
            Moves.L, Moves.D, Moves.B
    };
    private final String[] availablePostfixes = {
            "", "'", "2"
    };

    public ThreeScramble() {
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
        logger.debug("Generating scramble with size 3");
        for(int i = 0; i < length; i++) {
            getMove(i);
        }
    }

    private void getMove(int i) {
        //start with any move
        if(i == 0) {
            moves[i] = availableMoves[random.nextInt(availableMoves.length)];
        }
        //second move - check for R R
        else if (i == 1) {
            do {
                moves[i] = availableMoves[random.nextInt(availableMoves.length)];
            } while (moves[i].getIndex() == moves[i - 1].getIndex());
        }
        //any further move - as above and R L R
        else {
            do {
                moves[i] = availableMoves[random.nextInt(availableMoves.length)];
            } while ((moves[i].getIndex() == moves[i - 1].getIndex()) ||
                    (((moves[i].getIndex() % 3) == (moves[i - 1].getIndex() % 3)) &&
                            ((moves[i].getIndex() % 3) == (moves[i - 2].getIndex() % 3)))
                    );
        }

        //add postfix (including no postfix)
        moves[i].setPostfix(availablePostfixes[random.nextInt(availablePostfixes.length)]);
    }

}
