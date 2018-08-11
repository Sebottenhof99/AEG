package presentation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.PresDAOTransferLogic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    public TextField browsenumber;
    public TextField parentSKU;
    public TextField variation;

    PresDAOTransferLogic presDAOTransferLogic = new PresDAOTransferLogic();

    public void selectFile(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Excel Datei auswählen");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xls, *.xlsx)", "*.xls", "*.xlsx");
        chooser.getExtensionFilters().add(extFilter);

        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            selectedFile.setText(file.getPath());

        }
        presentMaterial();

    }

    public void presentMaterial(){
        ObservableList<String> list = presDAOTransferLogic.getMaterials();

        comboBox1.setItems(list);
        comboBox1.valueProperty().addListener(new ChangeListener<String>(){


            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                presentAdditionalMaterialParameters();
                setBulletpoints();
                setGeneralInformation();
                detailedAnchorPane.setVisible(true);
            }
        });
        }


    public void presentAdditionalMaterialParameters() {
        ObservableList<String> list = presDAOTransferLogic.getAdditionalMaterialParameters(comboBox1.getSelectionModel().getSelectedItem().toString());


        if (!list.isEmpty()) {
            comboBox2.setItems(list);
            comboBox2.setDisable(false);

            comboBox2.valueProperty().addListener(new ChangeListener<String>() {


                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                    System.out.println(comboBox1.getSelectionModel().getSelectedItem().toString());
                    detailedAnchorPane.setVisible(true);
                }
            });
        } else {
            comboBox2.setDisable(true);
        }
    }

    public void setBulletpoints(){
        ArrayList<String >bulletpoints  = presDAOTransferLogic.getBulletpoints(comboBox1.getSelectionModel().getSelectedItem().toString());

        if (!isNullOrEmpty(bulletpoints.get(0))) {
            bulletPoint1.setText(bulletpoints.get(0));
        }

        if (!isNullOrEmpty(bulletpoints.get(1))) {
            bulletPoint2.setText(bulletpoints.get(1));
        }
        if (!isNullOrEmpty(bulletpoints.get(2))) {
            bulletPoint3.setText(bulletpoints.get(2));
        }
        if (!isNullOrEmpty(bulletpoints.get(3))) {
            bulletPoint4.setText(bulletpoints.get(3));
        }
        if (!isNullOrEmpty(bulletpoints.get(4))) {
            bulletPoint5.setText(bulletpoints.get(4));
        }
    }

    private boolean isNullOrEmpty(String s){
        if (s==null||s.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public void setGeneralInformation(){
        ArrayList<String >generalInformation  = presDAOTransferLogic.getGeneralInformation(comboBox1.getSelectionModel().getSelectedItem().toString());

        articlename.setText(generalInformation.get(0));
        browsenumber.setText(generalInformation.get(2));
        variation.setText(generalInformation.get(4));


    }




}

