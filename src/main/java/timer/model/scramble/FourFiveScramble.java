package timer.model.scramble;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import timer.model.Moves;

import java.util.Random;

/**
 * Created by Jakub Janusz on 2016-02-04.
 */
public class FourFiveScramble implements IScramble {

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass().toString());
    private Random random;
    private Moves[] moves;
    private int length;
    private final Moves[] availableMoves = {
            Moves.R, Moves.U, Moves.F,
            Moves.L, Moves.D, Moves.B
    };
    private final String[] availablePostfixes = {
            "", "'", "2", "w", "w2"
    };

    public FourFiveScramble(int size) {
        if(size == 4) length = 40;
            else if(size == 5) length = 60;
            else length = -1;
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
        logger.debug("Generating scramble with size {}", length == 40 ? 4 : 5);
        for(int i = 0; i < length; i++) {
            getMove(i);
        }
    }

    private void getMove(int i) {
        //start with any move
        if(i == 0) {
            moves[i] = availableMoves[random.nextInt(availableMoves.length)];
            moves[i].setPostfix(availablePostfixes[random.nextInt(availablePostfixes.length)]);
        }
        //second move - check for R R or Rw Rw
        else if(i == 1) {
            do {
                moves[i] = availableMoves[random.nextInt(availableMoves.length)];
                moves[i].setPostfix(availablePostfixes[random.nextInt(availablePostfixes.length)]);
            } while ((moves[i].getIndex() == moves[i - 1].getIndex()) &&
                            ((moves[i].getLayers() == moves[i - 1].getLayers()))
                    );
        }
        //any further move - as above and check for R R R and R L R
        else {
            do {
                moves[i] = availableMoves[random.nextInt(availableMoves.length)];
                moves[i].setPostfix(availablePostfixes[random.nextInt(availablePostfixes.length)]);
            } while ((moves[i].getIndex() == moves[i - 1].getIndex()) &&
                            ((moves[i].getLayers() == moves[i - 1].getLayers())) ||
                    ((moves[i].getIndex() == moves[i - 1].getIndex()) &&
                            (moves[i].getIndex() == moves[i - 2].getIndex())) ||
                    ((moves[i].getIndex() == moves[i - 2].getIndex()) &&
                            (moves[i].getLayers() == moves[i - 2].getLayers()))
                    );
        }

        //add postfix (including no postfix)

    }

}
