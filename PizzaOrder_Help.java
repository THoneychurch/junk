/**
 * The PizzaOrder Help class is used to display supportive content
 * for the user to better understand it's functionality. 
 *  
 * @author Tristan Honeychurch
 * @version 1.0, (May 26, 2015 - June 04 2015)
 */

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PizzaOrder_Help extends JFrame implements MouseListener{

	private static final long serialVersionUID = 1L; // set serialVersionUID
	private CardLayout layout = new CardLayout(); // declare new CardLayout 'layout'
	private JPanel pnl_base, pnl_categories, pnl_selection, pnl_content, pnl_header; // delcare new JPanels 
	private JTextPane txt_content; // declare new JTextPane 'txt_content'
	private JScrollPane srl_content; // declare new JScrollPane 'srl_content'

	private JLabel lbl_category[] = new JLabel[5]; // declare new JPanel array 'lbl_category' to store category JLabels
	private String[] category_list = {"basics", "topping", "payment", "checkout", "troubleshoot"}; // declare String Array 'toppingList' with category list titles
	private JPanel[] category_panel = new JPanel[5]; // declare new JPanel array 'category_panel' to store individual category JPanels
	private JLabel currentSelection; // declare new JLabel 'currentSelection' to use as the category Image JLabel

	ImageIcon title = new ImageIcon(getClass().getClassLoader().getResource("images/help_title.png")); // declare 'large' ImageIcon with image

	Container base = getContentPane(); // declare Container 'base' from ContentPane
	
	public PizzaOrder_Help(String selection){

		base.add(pnl_base = new JPanel(new GridBagLayout())); // add 'pnl_base' to 'base' with a new GridBadLayout

		GridBagConstraints grid = new GridBagConstraints(); // declare 'grid' GridBagConstraints
		grid.fill = GridBagConstraints.BOTH; // set 'grid' fill value

		// CATEGORIES 
		grid.weighty = 0; // set 'grid' Y axis weight
		grid.weightx = 0; // set 'grid' X axis weight
		pnl_base.add(pnl_categories = new JPanel(), grid); // add new JPanel to 'pnl_base' with 'grid' properties
		pnl_categories.setLayout(new GridLayout(2, 1, 0, -11)); // set new GridLayout properies to 'pnl_categories'
		pnl_categories.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.LIGHT_GRAY)); // add new MatteBorder to 'pnl_categories'
		pnl_categories.add(new JLabel(title,  JLabel.CENTER)); // add new JLabel to 'pnl_categories' with JLabel properties

		pnl_categories.add(pnl_selection = new JPanel()); // add new JPanel to 'pnl_categories'
		pnl_selection.setLayout(new GridLayout(6, 1)); // add new GridLayout properties to 'pnl_selection'

		for(int i = 0; i < lbl_category.length; i++){ // LOOP between all categorie JLabel buttons 'lbl_categories'
			pnl_selection.add(lbl_category[i] = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/help_content/" + category_list[i] + "_unclicked.png")))); // add new JLabel with new ImageIcon to 'pnl_selection'
			lbl_category[i].setCursor(new Cursor(Cursor.HAND_CURSOR)); // set 'lbl_category[i]' cursor
			lbl_category[i].addMouseListener(this); // add MouseListner handler to 'lbl_category[i]'
		}

		// CONTENT
		grid.weightx = 3; // set 'grid' X axis weight
		grid.gridx = 3; // set 'grid' X axis
		pnl_base.add(pnl_content = new JPanel(layout), grid); // add new JPanel 'layout' CardLayout to 'pnl_content'

		String resource = this.getClass().getClassLoader().getResource("").toString(); // get current class directory to 'resource' string
	    resource = resource.replace("%20", " "); // replace in 'resource' string
	    resource = resource.replace("file:/", ""); // replace in 'resource' string

		for(int i = 0; i < category_list.length; i++){ // LOOP between all categories 'category_list'
			category_panel[i] = new JPanel(new BorderLayout()); // add new JPanel with new BorderLayout to 'category_panel[i]'
			category_panel[i].add(pnl_header = new JPanel(), BorderLayout.NORTH); // add new JPanel with BorderLayout properties to 'category_panel[i]'
			pnl_header.add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/help_content/" + category_list[i] + "_title.png")), JLabel.CENTER)); // add new JLabel with ImageIcon to 'pnl_header'

			SimpleAttributeSet attribs = new SimpleAttributeSet(); // declare new SimpleAttributeSet 'attribs'
			StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER); // set center alignment to 'attribs' AttributeSet
			category_panel[i].add(srl_content = new JScrollPane(txt_content = new JTextPane())); // add new JScrollPane with JTextPane to 'category_panel[i]'
			srl_content.setBorder(null); // set 'srl_content' border NULL
			txt_content.setEditable(false); // set 'txt_content' editable FALSE
			txt_content.setContentType("text/html"); // set 'txt_content' content type
			txt_content.setBorder(null); // set 'txt_content' border FALSE
			txt_content.setBackground(null);  // set 'txt_content' background NULL
			txt_content.setHighlighter(null); // set 'txt_content' highlighter NULL
 			txt_content.setParagraphAttributes(attribs, true); // set 'txt_content' paragraphAttributes 

			StringBuilder readHTML = new StringBuilder(); // declare new StringBuilder 'readHTML'
			try {

			    File text = new File(resource + "images/help_content/html/" + category_list[i] + "_content.html"); // declare new FILE 'file'
			    Scanner scanner = new Scanner(text); // declare new Scanner 'scanner'
			    String line;
			    while (scanner.hasNextLine()) {

			    	line = scanner.nextLine(); // set nextLine to string 'line'
			    	if(line.contains("File:")){
				    		line = line.substring(0, line.indexOf("e:") + 2) + resource +  line.substring(line.indexOf("e:") + 2, line.length()); // add image location for HTML image
			    	} 
			    	readHTML.append(line); // append 'scanner' nextLine to 'readHTML'
			    }
			    scanner.close(); // close 'scanner' Scanner

			} catch (IOException e) {} // throw IOEXCEPTION when failed

			txt_content.setText(readHTML.toString()); // set 'txt_content' text to 'readHTML' string
			pnl_content.add(category_panel[i], category_list[i]); // add 'category_panel[i]' CardLayout as 'category_list[i]' in 'pnl_content' (used to call specific card)
		}

		categorySelection(selection); // call categorySelection function
	}
	
	/**
	 * Function used to change the content to correct category.
	 * 
	 * @param category String used to determine which category to swap to.
	 */
	public void categorySelection(String category){

		for(int i = 0; i < category_list.length; i++){ // LOOP all categories 'category_list'
			if(category == category_list[i]){
				lbl_category[i].setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/help_content/" + category_list[i] + "_clicked.png"))); // set new ImageIcon button of 'lbl_category[i]'
				layout.show(pnl_content, category); // show called selection 'category' card in 'pnl_content'
				currentSelection = lbl_category[i]; // set 'currentSelection' to 'lbl_category[i]'
				break; 
			}
		}
	}

	public void mouseClicked(MouseEvent e) {

		// Changing current selection button styling
		for(int i = 0; i < lbl_category.length; i++){

			if(e.getSource() == lbl_category[i]){
				for(int n = 0; n < lbl_category.length; n++){

					if(lbl_category[n] == currentSelection){
						currentSelection.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/help_content/" + category_list[n] + "_unclicked.png"))); // set new ImageIcon button of 'lbl_category[n]'
						break;
					}
				}
				categorySelection(category_list[i]); // call categorySelection function
				break;
			}
		}
	}

	public void mouseEntered(MouseEvent e) {

		for(int i = 0; i < lbl_category.length; i++){
			if(e.getSource() == lbl_category[i] && lbl_category[i] != currentSelection){ 
				lbl_category[i].setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/help_content/" + category_list[i] + "_hover.png"))); // set new ImageIcon button of 'lbl_category[i]'
				break;
			}
		}
	}
	
	public void mouseExited(MouseEvent e) {

		for(int i = 0; i < lbl_category.length; i++){
			if(e.getSource() == lbl_category[i] && lbl_category[i] != currentSelection){
				lbl_category[i].setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/help_content/" + category_list[i] + "_unclicked.png"))); // set new ImageIcon button of 'lbl_category[i]'
				break;
			}
		}

	}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
}