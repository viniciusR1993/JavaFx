package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.entities.Person;

public class ViewController implements Initializable {

	@FXML
	private ComboBox<Person> comboBoxPerson;
	@FXML
	private Button btAll;
	
	private ObservableList<Person> obsList;
	
	@FXML
	public void onBtAllAction() {
		for(Person person : comboBoxPerson.getItems()) {	//Percorre todos os item da comboBox
			System.out.println(person);
		}
	}
	
	@FXML
	public void onComboBoxPersonAction() {
		Person person = comboBoxPerson.getSelectionModel().getSelectedItem();	//Pega o item que foi selecionado no comboBox
		System.out.println(person);
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

		//Código extraido do material udemy. Serve para mostrar apenas o nome de person na lista
		Callback<ListView<Person>, ListCell<Person>> factory = lv -> new ListCell<Person>() {
			@Override
			protected void updateItem(Person item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getName());
			}
		};
		comboBoxPerson.setCellFactory(factory);
		comboBoxPerson.setButtonCell(factory.call(null));
	}

	/*
	 * @FXML private TextField txtNumber1;
	 * 
	 * @FXML private TextField txtNumber2;
	 * 
	 * @FXML private Button btSum;
	 * 
	 * @FXML private Label labelResult;
	 * 
	 * @FXML public void onBtSumAction(){ try { Locale.setDefault(Locale.US); double
	 * number1 = Double.parseDouble(txtNumber1.getText()); //Pega o que está escrito
	 * no txtNumber1. Precisa do parse devido o retorno ser String double number2 =
	 * Double.parseDouble(txtNumber2.getText()); double sum = number1 + number2;
	 * 
	 * labelResult.setText(String.format("%.2f", sum)); //Seta o que está na label
	 * }catch(NumberFormatException e) { //Chama o alert (uma janela extra) //o null
	 * deixa o cabeçalho sem aparecer, mas pode ser colocad uma String
	 * Alerts.showAlert("ERROR!", null, e.getMessage(), AlertType.ERROR); } }
	 * 
	 * //Aqui você coloca o que você quer que seja feito antes de ser iniciado a
	 * aplicação
	 * 
	 * @Override public void initialize(URL url, ResourceBundle rb) {
	 * Constraints.setTextFieldDouble(txtNumber1); //Faz com que o txtNumber1 recebe
	 * apenas numbero double. Constraints.setTextFieldDouble(txtNumber2);
	 * Constraints.setTextFieldMaxLength(txtNumber1, 12);//Coloca valor máximo para
	 * a caixa }
	 */
}
