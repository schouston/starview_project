package maindisplay;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.*;

public class Glossary extends JFrame{

	private final int FRAME_WIDTH = 1250;
	private final int FRAME_HEIGHT = 450;



	public Glossary(){

		setTitle("StarView Glossary");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocationByPlatform(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		glossary();
		Container c = getContentPane();
		c.setBackground(Color.lightGray);
		this.setVisible(true);
	}

	public void glossary(){

		Font hfont = new Font("Verdana", Font.BOLD, 20);
		Font font = new Font("Verdana", Font.BOLD, 14);

		JPanel headPanel = new JPanel();
		
		JPanel bodyPanel = new JPanel();
		
		JTextArea heading = new JTextArea();
		heading.setEditable(false);
		heading.setFont(hfont);
		JTextArea body = new JTextArea();
		body.setEditable(false);
		body.setFont(font);

		JScrollPane scroll = new JScrollPane(bodyPanel,  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		String head = "StarView Glossary";

		String gloss =  "Absolute Magnitude:"+ '\n'+

				"measure of celestial objects intrinsic brightness, is independent of where it is viewed from. A brighter object has a lower magnitude"+ '\n'+ '\n'+

				"Apparent Magnitude:" + '\n'+ 

				"measure of brightness of celestial object as seen from Earth. " +
				"A brighter object has a lower magnitude" + '\n'+ '\n'+

				"Arc-Minute:" + '\n'+ "unit of angular measurement equivalent to 1/60 of a degree"+ '\n'+ '\n'+

				"Arc-Second:" + '\n'+ "unit of angular measurement used in astronomy, equivalent to 1/60 of an arc-minute" + '\n'+ '\n'+
								"Declination:" + '\n'+ "one of two angles that measures position of an object on the celestial sphere. Measured north or south of the celestial equator" + '\n'+ '\n'+
				"Henry Draper Catalogue:" +'\n'+
				"Star Catalogue, one of the first to include spectral type classification" +'\n'+'\n'+
				
				"Hammer-Aitoff Projection"+'\n'+
				"equal area map projection where area between objects is preserved"+'\n'+'\n'+
				"International Celestial Reference System, ICRS:" + '\n'+ "Standard celestial reference system adopted by the International Astronomical Union" + '\n'+ '\n'+
				"Lamberts Cylindrical Equal AreaProjection"+'\n'+
				"map projection where area is preserved but distortion increases toward the poles"+'\n'+'\n'+
				"Mercator Projection"+'\n'+
				"map projection which distorts the areas towards the poles"+'\n'+'\n'+
				"Orthographic-Azimuthal Projection "+'\n'+
				"map projection showing 180 degrees of sphere at once. Shape and area distorted towards the edges"+'\n'+'\n'+
				"Right Ascension:"+ '\n'+ "angular distance measured east from arbitrary zero reference point, the Vernal Equinox Point. Analogous to longitude on the terrestrial sphere" + '\n'+ '\n'+

				"Stellar Classification:" + '\n'+"classification of stars based on spectral characteristics. Classified as O, B, A, F, G, K, M, L, T, Y with O-type being the hottest and Y-type being the coolest"+ '\n'+ '\n'+

				"V-Band Magnitude:" + '\n'+ "apparent magnitude as seen in the visual (V) part of the electromagnetic spectrum"; 

		heading.setText(head);
		body.setText(gloss);
		body.setCaretPosition(0);
		headPanel.add(heading);
		bodyPanel.add(body);

		this.add(headPanel, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);

	}

}
