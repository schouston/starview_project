package maindisplay;
import java.awt.*;

import javax.swing.*;

public class UserGuide extends JFrame{


	private final int FRAME_WIDTH = 1500;
	private final int FRAME_HEIGHT = 450;

	public UserGuide(){

		setTitle("StarView User Guide");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocationByPlatform(true);
		this.getContentPane().setSize(1200,500);
		this.getContentPane().add(ugString());
		Container c = getContentPane();
		c.setBackground(Color.gray);
		this.setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public JPanel ugString(){

		JPanel guidePanel = new JPanel();
		guidePanel.setLayout(new BorderLayout());

		JTextArea heading = new JTextArea();
		heading.setEditable(false);
		Font hfont = new Font("Verdana", Font.BOLD, 20);
		heading.setFont(hfont);

		JPanel hPan = new JPanel();
		JPanel tPan = new JPanel();
		Font font = new Font("Verdana", Font.BOLD, 14);



		JTextArea introText = new JTextArea();
		introText.setFont(font);
		JTextArea infoText = new JTextArea();
		infoText.setEditable(false);
		JScrollPane scroll = new JScrollPane(tPan,  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, new Corner());
		//infoText.setCaretPosition(0);;
		//JScrollPane scroll = new JScrollPane(tPan);
		JTextArea text = new JTextArea();
		text.setEditable(false);
		text.setBackground(Color.lightGray);


		text.setFont(font);
		String head = " StarView User Guide" ;// + '\n' + "" ;


		String intro = 

				"Welcome to StarView, an interactive map of the Celestial Sphere which includes all stars upto a distance of 5 parsecs from the Sun." + '\n' + '\n'+
				"The stars from the Hipparcos Catalogue reach out to 50 parsecs from the Sun and the Gliese stars to 25 parsecs from the Sun."+ '\n' +'\n' +
				"The Celestial Sphere is displayed using the International Celestial Reference System." + '\n' +
				"The declination (Celestial latitude) goes from O degrees at the centre, reaching to +90 degrees at the top and -90 degrees at the bottom for all projections." + '\n' +
				"The right ascension (Celestial longitude) goes from 0 hours at the centre, upto 12 hours, or 180 degrees, at the right and down to 12 hours, or -180 degrees, at the left."+ '\n' +'\n' +
				"Each star is displayed as viewed from our Solar System in a colour which represents the stellar class."+ '\n' +
				"Each star can be selected by a mouse click and the panel at the top left of the screen will display information appropriate to the selected star." + '\n' + '\n' +

				"Information Display" +'\n' +'\n'+
				"The top left of the window displays information of the currently selected star." +'\n' +
				"This information is described as follows: " + '\n' + 
				"The Catalogue ID is the unique identifier of the star from its input catalogue."+ '\n' +
				"The Henry Draper Catalogue is a commonly reference stellar catalogue." + '\n' + 

				"The distance from our Solar System to the selected star is given in parsecs.  A parsec is equivalent to 3.26 light years."  + '\n' + 

				"The apparent magnitude is a measure of brightness of celestial object as seen from Earth. The brighter the object, the lower the magnitude." + '\n' + 

				"The Absolute Magnitude  is a measure of celestial objects intrinsic brightness, independent of where it is viewed from. The brighter the object, the lower the magnitude." + '\n' + 

				"The Right Ascension is the angular distance measured east from an arbitrary zero reference point, the Vernal Equinox Point. Analogous to longitude on the terrestrial sphere."  + '\n' + 
				"The Declination is the angular distance measured from the celestial equator to the north (positive) or south (negative).  Analogous to latitude on the terrestrial sphere." + '\n' + 
				"The stellar temperature is given in degrees Kelvin, equivalent to degrees celsius -272." + '\n' +
				"The spectral type is a classification of stars based on their spectral characteristics. Classified as O, B, A, F, G, K, M, L, T, Y with O-type being the hottest and Y-type being the coolest."  + '\n' +  
				"The colours of the stars represent the following types:"+ '\n' +  '\n' +
				"O stars are hottest and are blue"+ '\n' +
				"B stars are blueish-white"+ '\n' +
				"A stars are white"+ '\n' +
				"F stars are yellowish-white"+ '\n' +
				"G stars are yellow"+ '\n' +
				"K stars are orange"+ '\n' +
				"M stars are red"+ '\n' +  '\n' +

				"The type of Star System the object is in is also displayed"+ '\n' + '\n' +
				"CheckBoxes and Buttons" + '\n' + '\n' +
				"The check boxes at the left of the window allow you to select which catalogues the objects displayed are taken from" + '\n' +
				"To return the highlighter circle to the original position the button labeled 'Return to Centre' can be pressed."  + '\n' + '\n' +

				"Bottom Panel"+ '\n' + '\n' +
				"To search for a star by name or catalogue identifier the search box at the bottom left of the window can be used." + '\n' + 
				"Enter either a name or a catalogue identifier in the format 'Hip ','Ty ' or 'Gl ', depending on the catalogue, followed by the identifier"+ '\n' +
				 "StarView searches the catalogues which are selected so if a search is unsuccessful check if the appropriate catalogue is selected."+ '\n' + '\n' +
				"The colour that the stars are displayed in can be changed using the 'Display Colour' selection box"+ '\n' + '\n' +
				"A selection of map projections can be chosen using the 'Projection Type' selection box."+ '\n' + '\n' +
				
				

				

				"The default setting is the Hammer-Aitoff Projection.  This an equal area projections with a distinctive elliptical shape."+ '\n' +

				"The Mercator is a Cylindrical Projection.  It greatly distorts the areas towards the poles and so is not the best projection for the Celestial Sphere."+ '\n' +  "However it has been included as it is commonly used in maps of the Earth." + '\n' +
							
				"The projection labeled Cylindrical is Lamberts Cylindrical Equal Area Projection. The area is preserved but distortion increases toward the poles."+ '\n' +

				"The Orthographic-Azimuthal projection is a perspective projection, one half the Celestial Sphere at a time.  Distorts shape and area, especially towards the edges."+ '\n' +'\n' +


				"The stars displayed can be filtered using the filter boxes at the bottom right of the window:" + '\n' +
				"The filter box labeled 'Filter by distance' gives the option to reduce the stars displayed to just ones within 5 or 7 parsecs of the Sun." + '\n' +
				"The filter box labeled 'Filter by magnitude' gives the option to reduce the stars displayed to just up to an apparent(visual) magnitude of 3 or 7." + '\n' ;

		
		heading.setText(head);
		introText.setText(intro);
		introText.setCaretPosition(0);
		hPan.add(heading);
		tPan.add(introText);
		guidePanel.add(hPan, BorderLayout.NORTH);
		guidePanel.add(scroll, BorderLayout.CENTER);
		return guidePanel;
	}

}
