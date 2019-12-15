package ApletPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingWorker;
/**
 * klasa czyœci wszystkie pola z danymi w Source oraz reszte pól tekstowych
 */
public class CleanButton implements ActionListener {
	final MainAplet mainWindow;
	final ViewResult viewResult;
	Cleaning cleanAll=new Cleaning();
	public CleanButton(MainAplet mainWindow, ViewResult viewResult){
		this.mainWindow = mainWindow; 
		this.viewResult = viewResult;
	}
	class Cleaning extends SwingWorker<Void,Void> {
		@Override
		protected Void doInBackground()   throws Exception{
			cleanAll=new Cleaning();
			for(int i=0;i<10;i++){		
				mainWindow.sourceList.get(i).amplitude.setText("1");
				mainWindow.sourceList.get(i).YPlace.setText("0");
				mainWindow.sourceList.get(i).frequency.setText("0");
				mainWindow.sourceList.get(i).existingData.setState(false);
			}
			
			mainWindow.OXData.setText("0");
			mainWindow.OYData.setText("0");
			for(int i=0;i<ViewResult.Width;i++)
				for(int j=0;j<ViewResult.Height;j++)
					viewResult.mainResult[i][j]=0;
			PaintByRect ImageOfGraph =new PaintByRect(mainWindow,viewResult);
			mainWindow.graphViewByRect.add(ImageOfGraph);
			ImageOfGraph.setBounds(3, 3, ViewResult.Width, ViewResult.Height);
			mainWindow.revalidate();
			return null;
		}
		protected void done() {

			System.out.printf("wyczyszczone");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		cleanAll.execute();
		
	}
}
