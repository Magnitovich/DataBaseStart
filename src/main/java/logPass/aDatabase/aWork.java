package logPass.aDatabase;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by User on 04.03.2016.
 */
public class aWork {
    private SimpleIntegerProperty id=new SimpleIntegerProperty();
    private SimpleStringProperty firstname=new SimpleStringProperty("");
    private SimpleStringProperty lastname=new SimpleStringProperty("");
    private SimpleStringProperty email=new SimpleStringProperty("");
    private SimpleStringProperty phone=new SimpleStringProperty("");


    public aWork() {
    }

    public aWork(String  firstname, String lastname,String  email, String  phone) {

        this.firstname =new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
    }

    @Override
    public String toString() {
        return
        firstname.get()//+" <"+lastname.get()+"> "+" \""+email.get()+"\" "+" \""+phone.get()+"\""
                ;
    }
    public static void aInformation(Connection connection, ObservableList<aWork> list){
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery("SELECT FirstName, LastName, Email, Phone FROM contacts");
            while (rs.next()){
                list.add(new aWork(rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getString("Email"), rs.getString("Phone")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getFirstname() {
        return firstname.get();
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public aWork(String firstname) {
        this.firstname =new SimpleStringProperty(firstname);
    }
}
