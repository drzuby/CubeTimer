package timer.model;

/**
 * Created by Jakub Janusz on 2016-02-04.
 */
public enum Moves {

    R(0) {
        public String toString() {
            return "R" + this.postfix;
        }
    },
    U(1) {
        public String toString() {
            return "U" + this.postfix;
        }
    },
    F(2) {
        public String toString() {
            return "F" + this.postfix;
        }
    },
    L(3) {
        public String toString() {
            return "L" + this.postfix;
        }
    },
    D(4) {
        public String toString() {
            return "D" + this.postfix;
        }
    },
    B(5) {
        public String toString() {
            return "B" + this.postfix;
        }
    };

    private int index;
    protected String postfix;
    protected int layers;

    Moves(int index) {
        this.index = index;
        this.postfix = "";
        this.layers = 1;
    }

    public int getIndex() {
        return this.index;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
        setLayers(postfix.startsWith("w") ? 2 : 1);
    }

    public void setLayers(int layers) {
        this.layers = layers;
    }

    public int getLayers() {
        return this.layers;
    }

}
