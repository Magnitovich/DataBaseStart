package logPass.aDatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logPass.aDatabase.aWork;

/**
 * Created by User on 14.03.2016.
 */
public class aEmailController {
    private aWork work;
    @FXML
    private TextField EmailTo;

    @FXML
    private TextArea InsertText;

    @FXML
    void sendMessages(ActionEvent event) {

    }
    public void addSengingEmail(aWork work){
        if (work==null){return;}
        this.work=work;

            EmailTo.setText(work.getFirstname());
    }

}
