import lib.mainWindow;


public class View {

	private initAbfrage start;
	private mainWindow main;
	public View()
	{
		start = new initAbfrage();
		start.run();
		
		main = new mainWindow();
		main.run();
	}
	
	
	
}
