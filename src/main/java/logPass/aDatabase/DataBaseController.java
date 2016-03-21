package logPass.aDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import logPass.Main;
import java.util.Calendar;
import java.util.GregorianCalendar;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DataBaseController implements Initializable{

        private addController editControl;
    private Stage stage;
    private Parent root;
    private Parent rootEmail;
    private FXMLLoader fxmlLoad=new FXMLLoader();
    private ResourceBundle resBundle;
//    private ResultSet rs;
    private Connection con;
    private aEmailController EmailWindows;

    private Main loadMain;
    @FXML
    private ObservableList<aWork> data;
    private ObservableList<aWork> aComboBox;
    private ObservableList<aWork> aSearch=FXCollections.observableArrayList();
    private ObservableList<aWork> aSearchData=FXCollections.observableArrayList();
    @FXML
    TableView<aWork> table_employee;
    @FXML
    TableColumn<aWork, String> Firstname;
    @FXML
    TableColumn<aWork, String> Lastname;
    @FXML
    TableColumn<aWork, String> Email;
    @FXML
    TableColumn<aWork, String> Phone;
    @FXML
    TableColumn<aWork, Integer> id;
    @FXML
    ComboBox cmbCarrera;
    @FXML
    TextField seeFN;
    @FXML
    TextField seeLN;
    @FXML
    TextField seeE;
    @FXML
    TextField seePn;
    @FXML
    private Menu date_txt;
    @FXML
    private Menu time_txt;

        //ComboBox
    public void FillCombo() throws SQLException {
        Statement stm=null;
        ResultSet resultSet=null;
        stm=con.createStatement();
//ПИШЕТСЯ БЛАГОДАРЯ toString() в aWork!!!!!!
        resultSet=stm.executeQuery("SELECT FirstName from contacts");
        aComboBox=FXCollections.observableArrayList();
        cmbCarrera.setPromptText("Firstname"); //просто название в ComboBox
        while (resultSet.next()){
            aComboBox.add(new aWork(resultSet.getString("Firstname")));
            cmbCarrera.setItems(aComboBox);
        }
    while (resultSet.next()){
                    seeFN.setText(resultSet.getString("FirstName"));
            seeLN.setText(resultSet.getString("LastName"));
            seeE.setText(resultSet.getString("Email"));
            seePn.setText(resultSet.getString("Phone"));
    }    }

    public void ClickComboBox(ActionEvent actionEvent) throws SQLException {
//ЭТОТ ВАРИАНТ ПОИСКА ВЫВОДИТ ДАННЫЕ В TextField из БД
        String sql = "Select * FROM contacts WHERE FirstName =?";
        PreparedStatement prepSt = con.prepareStatement(sql);
        prepSt.setString(1, cmbCarrera.getSelectionModel().getSelectedItem().toString());
        ResultSet rds = prepSt.executeQuery();
        while (rds.next()) {
            seeFN.setText(rds.getString("FirstName"));
            seeLN.setText(rds.getString("LastName"));
            seeE.setText(rds.getString("Email"));
            seePn.setText(rds.getString("Phone"));
//        }

            //ЭТОТ ВАРИАНТ ПОИСКА ЧЕРЕЗ ТАБЛ МИНУЯ ЗАПРОС К БД
            aSearch.addAll(data);
            for (aWork w : aSearch) {
                if (w.getFirstname().contains(cmbCarrera.getSelectionModel().getSelectedItem().toString())) {
                    aSearchData.clear();
                    aSearchData.add(w);
                }
            }
            table_employee.setItems(aSearchData);
        }
    }
    public ObservableList<aWork> getData() {        return data;    }

      public void addBtn(ActionEvent actionEvent) throws SQLException {
//        Object obj=actionEvent.getSource();
//          if(!(obj instanceof Button)){return;}
//          Button click=(Button) obj;
//          Window w=((Node)actionEvent.getSource()).getScene().getWindow();

          editControl.EditAdd(new aWork());
          EditAddBtn();
          data.add(editControl.getWork());
    }

    public void loadFxmlEdit() throws IOException {
        fxmlLoad.setLocation(getClass().getResource("../../fxml/Add.fxml"));
        root= fxmlLoad.load(); //root передаем в EditAddBtn()
        editControl=fxmlLoad.getController();
    }
    public void SendMeil() throws IOException {
        FXMLLoader fx=new FXMLLoader();
        fx.setLocation(getClass().getResource("../../Fxml/SendEmail.fxml"));
        rootEmail= fx.load(); //root передаем в EditAddBtn()
        EmailWindows=fx.getController();
    }
public void base(){
    //загрузка базы данных   private static Connection con;        private static Statement stat;
    try {
        con = javaconectDB.ConectDB(); //загрузка базы

    } catch (Exception e) {            e.printStackTrace();
    }
    data = FXCollections.observableArrayList(); //приравниваем  базу к ObservableList<aWork>

    ResultSet rs = null;
    try {
        rs = con.createStatement().executeQuery("select * from contacts"); //считываем всю базу

        while (rs.next()) { //публикуем каждую строчку
            data.add(new aWork(rs.getString("Firstname"),rs.getString("Lastname"), rs.getString("Email"),
                    rs.getString("Phone")));

            Firstname.setCellValueFactory(new PropertyValueFactory<aWork, String>("firstname"));
            Lastname.setCellValueFactory(new PropertyValueFactory<aWork, String>("lastname"));
            Email.setCellValueFactory(new PropertyValueFactory<aWork, String>("email"));
            Phone.setCellValueFactory(new PropertyValueFactory<aWork, String>("phone"));
        }        } catch (SQLException e) {            e.printStackTrace();}

    table_employee.setItems(null);
    table_employee.setItems(data);

    Firstname.setCellFactory(TextFieldTableCell.<aWork>forTableColumn());
    //возможно код для сохранения данных в табл
    Firstname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<aWork,String>>() {
        @Override
        public void handle(TableColumn.CellEditEvent<aWork, String> event) {
            String str=event.getNewValue();
            int activeRow = event.getTablePosition().getRow();
            aWork w=event.getTableView().getItems().get(activeRow);
            w.setFirstname(str);}});

}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resBundle = resources;
       //загрузка Add.fxml
        try {
//            MouseOneClick();

            loadFxmlEdit();
        } catch (IOException e) {            e.printStackTrace();        }
        base();
        try {
            FillCombo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        currentDate();
    }

    public void EditAddBtn(){
        if(stage==null){
            stage=new Stage();
            stage.setTitle("Add window");
        //    stage.setTitle(resBundle.getString("key.EditApl"));
            stage.setMaxWidth(250);
            stage.setMinHeight(150);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            //  stage.initOwner(win);
        }
        stage.showAndWait();}
         public void EmailW(Window window){
        if(stage==null){
            stage=new Stage();
            stage.setTitle("Add window");
        //    stage.setTitle(resBundle.getString("key.EditApl"));
            stage.setMaxWidth(250);
            stage.setMinHeight(150);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.setScene(new Scene(rootEmail));
            //  stage.initOwner(win);
        }
        stage.showAndWait();}

    public void delBtn(ActionEvent actionEvent) throws SQLException {
        aWork select=(aWork)table_employee.getSelectionModel().getSelectedItem();
        data.remove(select);
        PreparedStatement preparedStatement=con.prepareStatement("DELETE FROM contacts WHERE FirstName ='"+select+"'");
        preparedStatement.execute();
        preparedStatement.close();
    }

       public void aClose(ActionEvent actionEvent)  {
   System.exit(0);
    }

    public void aCloseNew(ActionEvent actionEvent) {
        Node n=(Node)actionEvent.getSource();
        Stage st=(Stage)n.getScene().getWindow();
        st.close();
    }
      public void Reload(ActionEvent actionEvent) throws SQLException {
       base();
    }

    public void sendmailPr(ActionEvent actionEvent) throws IOException {
//        aWork select=(aWork)table_employee.getSelectionModel().getSelectedItem();
//       Window w=((Node)actionEvent.getSource()).getScene().getWindow();
//        EmailWindows.addSengingEmail(select);
//        EmailW(w);
    }
        //КОД ПРИ НАЖАТИИ НА ЭКРАН ОТКРЫВАЕТСЯ ОТПРАВКА МЕЙЛА
//        public void MouseOneClick(){
//
//            table_employee.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
//
//                @Override
//                public void handle(javafx.scene.input.MouseEvent event) {
//                    if(event.getClickCount()==1){
//                        Window w=((Node)event.getSource()).getScene().getWindow();
//                        aWork select=(aWork)table_employee.getSelectionModel().getSelectedItem();
//                        EmailWindows.addSengingEmail(select);
//                        EmailW(w);
//
//                    }        }    });
//        }

    public void currentDate(){
        Calendar cal=new GregorianCalendar();
        int month=cal.get(Calendar.MONTH);
        int year=cal.get(Calendar.YEAR);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        date_txt.setText("Date "+year+"/"+(month+1)+"/"+day);

     int sec=cal.get(Calendar.SECOND);
        int huor=cal.get(Calendar.HOUR);
        int min=cal.get(Calendar.MINUTE);
        time_txt.setText("Time "+huor+":"+(min)+":"+sec);
    }

}

