package hr.vvg.java.vjezbe.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.vvg.java.vjezbe.baza.Datoteke;
import hr.vvg.java.vjezbe.entitet.Clan;
import hr.vvg.java.vjezbe.entitet.Datoteka;
import hr.vvg.java.vjezbe.entitet.Knjiga;
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

public class ClanController {
	
	public ClanController() {
		
		
	}
	
	
	@FXML 
	private TextField prezimeClana; 
	
	@FXML 
	private TableView<Clan> clanTable;
	
	@FXML 
	private TableColumn<Knjiga, String> imeClanaColumn; 
	
	@FXML 
	private TableColumn<Knjiga, String> prezimeClanaColumn; 
	
	@FXML 
	private TableColumn<Knjiga, String> oibClanaColumn; 
	
	
	
	@FXML 
	public void initialize() { 
		
		imeClanaColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, String>("imeClana"));
		prezimeClanaColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, String>("prezimeClana"));
		oibClanaColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, String>("oibClana"));
		
		
	}

	
	public void prikaziSveClanove() {
		
		Datoteka datoteka = new Datoteka();
		
		List<Clan> pomocnaListaClanova = new ArrayList<>();
		
		File fileZaCitanje = new File("clanovi.txt");
		
		try {
			
			datoteka.ucitajClana(pomocnaListaClanova, fileZaCitanje);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
	List<Clan> filtriraniClanovi = new ArrayList<Clan>();
	
	if (prezimeClana.getText().isEmpty() == false) {
		
		filtriraniClanovi = pomocnaListaClanova.stream().filter(p -> p.getPrezimeClana() .contains(prezimeClana.getText())) 
				.collect(Collectors.toList()); 
		} else { 
			
			filtriraniClanovi = pomocnaListaClanova;
			
				} 
	
	ObservableList<Clan> listaClanova = FXCollections.observableArrayList(filtriraniClanovi);
	
	clanTable.setItems(listaClanova); 
	
	
	}
	
public void obrisi() { 
		
		Clan c = clanTable.getSelectionModel().getSelectedItem(); 
		
		clanTable.getItems().remove(c); 
		
		try {
			Datoteke.unesiClana(clanTable.getItems());
		} catch (FileNotFoundException | ParseException
				| DuplikatPublikacijeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	} 
	
	public void uredi() { 
		
		try { 
			
			FXMLLoader l = new FXMLLoader(getClass().getResource( "../javafx/clanUredi.fxml")); 
			
			BorderPane root = (BorderPane)l.load(); 
			
			ClanoviUrediController cont = l .<ClanoviUrediController> getController(); 
			
			cont.urediParametri(clanTable.getItems(), clanTable .getSelectionModel().getSelectedItem()); 
			
			JavaFXGlavna.setCenterPane(root); 
			
		} 
		
		catch (IOException e) { 
			
			e.printStackTrace(); 
			
		}
	}
}
