package presentation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.ExcelReader;
import logic.MainLogic;

import java.io.File;
import java.io.IOException;

public class FXMLBspController{
    public Button selectFileButton;
    public TextField selectedFile;
    public TextField articlename;
    public TextField bulletPoint1;
    public TextField bulletPoint2;
    public TextField bulletPoint3;
    public TextField bulletPoint4;
    public TextField bulletPoint5;
    public ComboBox comboBox1;
    public ComboBox comboBox2;
    public TextField priceField;
    public AnchorPane detailedAnchorPane;

    public void selectFile(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Excel Datei auswählen");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xls, *.xlsx)", "*.xls", "*.xlsx");
        chooser.getExtensionFilters().add(extFilter);

        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            selectedFile.setText(file.getPath());
            MainLogic mainLogic = new MainLogic();
            mainLogic.action(file.getPath());
        }
        fillComboBox1();

    }



    public void fillComboBox1(){
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("test1");
        list.add("test2");
        list.add("test3");

        comboBox1.setItems(list);

        comboBox1.valueProperty().addListener(new ChangeListener<String>(){


            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                System.out.println(comboBox1.getSelectionModel().getSelectedItem().toString());
                detailedAnchorPane.setVisible(true);
            }
        });
        }


}

