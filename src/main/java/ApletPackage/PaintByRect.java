package ApletPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PaintByRect extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final MainAplet mainWindow; 
	final ViewResult viewResult;
	ArrayList<Graphics> coloredPixel= new ArrayList<Graphics>();
	public PaintByRect(MainAplet mainWindow,ViewResult viewResult){
		this.mainWindow = mainWindow;
		this.viewResult = viewResult;
		setPreferredSize(new Dimension(ViewResult.Width,ViewResult.Height));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(!viewResult.noData.isVisible())
			if(!viewResult.wrongY.isVisible())
				if(!viewResult.wrongData.isVisible()){//sprawdzam czy okna b³êdu s¹ widoczne jeœli nie to przystêpujê do liczenia

					for(int i=0;i<ViewResult.Width;i++)
						for(int j=0;j<ViewResult.Height;j++)
							for(int k=0;k<100;k++){								
								if(viewResult.mainResult[i][j]>(k*viewResult.helpLast)){
									Graphics g2d= g;
									g2d.setColor(new Color(0,2*k,0));
									g2d.fillRect(i, j, 1, 1);	
									}
							}
				}System.out.println("koniec rysowania");
	}
}

