package maindisplay;
import java.awt.*;

import javax.swing.*;



public class StarGUI extends JFrame{

	private JPanel west, centre, south;
	private JTextArea dataDisplay;
	private JLabel searchLabel, filterDistLabel, filterMagLabel, projectionLabel;
	public JTextField searchbox;
	public JButton returnButton, systemButton, exoButton, helpButton, glossButton;
	public JComboBox<String> distBox, magBox, projBox, colourBox;
	public DisplayPanel mainDisplay;
	public JCheckBox hCheck, tCheck, gCheck;
	public JMenu distMenu;
	public JRadioButton nameButton;

	private StarManager manager;
	private StarController controller;
	//private DisplayPanel display;

	final int FRAME_WIDTH = 1900;
	final int FRAME_HEIGHT =1150;

	public StarGUI(StarController c, StarManager m){

		manager = m;
		controller = c;
		Toolkit tool = Toolkit.getDefaultToolkit();
		int xSize = (int) tool.getScreenSize().getWidth();
		int ySi = (int) tool.getScreenSize().getHeight();
		setSize(xSize, ySi);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("StarView Star Map");
		mainDisplay = new DisplayPanel(manager, controller);
		layoutWest();
		layoutCentre();
		layoutSouth();	
	}

	public DisplayPanel getDisplay(){return mainDisplay;}

	public JRadioButton getNameRadioButton(){return nameButton;}
	public JButton getGlossButton(){return glossButton;}
	public JButton	getReturnButton(){return returnButton;}
	public JButton	getHelpButton(){return helpButton;}
	public JTextField getSearchQuery(){return searchbox;}
	public JComboBox<String> getDistBox(){return distBox;}
	public JComboBox<String> getMagBox(){return magBox;}
	public JComboBox<String> getProjBox(){return projBox;}
	public JComboBox<String> getColBox(){return colourBox;}
	public JCheckBox getHCB(){return hCheck;}
	public JCheckBox getTCB() {return tCheck;}
	public JCheckBox getGCB(){return gCheck;}


	public void layoutWest(){
		
		Color bgcolor = new Color(119,144,255);
		west = new JPanel(new BorderLayout()){
			@Override
			protected void paintComponent(Graphics g){
				Graphics2D g2 = (Graphics2D) g;
				GradientPaint p = new GradientPaint(0,0, Color.gray.darker(), 0, getHeight(), Color.gray.brighter())	;
				g2.setPaint(p);
				g2.fillRect(0, 0, getWidth(), getHeight());
			}
		};


		west.setBackground(Color.gray);
		JPanel topPanel = new JPanel();
		
		Font font = new Font("Verdana",Font.BOLD, 13);
		Font headFont = new Font("Sans Serif", Font.BOLD, 15);
		dataDisplay = new JTextArea(2,17);
		dataDisplay.setSize(100, 100);
		dataDisplay.setFont(headFont);
		Color txtcolor = new Color(205,205,205);
		dataDisplay.setBackground(txtcolor);
		dataDisplay.setEditable(false);
		dataDisplay.append(manager.printSunData());
		topPanel.add(dataDisplay);

		Font buttonfont = new Font("Sans Serif", Font.BOLD, 15);
		Font catFont = new Font("Sans Serif", Font.BOLD, 17);
		Font catSFont = new Font("Sans Serif", Font.BOLD, 13);
		JLabel catLabel = new JLabel("Select Catalogue");
		catLabel.setFont(catFont);
		hCheck = new JCheckBox("Hipparcos");
		hCheck.setBackground(Color.lightGray);
		hCheck.setSelected(true);
		hCheck.addActionListener(controller);
		hCheck.setFont(catSFont);

		tCheck = new JCheckBox("Tycho");
		tCheck.setBackground(Color.lightGray);
		tCheck.addActionListener(controller);
		tCheck.setFont(catSFont);
		gCheck = new JCheckBox("Gliese");
		gCheck.setBackground(Color.lightGray);
		gCheck.addActionListener(controller);
		gCheck.setFont(catSFont);

		nameButton = new JRadioButton("Show star names");
		nameButton.setFont(headFont);
		nameButton.setSelected(true);
		nameButton.addActionListener(controller);

		JPanel buttonPanel = new JPanel(new GridLayout(9,1));
		buttonPanel.setBackground(txtcolor);
		returnButton = new JButton("Return to Centre");
		returnButton.setFont(buttonfont);
		returnButton.addActionListener(controller);
		systemButton = new JButton("View Star System");
		systemButton.addActionListener(controller);
		systemButton.setFont(buttonfont);
		exoButton = new JButton("View Planetary System");
		exoButton.addActionListener(controller);
		exoButton.setFont(buttonfont);
		helpButton = new JButton("User Guide");
		helpButton.setFont(buttonfont);
		helpButton.addActionListener(controller);
		glossButton = new JButton("Glossary");
		glossButton.setFont(buttonfont);
		glossButton.addActionListener(controller);
		JLabel blank = new JLabel("");
		blank.setBackground(Color.lightGray);
		JLabel blank2 = new JLabel("");
		blank2.setBackground(Color.lightGray);
		buttonPanel.add(catLabel);
		buttonPanel.add(hCheck);
		buttonPanel.add(tCheck);
		buttonPanel.add(gCheck);
		buttonPanel.add(blank);
		buttonPanel.add(nameButton);
		buttonPanel.add(returnButton);
		buttonPanel.add(helpButton);
		buttonPanel.add(glossButton);
		
		west.add(dataDisplay, BorderLayout.NORTH);
		west.add(buttonPanel, BorderLayout.SOUTH);
		this.add(west, BorderLayout.WEST);
	}

	public void layoutCentre(){

		mainDisplay.addMouseListener(controller);
		JScrollPane scroll = new JScrollPane(mainDisplay,  ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.addMouseListener(controller);
		this.add(mainDisplay, BorderLayout.CENTER);
	}

	public void layoutSouth(){

		south = new JPanel();
		south.setBorder(BorderFactory.createLineBorder(Color.black));
		GridLayout grid = new GridLayout(0,5);

		south.setBackground(Color.lightGray);
		south.setLayout(grid);
		Font font = new Font("Sans Serif", Font.BOLD, 14);
		searchLabel = new JLabel("Search");
		searchLabel.putClientProperty("JComponent.sizeVariant", "large");
		searchLabel.setFont(font);
		searchLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		searchbox = new JTextField("Enter name or id here");
		searchbox.addFocusListener(controller);
		
		searchbox.addActionListener(controller);
		searchbox.setBorder(BorderFactory.createLineBorder(Color.black));
		searchbox.setSize(10, 10);
		filterDistLabel = new JLabel("Filter by distance");
		filterDistLabel.setFont(font);
		filterDistLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		projectionLabel = new JLabel("Projection Type");
		projectionLabel.setFont(font);
		projectionLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel blank = new JLabel("");
		JLabel blank1 = new JLabel("");
		filterMagLabel = new JLabel("Filter by magnitude");
		filterMagLabel.setFont(font);
		filterMagLabel.setBorder(BorderFactory.createLineBorder(Color.black));

		String [] filtDStrings = { "all", "upto 5pc", "upto 7pc", "upto 10pc", "upto 20pc", "upto 30pc"};
		distBox = new JComboBox<String>(filtDStrings);
		distBox.addActionListener(controller);
		String [] filtMStrings = {"all","upto 10", "upto 7 (visible to the naked eye)", "upto 3"};
		magBox = new JComboBox<String>(filtMStrings);
		magBox.addActionListener(controller);
		String [] projStrings = { "Hammer-Aitoff", "Mercator", "Cylindrical Equal Area", "Orthographic"};
		projBox = new JComboBox<String>(projStrings);
		projBox.addActionListener(controller);

		String [] colourStrings = {"Spectral Type", "Uniform"};
		colourBox =  new JComboBox<String>(colourStrings);
		colourBox.addActionListener(controller);
		JLabel colLabel = new JLabel("Display Colour");
		colLabel.setFont(font);
		colLabel.setBorder(BorderFactory.createLineBorder(Color.black));

		south.add(searchLabel);
		south.add(colLabel);
		south.add(projectionLabel);
		south.add(filterDistLabel);
		south.add(filterMagLabel);		
		south.add(searchbox);
		south.add(colourBox);
		south.add(projBox);
		south.add(distBox);
		south.add(magBox);

		add(south, BorderLayout.SOUTH);
	}

	//method to display data on selected star
	public void updateDataDisplay(int index){
		dataDisplay.setText("");	
		if (index == -1)
			dataDisplay.append(manager.printSunData());
		else
			dataDisplay.append(manager.printStarData(index));
	}

}
