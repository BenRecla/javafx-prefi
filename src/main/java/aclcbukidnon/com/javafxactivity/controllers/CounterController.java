package aclcbukidnon.com.javafxactivity.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CounterController {

    @FXML
    private Label labelCount;

    private int count = 0;

    @FXML
    public void onPlusClick() {
        count++;
        labelCount.setText(String.valueOf(count));
    }

    @FXML
    public void onMinusClick() {
        count--;
        labelCount.setText(String.valueOf(count));
    }
}
