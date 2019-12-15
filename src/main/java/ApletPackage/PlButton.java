package ApletPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
	 zmiana jêzyka na polski
	 **/
public class PlButton implements ActionListener {
	
	final MainAplet mainWindow; 
	
	public PlButton(MainAplet mainWindow){
		this.mainWindow = mainWindow; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mainWindow.exit.setText("wyj\u015Bcie");
		mainWindow.clean.setText("wyczy\u015B\u0107");
		mainWindow.start.setText("wy\u015Bwietl");
		mainWindow.sourceLabel.setText("\u0179r\u00F3d\u0142a");
		mainWindow.ampLabel.setText("Amplituda");
		mainWindow.freqLabel.setText("Cz\u0119stotliwo\u015B\u0107");
		mainWindow.graphLabel.setText("Wykres");
		
		/*
		przek.setText("Przekroje");
		natnapis.setText("Natê¿enie");*/
		mainWindow.labelX.setText("Przekr\u00F3j wzd\u0142u\u017C OX dla Y=");
		mainWindow.labelY.setText("Przekr\u00F3j wzd\u0142u\u017C OY dla X=");
		mainWindow.langEng.setVisible(true);
		mainWindow.langPl.setVisible(false);
	}

}