package hr.vvg.java.vjezbe.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



import hr.vvg.java.vjezbe.baza.Datoteke;
import hr.vvg.java.vjezbe.entitet.Datoteka;
import hr.vvg.java.vjezbe.entitet.Knjiga;
import hr.vvg.java.vjezbe.enumeracija.Jezik;
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

public class KnjigeController {
	
	
	
	public KnjigeController() { 
		
	} 
	@FXML 
	private TextField nazivKnjige; 
	
	@FXML 
	private TableView<Knjiga> knjigaTable;
	
	@FXML 
	private TableColumn<Knjiga, String> nazivKnjigeColumn; 
	
	@FXML 
	private TableColumn<Knjiga, VrstaPublikacije> vrstaKnjigeColumn; 
	
	@FXML 
	private TableColumn<Knjiga, Integer> godinaIzdanjaKnjigeColumn; 
	
	@FXML 
	private TableColumn<Knjiga, Integer> brojStranicaKnjigeColumn; 
	
	@FXML 
	private TableColumn<Knjiga, Jezik> jezikKnjigeColumn; 
	
	@FXML 
	private TableColumn<Knjiga, String> nazivIzdavacaKnjigeColumn;
	
	
	@FXML 
	public void initialize() { 
		nazivKnjigeColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, String>("naziv")); 
		vrstaKnjigeColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, VrstaPublikacije>("vrstaPublikacije")); 
		godinaIzdanjaKnjigeColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, Integer>("godinaIzdanja")); 
		brojStranicaKnjigeColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, Integer>("brojStranica")); 
		jezikKnjigeColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, Jezik>("jezikKnjige")); 
		nazivIzdavacaKnjigeColumn.setCellValueFactory( new PropertyValueFactory<Knjiga, String>("nazivIzdavaca")); 
		
	}
	
	public void prikaziSveKnjige() {
		
		Datoteka datoteka = new Datoteka();
		
		List<Knjiga> pomocnaListaKnjiga = new ArrayList<>();
		
		File fileZaCitanje = new File("knjige.txt");
		
		try {
			
			datoteka.ucitajKnjigu(pomocnaListaKnjiga, fileZaCitanje);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (DuplikatPublikacijeException e) {
			e.printStackTrace();
		}
		
		
	List<Knjiga> filtriraneKnjige = new ArrayList<Knjiga>();
	
	if (nazivKnjige.getText().isEmpty() == false) { 
		filtriraneKnjige = pomocnaListaKnjiga.stream().filter(p -> p.getNaziv() .contains(nazivKnjige.getText())) 
				.collect(Collectors.toList()); 
		} else { 
			
			filtriraneKnjige = pomocnaListaKnjiga;
			
				} 
	ObservableList<Knjiga> listaKnjiga = FXCollections.observableArrayList(filtriraneKnjige); 
	knjigaTable.setItems(listaKnjiga); 
	
	}
	
	public void obrisi() { 
		
		Knjiga c = knjigaTable.getSelectionModel().getSelectedItem(); 
		
		knjigaTable.getItems().remove(c); 
		
		try {
			Datoteke.unesiKnjige(knjigaTable.getItems());
		} catch (FileNotFoundException | ParseException
				| DuplikatPublikacijeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	} 
	
	public void uredi() { 
		
		try { 
			
			FXMLLoader l = new FXMLLoader(getClass().getResource( "../javafx/knjigaUredi.fxml")); 
			
			BorderPane root = (BorderPane)l.load(); 
			
			KnjigaUrediController cont = l .<KnjigaUrediController> getController(); 
			
			cont.urediParametri(knjigaTable.getItems(), knjigaTable .getSelectionModel().getSelectedItem()); 
			
			JavaFXGlavna.setCenterPane(root); 
			
		} 
		
		catch (IOException e) { 
			
			e.printStackTrace(); 
			
		} 
		
	}
	

}
