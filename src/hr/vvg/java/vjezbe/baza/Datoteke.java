package hr.vvg.java.vjezbe.baza;

import hr.vvg.java.vjezbe.entitet.Casopis;
import hr.vvg.java.vjezbe.entitet.Clan;
import hr.vvg.java.vjezbe.entitet.Izdavac;
import hr.vvg.java.vjezbe.entitet.Knjiga;
import hr.vvg.java.vjezbe.enumeracija.Jezik;
import hr.vvg.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.vvg.java.vjezbe.iznimke.DuplikatPublikacijeException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Datoteke {
	
	
	
	
	public static List<Knjiga> ucitajKnjigu()
			throws FileNotFoundException, ParseException, DuplikatPublikacijeException {
		
		
		List<Knjiga> listaKnjiga = new ArrayList<>();
		File fileZaCitanje = new File("knjige.txt");
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
			
			System.out.println("Uèitavam " + ((i / 7) + 1) + ". knjigu.");
			
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
		
		System.out.println("Uèitavanje " + ((i / 7) + 1) + ". knjige završeno...");
		
		listaKnjiga.add(knjiga);
		
		}
		return listaKnjiga;

	}
	
	public static List<Knjiga> unesiKnjige(List<Knjiga> listaKnjiga) 
			throws FileNotFoundException, ParseException, DuplikatPublikacijeException {
		
		File fileZaPisanje = new File("knjige.txt");
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileZaPisanje)))) {
				
			for(Knjiga knjiga:listaKnjiga){
				
				
		         out.println(knjiga.getNaziv());
		         out.println(String.valueOf(knjiga.getJezikKnjige().getBrojJezika()));
		         out.println(knjiga.getNazivIzdavaca());
		         out.println(knjiga.getIzdavacKnjige().getDrzavaIzdavaca());
		         out.println((String.valueOf(knjiga.getGodinaIzdanja())));
		         out.println(String.valueOf(knjiga.getVrstaPublikacije().getBrojVrste()));
		         out.println(String.valueOf(knjiga.getBrojStranica()));

		         	
			}
		}catch (IOException e) {
			   
				System.out.println(e);
			
			}
		return listaKnjiga;
			
	
	}

	
	
	public static List<Casopis> ucitajCasopis() 
			throws FileNotFoundException, ParseException, DuplikatPublikacijeException {
		
		List<Casopis> listaCasopisa = new ArrayList<>();
		File fileZaCitanje = new File("casopisi.txt");
		
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
			
			System.out.println("Uèitavam " + ((i / 5) + 1) + ". èasopis.");
			
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
		
		System.out.println("Uèitavanje " + ((i / 5) + 1) + ". èasopisa završeno...");
		
		listaCasopisa.add(casopis);
		}
		
		return listaCasopisa;
	}
	
	public static List<Casopis> unesiCasopis(List<Casopis> listaCasopisa) 
			throws FileNotFoundException, ParseException, DuplikatPublikacijeException {
		
		File fileZaPisanje = new File("casopisi.txt");
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileZaPisanje)))) {
				
			for(Casopis casopis:listaCasopisa){
				
				
		         out.println(casopis.getNaziv());
		         out.println((String.valueOf(casopis.getGodinaIzdanja())));
		         out.println(String.valueOf(casopis.getVrstaPublikacije().getBrojVrste()));
		         out.println(String.valueOf(casopis.getBrojStranica()));
		         out.println(String.valueOf(casopis.getMjesecIzdavanjaCasopisa()));

		         	
			}
		}catch (IOException e) {
			   
				System.out.println(e);
			
			}
		return listaCasopisa;
			
	
	}
	
	public static List<Clan> ucitajClana () throws FileNotFoundException, ParseException {
		
		List<Clan> listaClanova = new ArrayList<>();
		File fileZaCitanje = new File("clanovi.txt");
		
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
	
	public static List<Clan> unesiClana(List<Clan> listaClanova) 
			throws FileNotFoundException, ParseException, DuplikatPublikacijeException {
		
		File fileZaPisanje = new File("clanovi.txt");
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileZaPisanje)))) {
				
			for(Clan clan:listaClanova){
				
				
		         out.println(clan.getImeClana());
		         out.println(clan.getPrezimeClana());
		         out.println(clan.getOibClana());


		         	
			}
		}catch (IOException e) {
			   
				System.out.println(e);
			
			}
		return listaClanova;
			
	
	}

}
