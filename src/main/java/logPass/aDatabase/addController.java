package logPass.aDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logPass.javaconect;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class addController {
aWork work;
    @FXML
    private TextField edName;
    @FXML
    private TextField edLn;
    @FXML
    private TextField edP;
    @FXML
    private TextField edE;
   @FXML
    private Label aMessDialog;
    @FXML
    private Label aMessDialog1;

    private static Connection con;
    private PreparedStatement prep;



    private boolean isAllEmpty(String stir){
        return stir==null || stir.trim().length()== 0;
    }
    //????? ??? ??? ???????? ??????? ?? ??????? ? ?????
    public static boolean validatePattern(String str, Pattern pattern) {
        return pattern.matcher(str).matches();
    }
    public void AddInfo(){
        work.setFirstname(edName.getText());
        work.setLastname(edLn.getText());
        work.setPhone(edP.getText());
        work.setEmail(edE.getText());


        try {
            Class.forName("com.mysql.jdbc.Driver");
            String str="insert into contacts(FirstName, LastName, Email, Phone)"+ " values(?,?,?,?)";
            con = javaconectDB.ConectDB(); //???????? ????
            prep = con.prepareStatement(str);

            prep.setString(1, edName.getText());
            prep.setString(2, edLn.getText());
            prep.setString(3, edE.getText());
            prep.setString(4, edP.getText());
             prep.execute();
        }catch (Exception e){
            System.out.println("ERRORRRR!!!");
        }
    }

    public void OkBtn(ActionEvent actionEvent) {
        String name = edName.getText();
        String Lname = edLn.getText();
        String Ename = edE.getText();
        String Pn = edP.getText();

        Pattern phone=Pattern.compile("[a-zA-Z?-??-?]+");
        Matcher matcher=phone.matcher(Pn);
        //????? ?????? ???????? ?mail
        //^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$
        //^?????? ??????, ???????? ?? ??????? + ????????, ??? ?????? ????? ?????????????? ?? ???? ???
        //+ ?????? email ????? a.alex@q.ua, ??????? + \\. ? ????? ??????? ???????????? ????????
        // * ????????, ??? ????? ???? ????????????? 0 ? ????? ???, ??? ??? ???????? ????? ??????? ?????
        //@ ? ??? ???? ?????.
        // ? ????? {2,} ????????, ??? ?????????? ???????(???????) ????? ?????????? ???? ?? 2-? ? ????? ????????
        Pattern mail=Pattern.compile("^([A-Za-z0-9]+)+(\\.[_A-Za-z0-9-]+)*+@[a-zA-Z]+(\\.[A-Za-z]{2,})");
        Matcher mE=mail.matcher(Ename);
        aMessDialog.setText("");
        aMessDialog1.setText("");

        if (isAllEmpty(name) || isAllEmpty(Lname) ) {
            aMessDialog.setText("WOW problem. Not all fields are filled");
            System.out.println("WORLD!!!!");
        }
        if (isAllEmpty(Pn) ||matcher.find()) {
            aMessDialog1.setText("Number contain words!");
            System.out.println("PHONE");
        return;}
        if (isAllEmpty(Ename)||!mE.find()){
            aMessDialog.setText("Enter Email realy !!!");
            System.out.println("MAIL");

        return;}else {
            AddInfo();
            CancelBtn(actionEvent);
        }
    }
        public void CancelBtn(ActionEvent actionEvent) {
            Node n=(Node)actionEvent.getSource();
            Stage st=(Stage)n.getScene().getWindow();
            st.close();
        }
        public void EditAdd(aWork work) {
            if (work == null) {
                return;
            }
            this.work = work;
            edName.setText(work.getFirstname());
            edLn.setText(work.getLastname());
            edP.setText(work.getPhone());
            edE.setText(work.getEmail());
        }
    public aWork getWork() {
        return work;
    }
    }
