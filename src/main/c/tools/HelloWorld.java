
public class HelloWorld {

	public static void main(String[] args) throws Exception {
		System.out.println("Hello World");
		System.out.println("Args: ");
		for (int i = 0; i < args.length; i++) {
			System.out.println("  " + (i+1) + ". " + args[i]);
		}
		System.out.println("Classpath: " + System.getProperty("java.class.path"));
		
		throw new Exception("Blarge!");
	}
}
