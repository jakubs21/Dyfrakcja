package ApletPackage;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
	 wprowadzona klasa pojedyñczego Ÿród³a
	 ustawienie wygl¹du pól do wpisywania danych
	 
	 **/
public class Source extends JPanel {
	
	private static final long serialVersionUID = 1L;
	final JTextField YPlace = new JTextField("0");
	final JTextField amplitude = new JTextField("1");
	final JTextField frequency = new JTextField("0");
	final Checkbox existingData =new Checkbox();
	public FlowLayout oneDataLayout = new FlowLayout();	
	public Source()  {		
		add(existingData);
		add(YPlace).setSize(70,40);
		YPlace.setBackground(new Color(250,250,250));
		YPlace.setPreferredSize(YPlace.getSize());
		add(amplitude).setBackground(new Color(250,250,250));
		amplitude.setPreferredSize(YPlace.getSize());
		add(frequency).setBackground(new Color(250,250,250));
		frequency.setPreferredSize(YPlace.getSize());
		oneDataLayout.setHgap(40);
		setLayout(oneDataLayout); 	
	}
}
