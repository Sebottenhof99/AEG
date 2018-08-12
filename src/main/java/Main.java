import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

public class Main extends Application {
    
        public static void main(String[] args) throws IOException {

            launch(args);
        }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/ExcelGenerator.fxml"));
        primaryStage.setScene(new Scene(root, 100, 57));
        primaryStage.setTitle("Excel Generator");
        primaryStage.getIcons().add(new Image(String.valueOf(this.getClass().getClassLoader().getResource("icon.png"))));
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(300);
        primaryStage.show();

        }
}

