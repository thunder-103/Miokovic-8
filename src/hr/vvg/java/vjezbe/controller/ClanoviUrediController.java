package hr.vvg.java.vjezbe.controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import org.controlsfx.dialog.Dialogs;

import hr.vvg.java.vjezbe.baza.Datoteke;
import hr.vvg.java.vjezbe.controller.base.UrediBase;
import hr.vvg.java.vjezbe.entitet.Clan;
import hr.vvg.java.vjezbe.iznimke.DuplikatPublikacijeException;

@SuppressWarnings("deprecation")
public class ClanoviUrediController extends UrediBase {
	
	@FXML 
	private TextField imeClana;
	
	@FXML 
	private TextField prezimeClana;
	
	@FXML 
	private TextField oibClana;
	
	private boolean isEdit;
	
	private Clan zaPrikaz; 
	
	private List<Clan> clanovi; 
	
	public ClanoviUrediController() {
		
	} 
	
public void urediParametri(List<Clan> clanovi, Clan zaPrikaz) { 
		
		this.zaPrikaz = zaPrikaz;
		this.clanovi = clanovi;
		
		imeClana.setText(zaPrikaz.getImeClana()); 
		prezimeClana.setText(zaPrikaz.getPrezimeClana()); 
		oibClana.setText(zaPrikaz.getOibClana()); 

 
		
} 
	
	@FXML 
	private void unesiClana() {
		
		List<Clan> clanovi = null; 
		if (!(validirajVrijednost(imeClana) 
				 & validirajVrijednost(prezimeClana) 
				& validirajVrijednost(oibClana))) { 
			
			Dialogs.create().title("Greška") .message("Podaci nisu u ispravnom formatu!").showError(); 
			
			return; 
			
		} 
		
		if (isEdit) { 
			
			clanovi = this.clanovi; 
			clanovi.remove(zaPrikaz); 
			
		} 
		
		else {
			
			try {
				clanovi = Datoteke.ucitajClana();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		
		clanovi.add(new Clan(imeClana.getText(),prezimeClana.getText(), oibClana.getText())); 
		
		
		try {
			Datoteke.unesiClana(clanovi);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplikatPublikacijeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Dialogs.create().title("Informacija") .message("Èlan je uspješno unesen").showInformation();
	}


}
