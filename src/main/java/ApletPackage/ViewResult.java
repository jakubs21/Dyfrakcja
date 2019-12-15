package ApletPackage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
	 klasa uruchamiaj¹ca program
	 zbiera z pól z danymi stringi zmienia na int
	 oblicza wynik result
	 wyœwietla b³ad w przypadku wpisania liter zamiast liczb
	 rysuje wykres 
 **/

public class ViewResult implements ActionListener {
	int YInt, FrequencyInt, AmplitudeInt;
	static int Width=400, Height=300;
	double result[][]= new double[Width][Height];
	double mainResult[][] = new double[Width][Height];
	double speed=300000000,helpMax=-1000, helpMin=1000000000, helpLast=0;
	boolean secondTime=false;
	Error wrongData=new Error();
	Error noData=new Error();
	Error wrongY=new Error();
	ChartPanel xChartPanel,yChartPanel;
	waiting waitWindow=new waiting();
	XYSeries sectionY= new XYSeries("SectionY");
	XYSeries sectionX= new XYSeries("SectionX");
	XYDataset xDataSet,yDataSet;
	JFreeChart yLineGraph,xLineGraph;
	XYSeriesCollection xSeries,ySeries;
	Calculate calculate = new Calculate();
	final MainAplet mainWindow;
	public ViewResult viewResult =this; 
	public ViewResult(MainAplet mainWindow){
		this.mainWindow = mainWindow; 
	}
	class  Calculate extends SwingWorker<Void,Void> {
		@Override
		protected Void doInBackground()   throws Exception{
			boolean TryCheckBox=false;
			double a,b,c;
			helpMax=-1000; helpMin=1000000000;
			calculate = new Calculate();
			System.out.println("licze w tle ");
			noData.setVisible(true);
			for(int kk=0;kk<10;kk++){
				TryCheckBox=mainWindow.sourceList.get(kk).existingData.getState();
				if(TryCheckBox){
					noData.setVisible(false);
				}
			}
			for(int i=0;i<Width;i++)
				for(int j=0;j<Height;j++)
					mainResult[i][j]=0;
			for(int k=0;k<10;k++){	
				if(mainWindow.sourceList.get(k).existingData.getState()){
					String YStr,frequencyStr,amplitudeStr;
					YStr=mainWindow.sourceList.get(k).YPlace.getText() ;
					frequencyStr=mainWindow.sourceList.get(k).frequency.getText() ;
					amplitudeStr=mainWindow.sourceList.get(k).amplitude.getText() ;
					try{ 
						AmplitudeInt=Integer.parseInt(amplitudeStr);// sprawdzam czy dana amplitude mo¿na zamieñiæ na int
					}catch(Exception e1){
						e1.printStackTrace();
						wrongData.setVisible(true);
					}
					try{ 
						YInt=Integer.parseInt(YStr);// sprawdzam czy dane Y mo¿na zamieñiæ na int
					}catch(Exception e1){
						e1.printStackTrace();
						if(YInt>Height){
							wrongY.setVisible(true);
						}
						wrongData.setVisible(true);
					}
					try{
						FrequencyInt=Integer.parseInt(frequencyStr);// sprawdzam czy dan¹ czêstotliwoœæ mo¿na zamieñiæ na int
					}catch(Exception e1){
						e1.printStackTrace();
						wrongData.setVisible(true);
					}
					if(!noData.isVisible())
						if(!wrongY.isVisible())
							if(!wrongData.isVisible())//sprawdzam czy okna b³êdu s¹ widoczne jeœli nie to przystêpujê do liczenia
							{
								for(int i=0;i<Width;i++){
									for(int j=0;j<Height;j++){
										a=(i*i)+((j-YInt)*(j-YInt));
										b=(2*Math.PI*FrequencyInt*Math.sqrt(a)*100000)/speed;
										c=AmplitudeInt*Math.cos(b);//*Math.exp(b);
										mainResult[i][j]=mainResult[i][j]+c;
									}
								}
							}
				}
			}
			if(!noData.isVisible()&&!wrongY.isVisible()&&!wrongData.isVisible()){
				for(int i=0;i<Width;i++){
					for(int j=0;j<Height;j++){
						//oblicznie wyniku pomocy max i min
						if(helpMax<mainResult[i][j])
							helpMax=mainResult[i][j];
						if(helpMin>mainResult[i][j])
							helpMin=mainResult[i][j];						
					}
				}
			}
			if(!noData.isVisible()&&!wrongY.isVisible()&&!wrongData.isVisible()){
				if(helpMin<0){
					helpMin=-helpMin;
					helpMax=helpMax+(2*helpMin);
					for(int i=0;i<Width;i++){
						for(int j=0;j<Height;j++){
							mainResult[i][j]=mainResult[i][j]+(2*helpMin);
						}
					}
				}

				System.out.println("max "+helpMax+"min "+helpMin);
				helpLast=Math.sqrt((helpMin*helpMin)+(helpMax*helpMax))/100;
				System.out.println("last "+helpLast);
				if(secondTime){
					xSeries.removeSeries(sectionX);
					ySeries.removeSeries(sectionY);
					sectionX.clear();
					sectionY.clear();					
				}
				String oYStr=mainWindow.OYData.getText() ;
				int oYInt=Integer.parseInt(oYStr);
				for(int i=0;i<Width;i++){
					sectionX.add(i,mainResult[i][oYInt]);
				}
				String oXStr=mainWindow.OXData.getText() ;
				int oXInt=Integer.parseInt(oXStr);
				for(int i=0;i<Height;i++){
					sectionY.add(i,mainResult[oXInt][i]);
				}
				xSeries= new XYSeriesCollection(sectionX);
				ySeries= new XYSeriesCollection(sectionY);
				xDataSet= xSeries;
				yDataSet= ySeries;
				xLineGraph= ChartFactory.createXYLineChart(null, null, null, xDataSet, PlotOrientation.VERTICAL, false, true, false);
				yLineGraph= ChartFactory.createXYLineChart(null, null, null, yDataSet, PlotOrientation.VERTICAL, false, true, false);
				xChartPanel= new ChartPanel(xLineGraph);
				yChartPanel= new ChartPanel(yLineGraph);
				mainWindow.graphx.add(xChartPanel,BorderLayout.CENTER);
				mainWindow.graphy.add(yChartPanel,BorderLayout.CENTER);
			}
			return null;		
		}
		protected void done() {
			mainWindow.revalidate();
			PaintByRect ImageOfGraph =new PaintByRect(mainWindow,viewResult);
			mainWindow.graphViewByRect.add(ImageOfGraph);
			ImageOfGraph.setBounds(3, 3, Width, Height);
			mainWindow.revalidate();
			waitWindow.setVisible(false);
			mainWindow.setEnabled(true);
			secondTime=true;
		}
	}
	/**
		klasa tworzy okno b³êdu 
	 */
	public class Error extends JFrame{
		private static final long serialVersionUID = 1L;
		JPanel errorPanel= new JPanel(null);
		JButton okClose= new JButton("OK");
		JLabel errorText= new JLabel("tekst b³êdu");
		public Error(){
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setBounds(400,400,400,200);
			setAlwaysOnTop(true);
			add(errorPanel);
			setTitle("b³¹d");
			errorPanel.add(errorText);
			errorText.setBounds(50,50,100,50);
			errorPanel.add(okClose).setBounds(150,100,100,50);
			errorPanel.add(errorText).setBounds(0,50,300,50);
			class errorQuit implements ActionListener{
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					dispose();
					mainWindow.setEnabled(true);
				}				
			}
			okClose.addActionListener(new errorQuit());
		}
	}
	/**
	klasa tworzy okno informuj¹ce o rysowaniu wykresu oraz bêdzie pozwala³a na anulowanie dzia³ania programu
	 */
	public class waiting extends JFrame{
		private static final long serialVersionUID = 1L;
		JPanel waitPanel= new JPanel(null);
		JButton waitClose= new JButton("ok");
		JLabel waitText= new JLabel("Czekaj trwa rysowanie wykresu");
		public waiting(){
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setBounds(400,400,400,200);
			setAlwaysOnTop(true);
			add(waitPanel);
			setTitle("czekaj");
			waitPanel.add(waitText);
			waitText.setBounds(50,50,100,50);
			waitPanel.add(waitClose).setBounds(150,100,100,50);
			waitPanel.add(waitText).setBounds(0,50,300,50);
			class waitQuit implements ActionListener{
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					dispose();
					mainWindow.setEnabled(true);
				}				
			}
			waitClose.addActionListener(new waitQuit());
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {		
		/*
		  ustawienia jêzyka w oknach b³êdu
		 */
		if(mainWindow.langEng.isVisible()){
			wrongData.errorText.setText("Wpisz liczby zamiast liter");
			wrongData.setTitle("B\u0142¹d");
			noData.errorText.setText("Zaznacz conajmniej jedno \u017Ar\u00F3d\u0142o");
			noData.setTitle("B\u0142¹d");
			wrongY.errorText.setText("Y musi by\u0107 mniejszy ni\u017C "+Height);
			wrongY.setTitle("B\u0142¹d");
			waitWindow.waitText.setText("Czekaj trwa rysowanie wykresu");
			waitWindow.setTitle("Czekaj");
		}
		else{
			wrongData.errorText.setText("Type the numbers instead of letters");
			wrongData.setTitle("Error");
			noData.errorText.setText("Select at least one source");
			noData.setTitle("Error");
			wrongY.errorText.setText("Y must be less than "+Height);
			wrongY.setTitle("Error");
			waitWindow.waitText.setText("Please wait a moment for graph");
			waitWindow.setTitle("Wait");
		}
		waitWindow.setVisible(true);
		calculate.execute();
	}
}

