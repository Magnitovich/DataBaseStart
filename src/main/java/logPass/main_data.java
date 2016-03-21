package logPass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * Created by User on 04.03.2016.
 */
public class main_data extends Application {

    private static Stage primaryStage;

    private static BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        showMain();
    }
    public static void showMain() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(logPass.Main.class.getResource("/Fxml/dataBase.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("Fxml/Login.fxml"));
        Parent root=loader.load();
        primaryStage.setTitle("D A T A  BASE");
        primaryStage.setScene(new Scene(root));
                primaryStage.show();
    }


//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("Fxml/dataBase.fxml"));
//        primaryStage.setTitle("Base");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.setMinWidth(350);
//        primaryStage.setMinHeight(200);
//        primaryStage.setResizable(true);
//        primaryStage.show();
//    }
public void CloseWindow() throws IOException {

        primaryStage.close();
}
    public static void main(String[] args) {
        launch(args);
    }
}


