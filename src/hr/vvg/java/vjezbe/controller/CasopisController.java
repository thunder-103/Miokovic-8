package hr.vvg.java.vjezbe.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.vvg.java.vjezbe.baza.Datoteke;
import hr.vvg.java.vjezbe.entitet.Casopis;
import hr.vvg.java.vjezbe.entitet.Datoteka;
import hr.vvg.java.vjezbe.entitet.Knjiga;
import hr.vvg.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.vvg.java.vjezbe.glavna.JavaFXGlavna;
import hr.vvg.java.vjezbe.iznimke.DuplikatPublikacijeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class CasopisController {
	
	public CasopisController() { 
		
	} 
	@FXML 
	private TextField nazivCasopisa; 
	
	@FXML 
	private TableView<Casopis> casopisTable;
	
	@FXML 
	private TableColumn<Knjiga, String> nazivCasopisaColumn; 
	
	@FXML 
	private TableColumn<Knjiga, VrstaPublikacije> vrstaCasopisaColumn; 
	
	@FXML 
	private TableColumn<Knjiga, Integer> godinaIzdanjaCasopisaColumn; 
	
	@FXML 
	private TableColumn<Knjiga, Integer> brojStranicaCasopisaColumn; 
	
	@FXML 
	private TableColumn<Knjiga, Integer> mjesecIzavanjaCasopisaColumn;
	
	
	
	@FXML 
	public void initialize() { 
		
		nazivCasopisaColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, String>("naziv")); 
		vrstaCasopisaColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, VrstaPublikacije>("vrstaPublikacije")); 
		godinaIzdanjaCasopisaColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, Integer>("godinaIzdanja")); 
		brojStranicaCasopisaColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, Integer>("brojStranica")); 
		mjesecIzavanjaCasopisaColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, Integer>("mjesecIzdavanjaCasopisa")); 
		
	}
	
	
	public void prikaziSveCasopise() {
		
		Datoteka datoteka = new Datoteka();
		
		List<Casopis> pomocnaListaCasopisa = new ArrayList<>();
		
		File fileZaCitanje = new File("casopisi.txt");
		
		try {
			
			datoteka.ucitajCasopis(pomocnaListaCasopisa, fileZaCitanje);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (DuplikatPublikacijeException e) {
			e.printStackTrace();
		}
		
		
	List<Casopis> filtriraniCasopisi = new ArrayList<Casopis>();
	
	if (nazivCasopisa.getText().isEmpty() == false) {
		
		filtriraniCasopisi = pomocnaListaCasopisa.stream().filter(p -> p.getNaziv() .contains(nazivCasopisa.getText())) 
				.collect(Collectors.toList()); 
		} else { 
			
			filtriraniCasopisi = pomocnaListaCasopisa;
			
				} 
	
	ObservableList<Casopis> listaCasopisa = FXCollections.observableArrayList(filtriraniCasopisi);
	
	casopisTable.setItems(listaCasopisa); 
	
	
	}
	
public void obrisi() { 
		
		Casopis c = casopisTable.getSelectionModel().getSelectedItem(); 
		
		casopisTable.getItems().remove(c); 
		
		try {
			Datoteke.unesiCasopis(casopisTable.getItems());
		} catch (FileNotFoundException | ParseException
				| DuplikatPublikacijeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	} 
	
	public void uredi() { 
		
		try { 
			
			FXMLLoader l = new FXMLLoader(getClass().getResource( "../javafx/casopisUredi.fxml")); 
			
			BorderPane root = (BorderPane)l.load(); 
			
			CasopisiUrediController cont = l .<CasopisiUrediController> getController(); 
			
			cont.urediParametri(casopisTable.getItems(), casopisTable .getSelectionModel().getSelectedItem()); 
			
			JavaFXGlavna.setCenterPane(root); 
			
		} 
		
		catch (IOException e) { 
			
			e.printStackTrace(); 
			
		} 
		
	}

}
