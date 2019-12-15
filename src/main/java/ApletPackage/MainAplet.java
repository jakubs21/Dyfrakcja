package ApletPackage;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MainAplet extends Applet {

	private static final long serialVersionUID = 1L;
	ArrayList<Source> sourceList=new ArrayList<Source>();
	ArrayList<JPanel> scalePanels= new ArrayList<JPanel>();	
 
	
	//buttons
	JButton start = new JButton("wy\u015Bwietl");
	JButton clean = new JButton("wyczy\u015B\u0107");
	JButton langEng = new JButton("English");
	JButton langPl = new JButton("Polski");
	JButton exit = new JButton("wyjscie");
	//layouts	
	GridLayout programLayout = new GridLayout();
	GridLayout leftLayout = new GridLayout(2,1);
	GridLayout allDataLayout = new GridLayout(11,1);
	GridLayout scaleLayout = new GridLayout(100,1);
	FlowLayout buttonsLayout= new FlowLayout();
	BorderLayout RightLayout = new BorderLayout();
	BorderLayout mainGraphLayout = new BorderLayout();
	BorderLayout graphxLayout = new BorderLayout();
	BorderLayout graphyLayout = new BorderLayout();
	FlowLayout descriptionLayout = new FlowLayout();
	//panels
	JPanel program=new JPanel();
	JPanel left=new JPanel();
	JPanel right=new JPanel();
	JPanel allData=new JPanel();
	JPanel menuButtons=new JPanel();
	JPanel maingraph=new JPanel();
	JPanel graphPlace=new JPanel();
	JPanel graphs=new JPanel();
	JPanel description= new JPanel();
	JPanel graphx=new JPanel();
	JPanel graphy=new JPanel();
	JPanel descriptionGraphX=new JPanel();
	JPanel descriptionGraphY=new JPanel();
	JPanel oneScale = new JPanel();
	JPanel graphPanel = new JPanel();
	JPanel graphViewByRect= new JPanel();

	//opisy labels
	JLabel sourceLabel= new JLabel("\u0179r\u00F3d\u0142a");
	JLabel yLabel= new JLabel("Y");
	JLabel ampLabel= new JLabel("Amplituda");
	JLabel freqLabel= new JLabel("Cz\u0119stotliwo\u015B\u0107");
	JLabel graphLabel= new JLabel("Wykres");
	JLabel labelX=new JLabel("Przekr\u00F3j wzd\u0142u\u017C OX dla Y=");
	JLabel labelY=new JLabel("Przekr\u00F3j wzd\u0142u\u017C OY dla X=");
	//pola wpisu danych
	JTextField OXData= new JTextField("0");
	JTextField OYData= new JTextField("0");
	/**
 zawiera wszyskie informacje na temat okna programu
	 */

	

	public MainAplet() throws HeadlessException {
		final ViewResult viewResult = null;
		setSize(1000,700);
		setPreferredSize(this.getSize());
		//ustawienia rozmiarów preferowanych przez jLabel
		sourceLabel.setSize(80,40);
		sourceLabel.setPreferredSize(sourceLabel.getSize());
		yLabel.setPreferredSize(sourceLabel.getSize());
		ampLabel.setPreferredSize(sourceLabel.getSize());
		freqLabel.setPreferredSize(sourceLabel.getSize());
		add(program);
		
		program.setSize(1000,700);
		program.setPreferredSize(program.getSize());
		/*
		 ustawianie layoutów paneli
		 */
		program.setLayout(programLayout);
		allData.setLayout(allDataLayout);
		left.setLayout(leftLayout);
		right.setLayout(RightLayout);
		menuButtons.setLayout(buttonsLayout);
		graphs.setLayout(leftLayout);
		//wygl¹d programu panele kolory
		buttonsLayout.setHgap(20);
		descriptionLayout.setHgap(20);
		allData.add(description);
		descriptionGraphX.setLayout(descriptionLayout);
		descriptionGraphY.setLayout(descriptionLayout);
		description.setLayout(descriptionLayout);
		description.setBackground(new Color(100,100,100));
		description.add(sourceLabel);
		description.add(yLabel);
		description.add(ampLabel);
		description.add(freqLabel);
		/*
		 tworzenie za pomoc¹ listy 10 paneli z polami do wpisu danych
		 */
		for(int i=0;i<10;i++){		
			Source dataList=new Source();
			sourceList.add(dataList);
			allData.add(sourceList.get(i));
			sourceList.get(i).setBackground(new Color(150,150,150));
		}
		/*
		 uk³adanie paneli w odpowiednich miejscach
		 */
		
		program.add(left);
		program.add(right);
		left.add(maingraph);
		maingraph.setLayout(mainGraphLayout);
		maingraph.setBackground(new Color(100,100,100));
		maingraph.add(graphLabel,BorderLayout.NORTH);
		maingraph.add(graphPlace,BorderLayout.CENTER);
		graphPlace.setBackground(new Color(100,100,100));
		maingraph.add(oneScale,BorderLayout.EAST);
		oneScale.setLayout(scaleLayout);
		for(int i=0;i<100;i++){
			JPanel colorScale=new JPanel();
			scalePanels.add(colorScale);
			scalePanels.get(i).setBackground(new Color(0,(2*i)+50,0));
			oneScale.add(scalePanels.get(i));			
		}
		
		left.add(graphs);
		graphs.add(graphx).setBackground(new Color(100,100,100));
		graphs.add(graphy).setBackground(new Color(100,100,100));
		graphx.setLayout(graphxLayout);		
		graphy.setLayout(graphyLayout);
		graphx.add(descriptionGraphX,BorderLayout.NORTH);
		OXData.setSize(50,30);
		OXData.setPreferredSize(OXData.getSize());
		descriptionGraphX.add(labelX);
		descriptionGraphX.add(OXData);
		graphy.add(descriptionGraphY,BorderLayout.NORTH);
		OYData.setSize(50,30);
		OYData.setPreferredSize(OYData.getSize());
		descriptionGraphY.add(labelY);
		descriptionGraphY.add(OYData);
		graphs.setBackground(new Color(100,100,100));
		right.add(allData,BorderLayout.CENTER);
		allData.setBackground(new Color(100,100,100));
		right.add(menuButtons,BorderLayout.SOUTH);
		menuButtons.setBackground(new Color(100,100,100));
		//przyciski
		menuButtons.add(start);
		start.setText("wy\u015Bwietl");		
		menuButtons.add(clean);
		exit.setText("wyj\u015Bcie");
		menuButtons.add(langEng);
		menuButtons.add(langPl);
		langEng.setVisible(true);
		langPl.setVisible(false);
		menuButtons.add(exit);
		clean.setText("wyczy\u015B\u0107");
		/*
		 dodawanie action listener
		 */
		graphPlace.add( graphPanel,BorderLayout.CENTER);	
		graphPanel.add(graphViewByRect);
		exit.addActionListener(new QuitButton());
		langEng.addActionListener(new EngButton(this));
		langPl.addActionListener(new PlButton(this));
		start.addActionListener(new ViewResult(this));
		clean.addActionListener(new CleanButton(this,viewResult));
		
		graphViewByRect.setLayout(null);
		graphViewByRect.setSize(ViewResult.Width+10, ViewResult.Height+10);
		graphViewByRect.setPreferredSize(graphViewByRect.getSize());
	}
}
	