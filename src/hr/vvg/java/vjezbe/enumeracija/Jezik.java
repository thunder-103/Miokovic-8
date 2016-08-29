package hr.vvg.java.vjezbe.enumeracija;


import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public enum Jezik {
	
	HRVATSKI (1, "HRVATSKI"), 
	ENGLESKI (2, "ENGLESKI"), 
	NJEMACKI (3, "NJEMACKI"), 
	FRANCUSKI (4, "FRANCUSKI"), 
	TALIJANSKI (5, "TALIJANSKI"), 
	RUSKI (6, "RUSKI"), 
	KINESKI (7, "KINESKI");
	
	private Integer brojJezika;
	private String nazivJezika;
	
	private Jezik(Integer brojJezika, String nazivJezika) {
		
	this.brojJezika= brojJezika;
	this.nazivJezika= nazivJezika;
	}

	public Integer getBrojJezika() {
		return brojJezika;
	}

	public String getNazivJezika() {
		return nazivJezika;
	}
	
	public static List<String> vrijednosti() {
		
		List<String> listaJezikaKaoStringovi = new ArrayList<>();
		
		for (Jezik jezik : Jezik.values()) {
			
			listaJezikaKaoStringovi.add(jezik.getNazivJezika());
			
		}

		return listaJezikaKaoStringovi;
		
		
	}
	
	public static Jezik getJezikById(int idJezik) {

		Jezik result = null;
		try {
			result = Arrays.stream(Jezik.values())
					.filter(j -> ((Jezik) j).getBrojJezika() == idJezik)
					.findFirst().get();
		} catch (NoSuchElementException ex) {

		}
		return result;
	}

	public static Jezik getJezikByName(String nazivJezika) {

		Jezik result = null;
		try {
			result = Arrays
					.stream(Jezik.values())
					.filter(j -> ((Jezik) j).getNazivJezika().equals(
							nazivJezika)).findFirst().get();
		} catch (NoSuchElementException ex) {

		}
		return result;
	}



}
