public class Node 
{
	public Node() 
	{

	}
	
	public static void main(String arg[])
	{
		Controller controller = new Controller();
		Application application = new Application();
		
		controller.start();
		application.start();
		
		try
		{
			application.join();
			controller.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Exécution terminée");
	}
}
