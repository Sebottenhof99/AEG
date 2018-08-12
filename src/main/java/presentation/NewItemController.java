package presentation;

import defines.Defines;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewItemController implements Initializable {

    public TextField material;
    public TextField bulletPoint1;
    public TextField bulletPoint3;
    public TextField bulletPoint2;
    public TextField bulletPoint4;
    public TextField articleName;
    public TextField bulletPoint5;
    public TextField parentSku;
    public TextField searchKeywords;
    public TextField browseNode;
    public TextArea description;
    public ComboBox variationDropdown;
    public Button add;
    public Label infobox;
    public Label descriptionLabel;
    public Label searchKeywordLabel;
    public Label browseNodeLabel;
    public Label parentSkuLabel;
    public Label variationLabel;
    public Label articleNameLabel;
    public Label bp2Label;
    public Label bp1Label;
    public Label subCategoryLabel;
    public Label materialLabel;
    public Label bp4Label;
    public Label bp3Label;
    public Label bp5Label;
    public Button close;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> variations = FXCollections.observableArrayList();
        variations.add(Defines.VariationThemes.SIZE);
        variations.add(Defines.VariationThemes.COLOR);
        variations.add(Defines.VariationThemes.SIZECOLOR);

        variationDropdown.setItems(variations);

    }


    public void addNewMaterial(ActionEvent actionEvent) {
        boolean isDataMissing=false;

        subCategoryLabel.setTextFill(Color.GREEN);
        if( !isDataFilled(material, materialLabel) |
                !isDataFilled(bulletPoint1, bp1Label) |
                !isDataFilled(bulletPoint2, bp2Label) |
                !isDataFilled(bulletPoint3, bp3Label) |
                !isDataFilled(bulletPoint4, bp4Label) |
                !isDataFilled(bulletPoint5, bp5Label) |
                !isDataFilled(articleName, articleNameLabel) |
                !isDataFilled(parentSku, parentSkuLabel) |
                !isDataFilled(browseNode, browseNodeLabel) |
                !isDataFilled(searchKeywords, searchKeywordLabel) |
                !isDataFilled(description, descriptionLabel) |
                !isDataFilled(variationDropdown, variationLabel))
        {
            isDataMissing = true;
            System.out.println("NOT");
        }
        System.out.println(isDataMissing);

        if (isDataMissing){
            infobox.setTextFill(Color.RED);
            infobox.setText("Bitte alle Pflichtfelder eintragen");
        }
        else {
            infobox.setTextFill(Color.ORANGE);
            infobox.setText("Die Daten werden übertragen. Bitte warten...");
        }
    }

    public boolean isDataFilled(TextField field, Label label){
        if(field == null || field.getText().trim().isEmpty()){
            field.clear();
            label.setTextFill(Color.RED);
            return false;
        }
        else{
            label.setTextFill(Color.GREEN);
            return true;
        }
    }

    public boolean isDataFilled(ComboBox field, Label label){
        if(field == null || field.getSelectionModel().isEmpty()){
            label.setTextFill(Color.RED);
            return false;
        }
        else{
            label.setTextFill(Color.GREEN);
            return true;
        }
    }

    public boolean isDataFilled(TextArea field, Label label){
        if(field == null || field.getText().trim().isEmpty()){
            label.setTextFill(Color.RED);
            return false;
        }
        else{
            label.setTextFill(Color.GREEN);
            return true;
        }
    }


    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
