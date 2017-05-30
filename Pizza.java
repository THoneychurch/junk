/**
 * The Pizza class represents the storing of important data between
 * the PizzaOrder and the Checkout classes.
 *  
 * @author Tristan Honeychurch
 * @version 1.0, (May 15, 2015 - June 04 2015)
 */

class Pizza{
	private int order_limit = 10; // declare 'order_limit'
	private String[] toppingList = {"Anchovies", "Bacon", "BBQ Sauce", "Beef", "Capsicum", "Chicken", "Chillies", "Egg", "Garlic", "Ham", "Hot Salami", "Mushroom", "Olives", "Oregano", "Peppers", "Pineapple", "Prawns", "Tomatoes"}; // declare String Array 'toppingList' with values
 	private double[] toppingPrice = {0.50, 1.00, 0.50, 1.00, 0.50, 1.00, 0.50, 1.00, 0.50, 1.00, 0.50, 0.50, 0.50, 0.50, 0.50, 0.50, 1.50, 0.50}; // declare Double Array 'toppingPrice' with values
 	
 	private String[][] order = new String[order_limit][toppingList.length + 1]; // declare multidimensional array 'order' used for the order collection (Limited to 'order_limit')
	private double[][] amount = new double[order_limit][toppingList.length + 1]; // declare multidimensional array 'amount' used for the order price collection (Limited to 'order_limit')

 	private String firstName, streetAddress, phoneNo = null; // declare Strings as NULL
 	private double total = 0.00; // declare double 'total' as the total order amount
 	
 	/**
 	 * Sets Details Of Current Order In Arrays From Calling Class.
 	 * 
 	 * @param getOrder passed onto a private String array.
 	 * @param getAmount array passed on to a private double array. 
 	 */
 	
 	// Mutators
 	public void setOrder(String[][] getOrder, double[][] getAmount){
 		order = getOrder; 
 		amount = getAmount;
 	}
 	
 	/**
 	 * Changes Variable Of Current Order Total
 	 * 
 	 * @param t stores the order total in variable to update for new total. 
 	 */
 	public void setTotal(double t){
 		total = t;
 	}
 	
 	/**
 	 * Changes Variable of Current Customer's First Name
 	 * 
 	 * @param n stores first name to a variable for the use of updating customer's first name.
 	 */
 	public void setName(String n){
 		firstName = n;
 	}
 	
 	/**
 	 * Changes Variable of Current Customer's Delivery Address
 	 * 
 	 * @param a stores address to a variable for the use of updating customer's address.
 	 */
 	public void setAddress(String a){
 		streetAddress = a;
 	}

 	/**
 	 * Changes Variable of Current Customer's Phone Number
 	 * 
 	 * @param ph stores phone number to a variable for the use of updating customer's phone number.
 	 */
 	public void setNumber(String ph){
 		phoneNo = ph;
 	}


 	// Accessors
 	/**
 	 * Retrieve List Of All Additional Toppings From Stored Array
 	 * 
 	 * @return returns a String 'toppingList[]' array of every topping to the calling String.
 	 */
 	public String[] getToppingList(){
 		return toppingList;
 	}
 	
 	/**
 	 * Retrieve List Of All Topping Prices From Stored Array
 	 * 
 	 * @return returns a double 'toppingPrice[]' array of every topping price to the calling double. 
 	 */

 	public double[] getToppingPrice(){
 		return toppingPrice;
 	}
 	
 	/**
 	 * Get The Set Limit Of Pizzas In One Order
 	 * 
 	 * @return returns a integer 'order_limit' for the number of maximum pizza orders in one Order amount. 
 	 */
 	public int getOrderLimit(){
 		return order_limit;
 	}
 	
 	/**
 	 * Retrieve Complete Order, Including Pizza Number and Order Item From A Stored Multi-Dimensional Array
 	 * 
 	 * @return returns a String 'order[][]' array of complete order to the calling String array.
 	 */
 	public String[][] getOrder(){
 		return order;
 	}
 	
 	/**
 	 * Retrieve Order Amount, Including The Pizza Number And Item Cost From A Stored Multi-Dimensional Array
 	 * 
 	 * @return returns a double 'amount[][]' array to called double array.
 	 */
 	public double[][] getAmount(){
 		return amount;
 	}
 	
 	/**
 	 * Get The Total Amount For The Current Order. 
 	 * 
 	 * @return returns a double 'total' of the current order total.
 	 */
 	public double getTotal(){
 		return total;
 	}

 	/**
 	 * Get The Ordering Customer's Stored First Name
 	 * 
 	 * @return returns a String 'firstName' to the calling String.
 	 */
 	public String getName(){
 		return firstName;
 	}
 	
 	/**
 	 * Get The Ordering Customer's Stored Address
 	 * 
 	 * @return returns a String 'streetAddress'to the calling String.
 	 */
 	public String getAddress(){
 		return streetAddress;
 	}
 	
 	/**
 	 * Get The Ordering Customer's Stored Phone Number
 	 * 
 	 * @return returns a String 'phoneNo' to the calling String.
 	 */
 	public String getNumber(){
 		return phoneNo;
 	}
}