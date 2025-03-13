package org.example.btap_cuoi_khoa_1.view;

import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import org.example.btap_cuoi_khoa_1.manager.AlarmsManager;
import org.example.btap_cuoi_khoa_1.model.Alarm;

public class AlarmCell extends ListCell<Alarm> {
    private final Label alarmLabel = new Label();
    private final Button toggleButton = new Button();
    private final HBox hbox = new HBox(30, alarmLabel, toggleButton);
    public AlarmCell() {
        toggleButton.setOnAction(event -> {
            Alarm alarm = getItem();
            if (alarm != null) {
                alarm.setActive(!alarm.isActive());
                updateItem(alarm, false);

            }
        });
    }

    @Override
    protected void updateItem(Alarm alarm, boolean empty) {
        super.updateItem(alarm, empty);
        if (empty || alarm == null) {
            setGraphic(null);
        } else {
            alarmLabel.setText(alarm.getTime() + " - " + alarm.getMessage());
            if(alarm.isActive()){
                toggleButton.setText("ON");
                toggleButton.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));

            }
            else {
                toggleButton.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                toggleButton.setText("OFF");
            }
            setGraphic(hbox);
        }
    }
}
