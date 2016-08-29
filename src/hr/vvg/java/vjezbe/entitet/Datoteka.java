package hr.vvg.java.vjezbe.entitet;

import hr.vvg.java.vjezbe.enumeracija.Jezik;
import hr.vvg.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.vvg.java.vjezbe.iznimke.DuplikatPublikacijeException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Datoteka implements ZaProdaju{
	
	public List<Knjiga> ucitajKnjigu(List<Knjiga> listaKnjiga,File fileZaCitanje)
			throws FileNotFoundException, ParseException, DuplikatPublikacijeException {
		
		List<String> listaLinija = new ArrayList<>();
		Jezik jezikKnjige = null;
		VrstaPublikacije vrstaPublikacije = null;
		Knjiga knjiga = null;
		BigDecimal cijenaPoStranici;
		
		try(BufferedReader in = new BufferedReader(new FileReader(fileZaCitanje))) {
			
			String line;
			
			while((line = in.readLine()) != null) {
				
				listaLinija.add(line);
				
				//System.out.println(line);
			}
		}
		catch(IOException e) {
				
			System.err.println(e);
		}
		
		for(int i = 0; i < listaLinija.size(); i += 7) {
			
			System.out.println("U�itavam " + ((i / 7) + 1) + ". knjigu.");
			
			//System.out.println(listaKnjiga.get(i));
			
		String naziv = listaLinija.get(i);
		int brojJezikaKnjige = Integer.parseInt(listaLinija.get(i + 1));
		
		for(Jezik jezik : Jezik.values()) {
			
			if (brojJezikaKnjige == jezik.getBrojJezika()) {
				
				jezikKnjige = Jezik.values()[brojJezikaKnjige - 1];
				
			}
		}
		String nazivIzdavaca = listaLinija.get(i + 2);
		
		String drzavaIzdavaca = listaLinija.get(i + 3);
		
		if(drzavaIzdavaca.equals("Hrvatska")) {
			
			cijenaPoStranici = Knjiga.getCijenaPoStraniciHr();
		}
		
		else {
			
			cijenaPoStranici = Knjiga.getCijenaPoStraniciForeign();
		}
		
		Izdavac izdavacKnjige = new Izdavac(nazivIzdavaca, drzavaIzdavaca);
		
		int godinaIzdanja = Integer.parseInt(listaLinija.get(i + 4));
		
		int brojVrstePublikacije = Integer.parseInt(listaLinija.get(i + 5));
		
		for(VrstaPublikacije vrstaPublikacije1 : VrstaPublikacije.values()) {
			
			if (brojVrstePublikacije == vrstaPublikacije1.getBrojVrste()) {
				
				vrstaPublikacije = VrstaPublikacije.values()[brojVrstePublikacije - 1];
			}
		}
		
		int brojStranica = Integer.parseInt(listaLinija.get(i + 6));
		
		knjiga = new Knjiga(naziv, jezikKnjige, izdavacKnjige, godinaIzdanja, vrstaPublikacije,
				cijenaPoStranici, brojStranica, null);
		
		System.out.println("U�itavanje " + ((i / 7) + 1) + ". knjige zavr�eno...");
		
		listaKnjiga.add(knjiga);
		
		}
		return listaKnjiga;

	}
	
	public List<Casopis> ucitajCasopis (List<Casopis> listaCasopisa, File fileZaCitanje) 
			throws FileNotFoundException, ParseException, DuplikatPublikacijeException {
		
		List<String> listaLinija = new ArrayList<>();
		
		VrstaPublikacije vrstaPublikacije = null;
		
		Casopis casopis = null;
		
		try(BufferedReader in = new BufferedReader(new FileReader(fileZaCitanje))) {
			
			String line;
			
			while((line = in.readLine()) != null) {
				
				listaLinija.add(line);
				
				//System.out.println(line);
			}
		}
		catch(IOException e) {
				
			System.err.println(e);
		}
		
		for(int i = 0; i < listaLinija.size(); i += 5) {
			
			System.out.println("U�itavam " + ((i / 5) + 1) + ". �asopis.");
			
			String nazivCasopisa = listaLinija.get(i);
			
			int godinaIzdanja = Integer.parseInt(listaLinija.get(i + 1));
			
			int mjesecIzdavanja = Integer.parseInt(listaLinija.get(i + 2));
			
			int brojVrstePublikacije = Integer.parseInt(listaLinija.get(i + 3));
			
			for(VrstaPublikacije vrstaPublikacije1 : VrstaPublikacije.values()) {
				
				if (brojVrstePublikacije == vrstaPublikacije1.getBrojVrste()) {
					
					vrstaPublikacije = VrstaPublikacije.values()[brojVrstePublikacije - 1];
				}
			}
			
			int brojStranica = Integer.parseInt(listaLinija.get(i + 4));
			
			
		casopis = new Casopis(nazivCasopisa, godinaIzdanja, brojStranica, vrstaPublikacije, mjesecIzdavanja);
		
		System.out.println("U�itavanje " + ((i / 5) + 1) + ". �asopisa zavr�eno...");
		
		listaCasopisa.add(casopis);
		}
		
		return listaCasopisa;
	}
	
	public List<Clan> ucitajClana (List<Clan> listaClanova, File fileZaCitanje) 
			throws FileNotFoundException, ParseException {
		
		Clan clan = null;
		
		List<String> listaLinija = new ArrayList<>();
		
		
		try(BufferedReader in = new BufferedReader(new FileReader(fileZaCitanje))) {
			
			String line;
			
			while((line = in.readLine()) != null) {
				
				listaLinija.add(line);
				
				//System.out.println(line);
			}
		}
		catch(IOException e) {
				
			System.err.println(e);
		}
		
		for(int i = 0; i < listaLinija.size(); i += 3) {
			
			String oib = listaLinija.get(i);
			
			String prezime = listaLinija.get(i + 1);
			
			String ime = listaLinija.get(i + 2);
			
			clan = new Clan(oib, prezime, ime);
			
			listaClanova.add(clan);
		}
		
		return listaClanova;
		
		
	}
	
}
	

	

