package logPass.aDatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by User on 14.03.2016.
 */
public class RegistrationController implements Initializable{
private Connection con=javaconectDB.ConectDB();
    PreparedStatement prep;
    @FXML
    private TextField name=null;
    @FXML
    private PasswordField password=null;
    @FXML
    Label aError;

    public void LogPass(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String str="insert into emplyeeinfo(name, password)"+ " values(?,?)";
            con = javaconectDB.ConectDB(); //???????? ????
            prep = con.prepareStatement(str);
            prep.setString(1, name.getText());
            prep.setString(2, password.getText());

           prep.execute();
                }catch (Exception e){            System.out.println("ERRORRRR!!!");
        }
    }
    public void CancelBtn(ActionEvent actionEvent) {
        Node n=(Node)actionEvent.getSource();
        Stage st=(Stage)n.getScene().getWindow();
        st.close();
    }


    public void aNext(ActionEvent actionEvent) throws SQLException{
        String sql = "select*from emplyeeinfo where name=?";

        prep = con.prepareStatement(sql);
        prep.setString(1, name.getText());
        ResultSet rs = prep.executeQuery();

        String username=name.getText();
        String pass=password.getText();
        aError.setText("");
        if(isNullOrEmpty(username)|| isNullOrEmpty(pass)){
            aError.setText("Use all fields!!!");
        return;}
        if (rs.next()) {
            aError.setText("Login exist, pls change Name");
            return;}
        else {
            LogPass();
            CancelBtn(actionEvent);}
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // LogPass();
    }
    //???????? ????????????? TextField
    private boolean isNullOrEmpty(String str){
        return str ==null || str.trim().length() ==0;
    }
}
