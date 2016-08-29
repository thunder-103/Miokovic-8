package hr.vvg.java.vjezbe.enumeracija;

import java.util.ArrayList;
import java.util.List;

public enum VrstaPublikacije {
	
	ELEKTRONICKA (1, "ELEKTRONICKA"), 
	PAPIRNATA (2, "PAPIRNATA");
	
	private Integer brojVrste;
	private String nazivVrste;
	
	private VrstaPublikacije(Integer brojVrste, String nazivVrste) {
		
	this.brojVrste= brojVrste;
	this.nazivVrste= nazivVrste;
	}

	public Integer getBrojVrste() {
		return brojVrste;
	}

	public String getNazivVrste() {
		return nazivVrste;
	}
	
	public static List<String> vrijednosti() {
		
		List<String> listaVrstePublikacijeKaoStringovi = new ArrayList<>();
		
		for (VrstaPublikacije vrstaPublikacije : VrstaPublikacije.values()) {
			
			listaVrstePublikacijeKaoStringovi.add(vrstaPublikacije.getNazivVrste());
			
		}

		return listaVrstePublikacijeKaoStringovi;
		
		
	}
	
	

}
