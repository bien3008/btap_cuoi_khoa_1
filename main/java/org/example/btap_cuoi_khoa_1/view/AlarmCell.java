package org.example.btap_cuoi_khoa_1.view;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import org.example.btap_cuoi_khoa_1.manager.AlarmsManager;
import org.example.btap_cuoi_khoa_1.manager.UserManager;
import org.example.btap_cuoi_khoa_1.model.Alarm;

public class AlarmCell extends ListCell<Alarm> {
    private final Label alarmLabel = new Label();
    private final Label daysLabel = new Label();
    private final Button toggleButton = new Button();
    private final HBox hbox = new HBox(30, alarmLabel, toggleButton);
    AlarmsManager manager = AlarmsManager.getInstance();
    public AlarmCell() {
        toggleButton.setOnAction(event -> {
            Alarm alarm = getItem();
            if (alarm != null) {
                alarm.setActive(!alarm.isActive());
                manager.getAlarmList().set(getIndex(), alarm);
                updateItem(alarm, false);
                manager.saveAlarm();
            }
        });
    }

    @Override
    protected void updateItem(Alarm alarm, boolean empty) {
        super.updateItem(alarm, empty);
        if (empty || alarm == null) {
            setGraphic(null);
        } else {
            alarmLabel.setText(alarm.getTime() + " - " + alarm.getMessage() + "\n" + alarm.getDays());
//            String daysText = alarm.getDays();
//            daysLabel.setText("Ngày hoạt động: " + (daysText.isEmpty() ? "Chỉ hôm nay" : daysText));
//            daysLabel.setStyle("-fx-text-fill: gray; -fx-font-size: 12px;");
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
