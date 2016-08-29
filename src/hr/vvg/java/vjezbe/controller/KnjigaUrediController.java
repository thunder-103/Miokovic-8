package hr.vvg.java.vjezbe.controller;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import hr.vvg.java.vjezbe.baza.Datoteke;
import hr.vvg.java.vjezbe.controller.base.UrediBase;
import hr.vvg.java.vjezbe.entitet.Izdavac;
import hr.vvg.java.vjezbe.entitet.Knjiga;
import hr.vvg.java.vjezbe.enumeracija.Jezik;
import hr.vvg.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.vvg.java.vjezbe.iznimke.DuplikatPublikacijeException;


@SuppressWarnings("deprecation")
public class KnjigaUrediController extends UrediBase {
	
	@FXML 
	private TextField nazivKnjiga;
	
	@FXML 
	private ComboBox<String> vrstaKnjige;
	
	@FXML 
	private TextField godinaKnjige;
	
	@FXML 
	private TextField brStranicaKnjige;
	
	@FXML 
	private ComboBox<String> jezikKnjige;

	@FXML 
	private TextField nazivIzdavaca;
	
	@FXML 
	private TextField drzavaIzdavaca;
	
	private boolean isEdit; 
	
	private Knjiga zaPrikaz; 
	
	private List<Knjiga> knjige; 
	
	public KnjigaUrediController() {
		
	} 
	
	public void urediParametri(List<Knjiga> knjige, Knjiga zaPrikaz) { 
		
		this.zaPrikaz = zaPrikaz;
		this.knjige = knjige; 
		isEdit = true; 
		nazivKnjiga.setText(zaPrikaz.getNaziv()); 
		vrstaKnjige.setValue(zaPrikaz.getVrstaPublikacije().toString()); 
		godinaKnjige.setText(zaPrikaz.getGodinaIzdanja() + ""); 
		brStranicaKnjige.setText(zaPrikaz.getBrojStranica() + ""); 
		jezikKnjige.setValue(zaPrikaz.getJezikKnjige().toString()); 
		nazivIzdavaca.setText(zaPrikaz.getIzdavacKnjige().getNazivIzdavaca()); 
		drzavaIzdavaca.setText(zaPrikaz.getDrzavaIzdavaca()); } 
	
	@FXML 
	public void initialize() { 
		
		jezikKnjige.getItems().addAll(Jezik.vrijednosti()); 
		vrstaKnjige.getItems().addAll(VrstaPublikacije.vrijednosti()); 
		
	}
	
	@FXML 
	private void unesiKnjigu() {
		
		List<Knjiga> knjige = null; 
		if (!(validirajVrijednost(nazivIzdavaca) & validirajVrijednost(nazivKnjiga) 
				& validirajVrijednost(drzavaIzdavaca) & validirajVrijednost(vrstaKnjige) 
				& validirajVrijednost(jezikKnjige) & validirajBroj(godinaKnjige) & 
				validirajBroj(brStranicaKnjige))) { 
			
			Dialogs.create().title("Greška") .message("Podaci nisu u ispravnom formatu!").showError(); 
			
			return; 
			
		} 
		
		if (isEdit) { 
			
			knjige = this.knjige; 
			knjige.remove(zaPrikaz); 
			
		} 
		
		else {
			
			try {
				knjige = Datoteke.ucitajKnjigu();
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
		}
		
		Jezik jezik = Jezik.valueOf(jezikKnjige.getValue()); 
		
		float cijenaStranice = (jezik == Jezik.HRVATSKI) ? 0.45f : 0.75f; 
		
		knjige.add(new Knjiga(nazivKnjiga.getText(), jezik, 
				new Izdavac( nazivIzdavaca.getText(), drzavaIzdavaca.getText()), 
				Integer.parseInt(godinaKnjige.getText()), VrstaPublikacije.valueOf(vrstaKnjige.getValue()), 
				BigDecimal.valueOf(cijenaStranice), Integer.parseInt(brStranicaKnjige.getText()), isEdit)); 
		
		
		try {
			Datoteke.unesiKnjige(knjige);
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
		
		Dialogs.create().title("Informacija") .message("Knjiga je uspješno unesena").showInformation();
	}
	
}

