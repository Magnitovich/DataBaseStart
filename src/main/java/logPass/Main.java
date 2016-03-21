package logPass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;

    private static BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");
        showMain();
    }
     public static void showMain() throws IOException {
         FXMLLoader loader=new FXMLLoader();
         loader.setLocation(Main.class.getResource("/Fxml/Login.fxml"));
       // Parent root = FXMLLoader.load(getClass().getResource("Fxml/Login.fxml"));
        Parent root=loader.load();
         primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaxWidth(350);
        primaryStage.setMinHeight(200);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void showRegistrations() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("/Fxml/Regisrations.fxml"));
        BorderPane Regisrations=loader.load();

        Stage addDialogStage=new Stage();
        addDialogStage.setTitle("Registration");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        addDialogStage.initOwner(primaryStage);
        Scene scene=new Scene(Regisrations);
        addDialogStage.setScene(scene);
        addDialogStage.showAndWait();

    }
    public static void SendEmail(Window window) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("/Fxml/SendEmail.fxml"));
        BorderPane Regisrations=loader.load();

        Stage addDialogStage=new Stage();
        addDialogStage.setTitle("Send");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        addDialogStage.initOwner(primaryStage);
        Scene scene=new Scene(Regisrations);
        addDialogStage.setScene(scene);
        addDialogStage.showAndWait();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
//  ant -f D:\stadyIdea\SQL_JavaFX\Newdatabase\Log_Pass_Test_AddDB_Proverka\build.xml