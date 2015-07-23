import java.util.Vector;

import lib.mainWindow;



public class ausgabe{

	private initAbfrage start;
	private mainWindow mainWindow;

	
	
	public ausgabe()
	{
		start = new initAbfrage();
		start.run();
		mainWindow = new mainWindow();
		mainWindow.run();
		mainWindow.setmTxtArea("llalalal");
		
	}
	
	public void fill(Vector<Model> p)
	{
	mainWindow.setmTxtArea("Hallooooooooooooooooooooooooooooooooo");
	
	}
	
	
}
