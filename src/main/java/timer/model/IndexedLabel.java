package timer.model;

import javafx.scene.control.Label;

/**
 * Created by Jakub Janusz on 2016-02-03.
 */
public class IndexedLabel {

    private int index;
    private Label label = new Label();

    private IndexedLabel() {

    }

    public IndexedLabel(int index, String value) {
        this.index = index;
        this.label.setText(value);
    }

    public String toString() {
        return index + ". " + label.getText();
    }

}
