package menjacnica.gui;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import menjacnica.Menjacnica;
import menjacnica.Valuta;
import menjacnica.gui.models.MenjacnicaTableModel;

public class GUIKontroler {
	public static MenjacnicaGUI menjacnica;
	public static Menjacnica sistem;
	
	public static void main(String[] args) {
		sistem = new Menjacnica();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menjacnica = new MenjacnicaGUI();
					menjacnica.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(menjacnica.getContentPane(),
				"Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak",
				JOptionPane.YES_NO_OPTION);

		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	public static void prikaziAboutProzor(){
		JOptionPane.showMessageDialog(menjacnica.getContentPane(),
				"Autor: Bojan Tomic, Verzija 1.0", "O programu Menjacnica",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void sacuvajUFajl() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(menjacnica.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				sistem.sacuvajUFajl(file.getAbsolutePath());
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(menjacnica.getContentPane(), e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void ucitajIzFajla() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(menjacnica.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				sistem.ucitajIzFajla(file.getAbsolutePath());
				prikaziSveValute();
			}	
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(menjacnica.getContentPane(), e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void prikaziSveValute() {
		vratiModel().staviSveValuteUModel(sistem.vratiKursnuListu());

	}
	

	public static void prikaziDodajKursGUI() {
		DodajKursGUI prozor = new DodajKursGUI();
		prozor.setLocationRelativeTo(menjacnica.getContentPane());
		prozor.setVisible(true);
	}

	public static void prikaziObrisiKursGUI(int red) {
		
		if (red != -1) {
			ObrisiKursGUI prozor = new ObrisiKursGUI(vratiModel().vratiValutu(red));
			prozor.setLocationRelativeTo(menjacnica.getContentPane());
			prozor.setVisible(true);
		}
	}
	
	public static void prikaziIzvrsiZamenuGUI(int red) {
		if (red != -1) {
			IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI(vratiModel().vratiValutu(red));
			prozor.setLocationRelativeTo(menjacnica.getContentPane());
			prozor.setVisible(true);
		}
	}
	
	public static void dodajValutu(Valuta valuta){
		sistem.dodajValutu(valuta);
	}
	
	public static MenjacnicaTableModel vratiModel(){
		return menjacnica.vratiModel();
	}
	
	public static double izvrsiTransakciju(Valuta valuta, boolean prodaja, double iznos){
		return sistem.izvrsiTransakciju(valuta, prodaja, iznos);
	}
	
	public static void obrisiValutu(Valuta valuta){
		sistem.obrisiValutu(valuta);
	}
}
