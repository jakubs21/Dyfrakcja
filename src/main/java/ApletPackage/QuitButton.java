package ApletPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class QuitButton implements ActionListener{
	/**
	 wyj�cie
	 **/
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
