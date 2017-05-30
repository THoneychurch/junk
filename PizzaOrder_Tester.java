/**
 * The Pizza Order Help class represents the 
 *  
 * @author Tristan Honeychurch
 * @version 1.0, (May 26, 2015 - June 04 2015)
 */

public class PizzaOrder_Tester {
	
	public static void main(String[] args){
		
		PizzaOrder box = new PizzaOrder();
		box.setSize(500, 710);
		box.setTitle("Make Your Own | PIZZA ORDER");
		box.setLocationRelativeTo(null);
		box.setVisible(true);
		box.setResizable(false);
		box.setDefaultCloseOperation(PizzaOrder.EXIT_ON_CLOSE);
		box.addComponentListener(box);
		box.revalidate();
	}
	
}