package ApletPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class QuitButton implements ActionListener{
	/**
	 wyjœcie
	 **/
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
