package hr.vvg.java.vjezbe.controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import hr.vvg.java.vjezbe.baza.Datoteke;
import hr.vvg.java.vjezbe.controller.base.UrediBase;
import hr.vvg.java.vjezbe.entitet.Casopis;
import hr.vvg.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.vvg.java.vjezbe.iznimke.DuplikatPublikacijeException;


@SuppressWarnings("deprecation")
public class CasopisiUrediController extends UrediBase {
	
	@FXML 
	private TextField nazivCasopisa;
	
	@FXML 
	private ComboBox<String> vrstaCasopisa;
	
	@FXML 
	private TextField godinaCasopisa;
	
	@FXML 
	private TextField brStranicaCasopisa;

	@FXML 
	private TextField mjesecIzdavanja;
	
	private boolean isEdit;
	
	private Casopis zaPrikaz; 
	
	private List<Casopis> casopisi; 
	
	public CasopisiUrediController() {
		
	} 
	
public void urediParametri(List<Casopis> casopisi, Casopis zaPrikaz) { 
		
		this.zaPrikaz = zaPrikaz;
		this.casopisi = casopisi;
		
		nazivCasopisa.setText(zaPrikaz.getNaziv()); 
		vrstaCasopisa.setValue(zaPrikaz.getVrstaPublikacije().toString()); 
		godinaCasopisa.setText(zaPrikaz.getGodinaIzdanja() + ""); 
		brStranicaCasopisa.setText(zaPrikaz.getBrojStranica() + ""); 
		mjesecIzdavanja.setText(zaPrikaz.getMjesecIzdavanjaCasopisa() + ""); 
 
		
} 
	
	@FXML 
	public void initialize() { 
		 
		vrstaCasopisa.getItems().addAll(VrstaPublikacije.vrijednosti()); 
		
	}
	
	@FXML 
	private void unesiCasopis() {
		
		List<Casopis> casopisi = null; 
		if (!(validirajVrijednost(nazivCasopisa) 
				 & validirajVrijednost(vrstaCasopisa) 
				& validirajVrijednost(godinaCasopisa) & validirajBroj(brStranicaCasopisa) & 
				validirajBroj(mjesecIzdavanja))) { 
			
			Dialogs.create().title("Greška") .message("Podaci nisu u ispravnom formatu!").showError(); 
			
			return; 
			
		} 
		
		if (isEdit) { 
			
			casopisi = this.casopisi; 
			casopisi.remove(zaPrikaz); 
			
		} 
		
		else {
			
			try {
				casopisi = Datoteke.ucitajCasopis();
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
		
		
		casopisi.add(new Casopis(nazivCasopisa.getText(), Integer.parseInt(godinaCasopisa.getText()), 
				Integer.parseInt(brStranicaCasopisa.getText()), VrstaPublikacije.valueOf(vrstaCasopisa.getValue()), 
				Integer.parseInt(mjesecIzdavanja.getText()))); 
		
		
		try {
			Datoteke.unesiCasopis(casopisi);
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
		
		Dialogs.create().title("Informacija") .message("Èasopis je uspješno unesen").showInformation();
	}

}
