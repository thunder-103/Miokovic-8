package hr.vvg.java.vjezbe.controller;

import hr.vvg.java.vjezbe.glavna.JavaFXGlavna;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class GlavnaController {
	
	public void prikaziKnjige() { 
		
		try { 
			BorderPane knjigePane = (BorderPane) 
				FXMLLoader.load(JavaFXGlavna.class 
						.getResource("../javafx/knjige.fxml")); 
			JavaFXGlavna.setCenterPane(knjigePane); 
			} catch (Exception e) { 
				e.printStackTrace(); 
				} 
		}
	
	public void prikaziCasopise() { 
		
		try { 
			BorderPane casopisiPane = (BorderPane) 
				FXMLLoader.load(JavaFXGlavna.class 
						.getResource("../javafx/casopisi.fxml")); 
			JavaFXGlavna.setCenterPane(casopisiPane); 
			} catch (Exception e) { 
				e.printStackTrace(); 
				} 
		}
	
	public void prikaziClanove() { 
		
		try { 
			BorderPane clanoviPane = (BorderPane) 
				FXMLLoader.load(JavaFXGlavna.class 
						.getResource("../javafx/clanovi.fxml")); 
			JavaFXGlavna.setCenterPane(clanoviPane); 
			} catch (Exception e) { 
				e.printStackTrace(); 
				} 
		}
	
	public void dodajKnjige() { 
		
		try { 
			
			BorderPane knjigePane = (BorderPane) FXMLLoader
					.load(JavaFXGlavna.class .getResource("../javafx/knjigaUredi.fxml")); 
			
			JavaFXGlavna.setCenterPane(knjigePane); 
			
		} 
		
		catch (Exception e) { 
			
			e.printStackTrace(); 
			
		} 
		
	}
	
	public void dodajCasopis() { 
		
		try { 
			
			BorderPane casopisiPane = (BorderPane) FXMLLoader
					.load(JavaFXGlavna.class .getResource("../javafx/casopisUredi.fxml")); 
			
			JavaFXGlavna.setCenterPane(casopisiPane); 
			
		} 
		
		catch (Exception e) { 
			
			e.printStackTrace(); 
			
		} 
		
	}
	
	public void dodajClana() { 
		
		try { 
			
			BorderPane clanoviPane = (BorderPane) FXMLLoader
					.load(JavaFXGlavna.class .getResource("../javafx/clanUredi.fxml")); 
			
			JavaFXGlavna.setCenterPane(clanoviPane); 
			
		} 
		
		catch (Exception e) { 
			
			e.printStackTrace(); 
			
		} 
		
	}

}
