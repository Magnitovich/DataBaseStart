package logPass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logPass.aDatabase.DataBaseController;
//import net.proteanit.sql.DbUtils;
import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    DataBaseController dataBaseController=new DataBaseController();
    private Stage stage;
    private Parent root;
    private FXMLLoader fxmlLoad=new FXMLLoader();

    @FXML
    TextField Username;
    @FXML
    PasswordField Password;
    @FXML
    Label text;

        private Main main;
    Connection con=null;
    ResultSet rs=null;
    PreparedStatement ps=null;

    //<------------Работа кнопки Login------------>
    public void aLogin(ActionEvent actionEvent) {
        String sql= "select*from emplyeeinfo where name=? and password=?";
        try {
            con = javaconect.ConectDB();
            ps = con.prepareStatement(sql);
            ps.setString(1, Username.getText());
            ps.setString(2, Password.getText());
            rs = ps.executeQuery();
            if (rs.next()) {
                text.setText("Username and Password is correct");
                //если верны логин и пороль загружаем базу данных

                fxmlLoad.setLocation(getClass().getResource("/fxml/dataBase.fxml"));
                root = fxmlLoad.load();
                dataBaseController = fxmlLoad.getController();
                if (stage == null) {
                    stage = new Stage();
                    stage.setScene(new Scene(root));                }


                Node n=(Node)actionEvent.getSource();
                Stage st=(Stage)n.getScene().getWindow();
                st.close();
                stage.show();
            }
            else { text.setText("ERROR! Username and Password is NOT correct");
           // Username.clear();
            Password.clear();}
        } catch (SQLException e) {
            e.printStackTrace();
    } catch (Exception e) {            e.printStackTrace();        }
    }

    public void aRegistration(ActionEvent actionEvent) throws IOException {
        Node n=(Node)actionEvent.getSource();
        Stage st=(Stage)n.getScene().getWindow();
        st.close();

        main.showRegistrations();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
