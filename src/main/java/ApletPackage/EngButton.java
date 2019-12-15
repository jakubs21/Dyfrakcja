package ApletPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
	 zmiana jêzyka na angielski
	 **/
public class EngButton implements ActionListener{
final MainAplet mainWindow; 
	
	public EngButton(MainAplet mainWindow){
		this.mainWindow = mainWindow; 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		mainWindow.exit.setText("quit");
		mainWindow.clean.setText("clear");
		mainWindow.start.setText("view");
		mainWindow.sourceLabel.setText("Source");
		mainWindow.ampLabel.setText("Amplitude");
		mainWindow.freqLabel.setText("Frequency");
		mainWindow.graphLabel.setText("Graph");
		/*przek.setText("Sections");
		natnapis.setText("Intensity");*/
		mainWindow.labelX.setText("Setion along the OX for Y=");
		mainWindow.labelY.setText("Setion along the OY for X=");
		mainWindow.langEng.setVisible(false);
		mainWindow.langPl.setVisible(true);
	}
}
