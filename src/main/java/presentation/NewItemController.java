package presentation;

import defines.Defines;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.AdditionNewItem;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.TreeMap;

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
    public Label materialLabel;
    public Label bp4Label;
    public Label bp3Label;
    public Label bp5Label;
    public Button close;
    public VBox SubclassVbox;
    public Button addSubclassRow;

    private ObservableList<HBox> subclassSet;


    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> variations = FXCollections.observableArrayList();
        variations.add(Defines.VariationThemes.SIZE);
        variations.add(Defines.VariationThemes.COLOR);
        variations.add(Defines.VariationThemes.SIZECOLOR);
        subclassSet = FXCollections.observableArrayList();


        variationDropdown.setItems(variations);

    }

    public void addNewRow(){

this.parentSku.setDisable(true);


        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);

        Label subclass = new Label("Unterkategorie");
        subclass.setFont(Font.font(18));
        hBox.getChildren().add(subclass);

        TextField subClassTextField = new TextField();
        subClassTextField.setPrefWidth(180);
        subClassTextField.setPrefHeight(25);
        hBox.getChildren().add( subClassTextField );

        Label parentSKU = new Label("Parent SKU");
        parentSKU.setFont(Font.font(18));
        hBox.getChildren().add(parentSKU);

        TextField parentSkuTextField = new TextField();
        parentSkuTextField.setPrefWidth(180);
        parentSkuTextField.setPrefHeight(25);
        hBox.getChildren().add( parentSkuTextField );

        Button minus = new Button("-");

        minus.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Button clickedButton = (Button) event.getSource();

                SubclassVbox.getChildren().remove(clickedButton.getParent());
                subclassSet.remove(clickedButton.getParent());
                if (subclassSet.size()==0){
                    parentSku.setDisable(false);

                }


            }

        });
        hBox.getChildren().add( minus );

        subclassSet.add(hBox);


    SubclassVbox.getChildren().add(hBox);
    SubclassVbox.setSpacing(10);
    }




    public void addNewMaterial(ActionEvent actionEvent) {


        boolean isDataMissing = false;

      //  subCategoryLabel.setTextFill(Color.GREEN);
        if (!isDataFilled(material, materialLabel) |
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
                !isDataFilled(variationDropdown, variationLabel)) {
            isDataMissing = true;
            System.out.println("NOT");
        }
        System.out.println(isDataMissing);
//todo return back
        if (false) {
            infobox.setTextFill(Color.RED);
            infobox.setText("Bitte alle Pflichtfelder eintragen");
        } else {
            infobox.setTextFill(Color.ORANGE);
            infobox.setText("Die Daten werden übertragen. Bitte warten...");
            processData();
        }
    }

    private boolean processData(){

        ArrayList<String> mainParameter = new ArrayList<>();
        mainParameter.add(material.getText());//0
        mainParameter.add(bulletPoint1.getText());//1
        mainParameter.add(bulletPoint2.getText());//2
        mainParameter.add(bulletPoint3.getText());//3
        mainParameter.add(bulletPoint4.getText());//4
        mainParameter.add(bulletPoint5.getText());//5
        mainParameter.add(articleName.getText());//6
        mainParameter.add(variationDropdown.getSelectionModel().getSelectedItem().toString());//7
        mainParameter.add(browseNode.getText());//8
        mainParameter.add(searchKeywords.getText());//9
        mainParameter.add(description.getText());//10


        AdditionNewItem additionNewItem;
        if (!parentSku.isDisabled()){
            mainParameter.add(parentSku.getText());//11
            additionNewItem = new AdditionNewItem(mainParameter);


        }else{
            TreeMap<String,String> subCategorieParentSkuPaar = new TreeMap<>();
            for (int i = 0; i <subclassSet.size() ; i++) {

                TextField a = (TextField)subclassSet.get(i).getChildren().get(1);
                String sub = a.getText();
                TextField b = (TextField)subclassSet.get(i).getChildren().get(3);
                String parent = b.getText();
                System.out.println("Sub: " + sub +" parent von ihr" + parent );

                subCategorieParentSkuPaar.put(sub, parent);

            }

            additionNewItem = new AdditionNewItem(mainParameter, subCategorieParentSkuPaar);
        }



        return  additionNewItem.setItem();

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
