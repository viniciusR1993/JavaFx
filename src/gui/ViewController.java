package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.Person;

public class ViewController implements Initializable {

	@FXML
	private ComboBox<Person> comboBoxPerson;
	@FXML
	private Button btApply;
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtEmail;

	private ObservableList<Person> obsList;

	@FXML
	public void onComboBoxPersonAction() {
		try {
			Person person = comboBoxPerson.getSelectionModel().getSelectedItem(); // Pega o item que foi selecionado no comboBox
			if(person!=null) {
				txtId.setPromptText(String.format(person.getId().toString()));
				txtName.setPromptText(person.getName());
				txtEmail.setPromptText(person.getEmail());
			}
		}catch(RuntimeException e) {
			// Chama o alert (uma janela extra) //o null
			// deixa o cabeçalho sem aparecer, mas pode ser colocad uma String
			Alerts.showAlert("ERROR!", null, e.getMessage(), AlertType.ERROR);	//Chama um alerta
		}
	}

	@FXML
	public void onBtApplyAction() {
		if(comboBoxPerson.getSelectionModel().getSelectedItem() == null) {
			Alerts.showAlert("ERROR!", null, "Nenhum Person selecionado", AlertType.ERROR);	//Chama um alerta
		}else {
			try {
				Person person = comboBoxPerson.getSelectionModel().getSelectedItem();	//atribui a person o item selecionado
				
				//Removendo da lista o item
				//Para percorrer os objeto na combo box utilize comboBoxPerson.getItems()
				for(int i = 0; i<obsList.size(); i++) {
					if(obsList.get(i).getName().equals(person.getName())) {
						obsList.remove(i);
					}
				}
				
				//Conferindo onde tem texto e alterando o person
				if (!txtEmail.getText().equals("")) {
					person.setEmail(txtEmail.getText());
				}
				if (!txtName.getText().equals("")) {
					person.setName(txtName.getText());
				}
				if (!txtId.getText().equals("")) {
					person.setId(Integer.parseInt(txtId.getText()));
				}
				
				obsList.add(person);	//adiciona um intem a lista
				
				//Limpando a tela
				txtName.setText("");
				txtName.setPromptText("");
				txtId.setText("");
				txtId.setPromptText("");
				txtEmail.setText("");
				txtEmail.setPromptText("");
				

			} catch (RuntimeException e) {
				// Chama o alert (uma janela extra) //o null
				// deixa o cabeçalho sem aparecer, mas pode ser colocad uma String
				Alerts.showAlert("ERROR!", null, e.getMessage(), AlertType.ERROR);	//Chama um alerta
			}
		}		
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// Criando uma lista de Person
		List<Person> listPerson = new ArrayList<Person>();
		listPerson.add(new Person(1, "Maria", "maria@gmail.com"));
		listPerson.add(new Person(2, "Alex", "alex@gmail.com"));
		listPerson.add(new Person(3, "Bob", "bob@hotmail.com"));

		// Coloca no obs a lista
		obsList = FXCollections.observableArrayList(listPerson);
		// Linca a lista com ObservableList
		comboBoxPerson.setItems(obsList);

		// Código extraido do material udemy. 
		//Serve para mostrar apenas o nome de person na lista
		Callback<ListView<Person>, ListCell<Person>> factory = lv -> new ListCell<Person>() {
			@Override
			protected void updateItem(Person item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getName());
			}
		};
		comboBoxPerson.setCellFactory(factory);
		comboBoxPerson.setButtonCell(factory.call(null));


		Constraints.setTextFieldInteger(txtId); // Permite que apenas integer seja colocado no campo TxtId (o codigo do constraints foi retirado da udemy)
		Constraints.setTextFieldMaxLength(txtId, 7); // Permite que apenas seja colocado 7 caracter no campo txtId (o codigo do constraints foi retirado da udemy)
		Constraints.setTextFieldMaxLength(txtName, 50);
		Constraints.setTextFieldMaxLength(txtEmail, 50);
	}
}
