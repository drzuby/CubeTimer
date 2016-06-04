package timer.model;

import timer.model.scramble.*;

/**
 * Created by Jakub Janusz on 2016-02-04.
 */
public class Session {

    private int cube;
    private IScramble scramble;

    public Session(int cube) {
        this.cube = cube;
        setScramble();
    }

    public String getScramble() {
        return scramble.getMoves();
    }

    private void setScramble() {
        switch (cube) {
            case 2:
                scramble = new TwoScramble();
                break;
            case 3:
                scramble = new ThreeScramble();
                break;
            case 4:
                scramble = new FourFiveScramble(4);
                break;
            case 5:
                scramble = new FourFiveScramble(5);
                break;
            case 6:
                scramble = new SixSevenScramble();
                break;
            case 7:
                scramble = new SixSevenScramble();
                break;
            default:
                scramble = null;
        }
    }

}
