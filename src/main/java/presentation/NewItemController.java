package presentation;

import defines.Defines;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> variations = FXCollections.observableArrayList();
        variations.add(Defines.VariationThemes.SIZE);
        variations.add(Defines.VariationThemes.COLOR);
        variations.add(Defines.VariationThemes.SIZECOLOR);

        variationDropdown.setItems(variations);

    }

    public void addNewMaterial(ActionEvent actionEvent) {

    }


}
