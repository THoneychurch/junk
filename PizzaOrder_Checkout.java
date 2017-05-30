/**
 * The Pizza Order Checkout class represents all the information of the current order
 * and the final stage before processing an order.
 *  
 * @author Tristan Honeychurch
 * @version 1.0, (May 15, 2015 - June 04 2015)
 */

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PizzaOrder_Checkout extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L; // set serialVersionUID
	private final Pizza pizza; // declare final Pizza class 'pizza'
	private final PizzaOrder pizzaOrder; // declare final PizzaOrder class 'pizzaOrder'
	private JPanel pnl_content, pnl_header, pnl_order, pnl_complete; // declare JPanels
	private JLabel total; // declare JLabel 'total' used to keep current order total
	private JTextArea txt_order = new JTextArea(22, 25); // declare new JTextArea 'txt_order' used to store order details
	private JScrollPane srl_order = new JScrollPane(txt_order); // declare new JScrollPane 'srl_order' with 'txt_order' implemented
	private JButton btn_reset, btn_process; // declare JButtons
	
	ImageIcon title = new ImageIcon(getClass().getClassLoader().getResource("images/checkout_title.png")); // declare 'title' ImageIcon with image
	
	Container base = getContentPane(); // declare Container 'base' from ContentPane
	
	public PizzaOrder_Checkout(Pizza pizza, PizzaOrder pizzaOrder){

		this.pizza = pizza;	// set final 'pizza' from returned 'pizza' class
		this.pizzaOrder = pizzaOrder; // set final 'pizzaOrder' class from returned 'PizzaOrder' class
		base.add(pnl_content = new JPanel()); // Add 'pnl_content' to base Container with BorderLayout
		pnl_content.setLayout(new BorderLayout()); // set new BorderLayout for 'pnl_content'
		
		// Header		
		pnl_content.add(pnl_header = new JPanel(), BorderLayout.NORTH); // add 'pnl_header' to 'pnl_content' with 'grid' layout properties
		pnl_header.add(new JLabel("", title, JLabel.CENTER)); // add 'lbl_title' JLabel to 'pnl_header' with JLabel properies
		
		// Order List
		pnl_content.add(pnl_order = new JPanel(), BorderLayout.CENTER); // add new JPanel with BorderLayout properties to 'pnl_content'
		pnl_order.add(srl_order); // add 'srl_order' to 'pnl_order'
		txt_order.setEditable(false); // set 'txt_order' editable FALSE
		txt_order.setLineWrap(true); // wrap text 'txt_order' TRUE
		txt_order.setFont(new Font("Verdana", Font.PLAIN, 11)); // add new font to 'txt_order'
		
		DefaultCaret caret = (DefaultCaret)txt_order.getCaret(); // add DefaultCaret 'caret' to JTextArea 'txt_order'
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); // set 'caret' to Auto Update	
		pnl_order.add(total = new JLabel("Order Total: $0.00")); // add new JLabel to 'pnl_order' with total

		// Footer
		pnl_content.add(pnl_complete = new JPanel(), BorderLayout.SOUTH); // add 'pnl_paymentOption' to 'pnl_payment'
		pnl_complete.setLayout(new GridLayout(1, 2)); // set new GridLayout properties to 'pnl_paymentOption'payment_option.add(opt_pickup); // add 'opt_pickup' JRadioButton to 'payment_option' ButtonGroup
		pnl_complete.add(btn_reset = new JButton("Reset Order")); // add new JButton to 'pnl_complete'
		pnl_complete.add(btn_process = new JButton("Complete Order")); // add new JButton to 'pnl_complete'
		btn_reset.setToolTipText("Click To Clear Entire Order"); // set JButton tooltip text
		btn_reset.addActionListener(this); // add ActionListener handler to 'btn_reset'
		btn_process.setToolTipText("Click To Process Your Order"); // set JButton tooltip text
		btn_process.addActionListener(this); // add ActionListener handler to 'btn_process'		
	}
	
	public void actionPerformed(ActionEvent e){
		
		// COMPLETE BUTTON
		if(e.getSource() == btn_process){

			// IF Complete Button text is Complete Order
			if(btn_process.getText() == "Complete Order"){ 

				/**
				* Verification Check | Verifying IF customer name, phone no and address has value from 'pizza' class 
				*/

				// IF verication check failed
				if(pizza.getName() == null || pizza.getNumber() == null || pizza.getAddress() == "unknown"){
					JOptionPane.showMessageDialog(null, "You Must Fill In The 'Payment Options' Information Before You Can Proceed", "Missing Customer Details", JOptionPane.ERROR_MESSAGE); // display message 
				}

				// IF Verification Check Passed!
				else if(pizza.getName() != null && pizza.getNumber() != null){

					// PROCESS ORDER PROMPT
					int complete = JOptionPane.showConfirmDialog(null, "Are You Ready To Process Your Order?", "Have You Ordered Enough...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
					if(complete == JOptionPane.YES_OPTION){
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy h:mma"); // declare new DateFormat 'dateformat' with format properties
						Date date = new Date(); // declare new Date 'date'

						txt_order.append("\r\n\r\n============================"); // append to 'txt_order'
						txt_order.append("\r\n\r\nOrder Date: " + dateFormat.format(date)); // append to 'txt_order'
						txt_order.append("\r\nOrder No: 000001"); // append to 'txt_order'
						
						txt_order.append("\r\n\r\nCustomer Name: " + pizza.getName()); // append to 'txt_order'

						if(pizza.getAddress() != null){
							txt_order.append("\r\nAddress: " + pizza.getAddress()); // append to 'txt_order'
						}

						txt_order.append("\r\nPhone No: " + pizza.getNumber() + "\r\n"); // append to 'txt_order'
						
						if(pizza.getAddress() != null){
							txt_order.append("\r\n+Delivery Charge ($1.00)"); // append to 'txt_order'				
						}
						
						txt_order.append("\r\n=TOTAL COST: $" + String.format("%.2f", pizza.getTotal())); // append to 'txt_order'
						txt_order.append("\r\n\r\n============================"); // append to 'txt_order'
						txt_order.append("\r\n\r\nTHANK YOU FOR CHOSING US, ENJOY!"); // append to 'txt_order'
						
						// KEEP RECEIPT PROMPT
						int receipt = JOptionPane.showConfirmDialog(null, "Would You Like To Keep A Copy Of Your Receipt?", "Keeping A Reciept", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if(receipt == JOptionPane.YES_OPTION){
							try{
								if(txt_order.getText().length() > 0){
									String content = "      [MAKE YOUR OWN] PIZZA ORDER\r\n\r\n" + txt_order.getText(); // declare string 'content' of complete order
									FileDialog dialog = new FileDialog(this, "Save File As", FileDialog.SAVE); // declare new FileDialog 'dialog' 
									dialog.setFile("receipt"); // set 'dialog' receipt filename 
									dialog.setVisible(true); // set 'dialog' visible TRUE
									String path = dialog.getDirectory() + dialog.getFile() + ".txt"; // declare string 'path' of desired save path

									FileOutputStream stream = new FileOutputStream(path); // declare new FileOutputStream 'stream' < Saving File >
									byte[] b = content.getBytes(); // declare byte 'b' of 'content' to byte
									stream.write(b); // write to text file
									stream.close(); // close 'stream' 
									// < Save Complete >
								}
							}
							catch(Exception n){ // throws exception on fail
								JOptionPane.showMessageDialog(null, "Unfortunatly there was a problem, please contact us with your 'Order ID' for a printed receipt with your order.", "Could Not Save Receipt", JOptionPane.ERROR_MESSAGE); // display error message
							}
						}

						// ORDER COMPLETE
						JOptionPane.showMessageDialog(null, "Your Order Has Been Processed, We Hope You Enjoy Your Pizza", "Thank You For Choosing Us", JOptionPane.PLAIN_MESSAGE); // display message
						pizzaOrder.editOrder("complete"); // set 'pizzaOrder' function
						btn_process.setText("Print Receipt"); // set 'btn_process' text
						btn_reset.setText("New Order"); // set 'btn_reset' text
					}
				}
			}

			// IF complete button text is Print Receipt
			else if(btn_process.getText() == "Print Receipt"){
				try{
					if(txt_order.getText().length() > 0){
						String content = "      [MAKE YOUR OWN] PIZZA ORDER\r\n\r\n" + txt_order.getText(); // declare string 'content' of complete order
						FileDialog dialog = new FileDialog(this, "Save File As", FileDialog.SAVE); // declare new FileDialog 'dialog' 
						dialog.setFile("receipt"); // set 'dialog' receipt filename 
						dialog.setVisible(true); // set 'dialog' visible TRUE
						String path = dialog.getDirectory() + dialog.getFile() + ".txt"; // declare string 'path' of desired save path

						FileOutputStream stream = new FileOutputStream(path); // declare new FileOutputStream 'stream' < Saving File >
						byte[] b = content.getBytes(); // declare byte 'b' of 'content' to byte
						stream.write(b); // write to text file
						stream.close(); // close 'stream' 
						// < Save Complete >
					}
				}
				catch(Exception n){ // throws exception on fail
					JOptionPane.showMessageDialog(null, "Unfortunatly there was a problem, please contact us with your 'Order ID' for a printed receipt with your order.", "Could Not Save Receipt", JOptionPane.ERROR_MESSAGE); // display error messgae
				}
			}
		}
		
		// RESET ORDER BUTTON
		else if(e.getSource() == btn_reset){

			// IF reset button text is Reset Order 
			if(btn_reset.getText() == "Reset Order"){
				int reset = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Reset Your Order?", "Resetting Entire Order", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // prompt message
				if(reset == JOptionPane.YES_OPTION){
					btn_process.setEnabled(false); // set 'btn_process' enabled FALSE
					pizzaOrder.editOrder("reset"); // set 'pizzaOrder' function
				}
			}

			// IF reset button text is New Order
			else if(btn_reset.getText() == "New Order"){
				int newOrder = JOptionPane.showConfirmDialog(null, "Would You Like To Create A New Order Order?", "Still Hungry?....", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // prompt message
				if(newOrder == JOptionPane.YES_OPTION){
					btn_process.setEnabled(false); // set 'btn_process' enabled FALSE
					btn_reset.setText("Reset Order"); // set 'btn_reset' text
					btn_process.setText("Complete Order"); // set 'btn_process' text
					pizzaOrder.editOrder("reset"); // set 'pizzaOrder' function
				}
			}
		}
	}
	
	/**
	 * Function is used to update the order list with the current order, this 
	 * is called from the PizzaOrder class and updates with passing arrays
	 * 
	 * @param o Order array used to collect the information about the current order item names
	 * @param p Amount array is used to collect the information about the current order prices
	 */
	public void updateList(String[][] o, double[][] p){
		btn_process.setEnabled(true); // set 'btn_process' enabled TRUE
		btn_process.setText("Complete Order"); // set 'btn_process' text 
		txt_order.setText(null); // set 'txt_order' JTextArea to NULL
		double t = pizza.getTotal(); // declare double 't' from called 'pizza' function
 		total.setText("Order Total: $" + String.format("%.2f", t)); // set 'total' JLabel text to sent 't' total 
 		
		int x, y; // decalre integers
		for (x = 0; x < o.length; x++) { // LOOP all pizzas in order ( order[pizza number] )
			if(o[x] != null){ // IF pizza number is valid
				
				if(x > 0){txt_order.append("\r\n");} // IF pizza number exeeds 1 || [0]
				
				for (y = 0; y < o[x].length; y++) { // LOOP all order items ( order[pizza number][order item] )
					String order = o[x][y]; // declare 'order' to order item
					String price = String.valueOf(String.format("%.2f",p[x][y])); // declare 'price' order item price ( amount[pizza number][item cost] )

					/**
					* Order Array Explaination:
					* 
					* - two arrays, order (o) and amount (p)
					* - each array is set as a 2-dimension
					* - the order array is set out as order[Pizza Number][Order Item] where [Order Item] location 0 is always the selected size
					* - the amount array is set out as amount[Pizza Number][Item Price] where [Item Price] location 0 is always the selected size
					*
					*/

					if(o.length > 0 && y == 0){ 
						txt_order.append("\r\n"); // append to 'txt_order'
					}					
					if(y == 0 && order == "lbl_sizeS"){ // IF small size selected
						txt_order.append("--------------------- SMALL PIZZA: ($" + price + ")"); // append to 'txt_order'
					}
					if(y == 0 && order == "lbl_sizeM"){ // IF medium size selected
						txt_order.append("--------------------- MEDIUM PIZZA: ($" + price + ")"); // append to 'txt_order'
					}
					if(y == 0 && order == "lbl_sizeL"){ // IF large size selected
						txt_order.append("--------------------- LARGE PIZZA: ($" + price + ")"); // append to 'txt_order'
					}
					else if(y > 0 && order != null){ // IF topping selected
						txt_order.append("\r\n+ " + order + ": ($" + price + ")"); // append to 'txt_order'
					}
				}
				txt_order.setText(txt_order.getText().trim()); // set 'txt_order' to trimmed 'txt_order' to remove additional spacing*
			}
			
			else{ // No more pizzas in order
				break;
			}
		}
	}

	/**
	 * Function used to change the location of the checkout window
	 * 
	 * @param x X axis location
	 * @param y Y axis location
	 */
	public void setDialogLocation(int x, int y){
	    this.setLocation(x, y); // set 'this' class location
	}
}