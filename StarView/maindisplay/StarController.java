package maindisplay;
import java.awt.event.*;
import java.awt.geom.Point2D;

import javax.swing.JOptionPane;

//class to listen for events. also creates initial GU object
public class StarController implements MouseListener, ActionListener, FocusListener {

	private final int MAX_DIST = 50;				//Maximum distance for filter
	private final int MAX_MAG = 13;					//Maximum magnitude for filter

	private StarManager manager;
	private StarGUI gui;
	private DisplayPanel display;
	Star [] stars;

	private int distFilterInt = MAX_DIST;		//integer to pass as parameter for distance filter
	private int magFilterInt = MAX_MAG;			//integer to pass as parameter for magnitude filter
	private int hippInt = 1;					//flag to be passed to StarManager object to signal to include Hipparcos Cat: 1 include, -1 not included
	private int tyInt = -1;						//flag to be passed to StarManager object to signal to include Tycho Cat: 1 include, -1 not included
	private int glInt = -1;						//flag to be passed to StarManager object to signal to include Gliese Cat: 1 include, -1 not included
	private Star selectedStar;					//used to set the information display for star object selected by clicking
	private int mapProjFlag = 0;				//flag to signal map projection 0 = HA, 1 = Met, 2 = Cylin, 3 = OA
	private boolean nameDisBool = true;

	public StarController(StarManager m){

		manager = m;
		selectedStar = manager.getStarArray()[0];
		gui = new StarGUI(this, manager);
		gui.setVisible(true);
		display = gui.getDisplay();
	}

	public Star getSelectedStar(){return selectedStar;}
	public int getHippInt(){return hippInt;}
	public int getTyInt(){return tyInt;}
	public int getglInt(){return glInt;}
	public int getDistFilterInt(){return distFilterInt;}
	public int getMagFilterInt(){return magFilterInt;}
	public int getProjFlag(){return mapProjFlag;}
	public boolean getNameDisBool(){return nameDisBool;}

	//method to clear initial search box text
	public void focusGained(FocusEvent e)	{
		gui.searchbox.setText("");
	}

	public void focusLost(FocusEvent e)	{

	}

	public void mousePressed(MouseEvent e){	

	}


	//searches star array and returns a point if a star object is found in that position
	public void mouseClicked(MouseEvent e){

		stars = manager.getFiltArray();

		for (int i = 0; i < stars.length; i++){	
			double x =0 ;
			double y =0;
			if(mapProjFlag == 0){
				x = ((stars[i].getCartX())*display.getHAWScale() + ( display.getXdisplacement()));
				y = ((stars[i].getCartY())*display.getHAHScale() + (display.getYdisplacement()));
			}
			else if (mapProjFlag == 1){
				x = ((stars[i].getCartX())*display.getMetWScale() + ( display.getXdisplacement()));
				y = ((stars[i].getCartY())*display.getMetHScale() + (display.getYdisplacement()));
			}
			else if (mapProjFlag == 2){
				x = ((stars[i].getCartX())*display.getCylWScale() + ( display.getXdisplacement()));
				y = ((stars[i].getCartY())*display.getCylHScale() + (display.getYdisplacement()));				
			}
			else{
				if(stars[i].getRA() < 0){
					if (stars[i].getRA() > -90) 
						x = ((stars[i].getCartX())*display.getOAWScale() + ( display.getXdisplacement()) - display.getOAWidthScale() - 5);
					else
						x = ((stars[i].getCartX())*display.getOAWScale() + ( display.getXdisplacement()) + display.getOAWidthScale() +5);
				}
				else
					if (stars[i].getRA() < 90)
						x = ((stars[i].getCartX())*display.getOAWScale() + ( display.getXdisplacement())- display.getOAWidthScale() -5);
					else
						x = ((stars[i].getCartX())*display.getOAWScale() + ( display.getXdisplacement()) + display.getOAWidthScale()+5);

				y = ((stars[i].getCartY())*display.getOAHScale() + (display.getYdisplacement()));
			}



			if ( e.getX() >= x-2.5  && e.getX() <= x+2.5 && e.getY() >= y-2.5 && e.getY() <= y+2.5 ){
				Point2D.Double point = new Point2D.Double(stars[i].getCartX(), stars[i].getCartY());
				if(mapProjFlag == 0)
					display.setCircleCoords(point.x*display.getHAWScale(), point.y*display.getHAHScale());
				else if (mapProjFlag == 1)
					display.setCircleCoords(point.x*display.getMetWScale(), point.y*display.getMetHScale());
				else if (mapProjFlag == 2)
					display.setCircleCoords(point.x*display.getCylWScale(), point.y*display.getCylHScale());
				else{
					if(stars[i].getRA() < 0){
						if (stars[i].getRA() > -90) 
							display.setCircleCoords(point.x*display.getOAWScale() - display.getOAWidthScale() - 5, point.y*display.getOAHScale());
						else
							display.setCircleCoords(point.x*display.getOAWScale() + display.getOAWidthScale() +5, point.y*display.getOAHScale());
					}
					else{
						if (stars[i].getRA() < 90)
							display.setCircleCoords(point.x*display.getOAWScale() - display.getOAWidthScale() -5, point.y*display.getOAHScale());
						else
							display.setCircleCoords(point.x*display.getOAWScale() + display.getOAWidthScale() + 5, point.y*display.getOAHScale());						
					}

				}
				selectedStar = stars[i];
				display.repaint();
				gui.updateDataDisplay(i);
			}
		}
	}

	public void mouseEntered(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	//method to listen for and distinguish between user interactions
	public void actionPerformed(ActionEvent e){

		if(e.getSource() == gui.getHCB()){
			if(gui.getHCB().isSelected())hippInt = 1;
			else hippInt = -1;	
			display.repaint();
		}
		if(e.getSource() == gui.getTCB()){
			if(gui.getTCB().isSelected())tyInt = 1;
			else tyInt = -1;	
			display.repaint();
		}
		if(e.getSource() == gui.getGCB()){
			if(gui.getGCB().isSelected())glInt = 1;
			else glInt = -1;	
			display.repaint();
		}

		else if(e.getSource() == gui.getNameRadioButton()){

			if (gui.getNameRadioButton().isSelected()){
				nameDisBool = true;
			}
			else nameDisBool = false;

			display.repaint();
		}
		else if (e.getSource() == gui.getReturnButton()){							//return main display to inital coordinates
			display.setcoords();
			display.resetCoords();
			display.scale = 1;
			display.repaint();
			gui.updateDataDisplay(-1);
		}

		else if (e.getSource() == gui.getHelpButton()){								//creates UserGuide object
			UserGuide guide = new UserGuide();
		}

		else	if (e.getSource() == gui.getSearchQuery()) {							//search for star
			System.out.println("what the");
			processSearch();
		}

		else if (e.getSource() == gui.getColBox()){										//select dipslay colour
			String selectedString = (String) gui.colourBox.getSelectedItem();
			if(selectedString.equals("Spectral Type"))display.setAltColour(0);
			else if (selectedString.equals("Uniform"))display.setAltColour(1);
		}

		else if (e.getSource() == gui.getDistBox()){							//distance filter
			processDistFilter();
		}

		else	if (e.getSource() == gui.getMagBox()){							//magnitude filter
			processMagFilter();
		}

		else if(e.getSource() == gui.getProjBox()){
			processMapSelection();			
		}

		else if (e.getSource() == gui.getGlossButton()){
			
			Glossary gloss = new Glossary();	
		}
	}

	//method to process a search query
	public void processSearch(){
		String searchName = gui.getSearchQuery().getText().trim();

		Point2D.Double point = manager.searchStarArray(searchName);
		gui.searchbox.setText("");

		double HAWSc = display.getHAWScale();
		double HAHSc = display.getHAHScale();
		double metWSc = display.getMetWScale();
		double metHSc = display.getMetHScale();
		double cylWSc = display.getCylWScale();
		double cylHSc = display.getCylHScale();
		double OAWSc = display.getOAWScale();
		double OAHSc = display.getOAHScale();
		double OAWidthSc = display.getOAWidthScale();

		double ra = manager.getFiltArray()[manager.getSearchIndex()].getRA();

		if(manager.searchBool){

			if(mapProjFlag == 0)
				display.setCircleCoords(point.x * HAWSc , point.y * HAHSc);
			else if (mapProjFlag == 1)
				display.setCircleCoords(point.x * metWSc , point.y * metHSc);
			else if (mapProjFlag == 2)
				display.setCircleCoords(point.x * cylWSc , point.y * cylHSc);
			else{ if(ra < 0){
				if(ra > -90) 
					display.setCircleCoords(point.x * OAWSc - OAWidthSc - 5, point.y * OAHSc);
				else
					display.setCircleCoords(point.x * OAWSc + OAWidthSc +5, point.y * OAHSc);
			}
			else{
				if (ra < 90)
					display.setCircleCoords(point.x * OAWSc - OAWidthSc - 5, point.y * OAHSc);
				else
					display.setCircleCoords(point.x * OAWSc + OAWidthSc + 5, point.y * OAHSc);
			}
			}

			display.repaint();
			gui.updateDataDisplay(manager.getSearchIndex());
			selectedStar = manager.getFiltArray()[manager.getSearchIndex()];}
		else
			JOptionPane.showMessageDialog(null, searchName + " could not be found");
	}

	//method to process a distance filter interaction
	public void processDistFilter(){
		String selectedString = (String) gui.getDistBox().getSelectedItem();
		if(selectedString.equals("upto 5pc"))
			distFilterInt = 5;
		else if(selectedString.equals("upto 7pc"))
			distFilterInt = 7;
		else if(selectedString.equals("upto 10pc"))
			distFilterInt = 10;
		else if(selectedString.equals("upto 20pc"))
			distFilterInt = 20;
		else if(selectedString.equals("upto 30pc"))
			distFilterInt = 30;
		else 
			distFilterInt = MAX_DIST;

		display.repaint();
	}

	//method to process a magnitude filter interaction
	public void processMagFilter(){
		String selectedString = (String) gui.getMagBox().getSelectedItem();
		if(selectedString.equals("upto 10"))
			magFilterInt = 10;
		else if(selectedString.equals("upto 7 (visible to the naked eye)"))
			magFilterInt = 7;
		else if(selectedString.equals("upto 3"))
			magFilterInt = 3;
		else 
			magFilterInt = MAX_MAG;

		display.repaint();		
	}

	public void processMapSelection(){

		String selectedString = (String) gui.getProjBox().getSelectedItem();
		Star [] stars = manager.getStarArray();


		for (int i = 0; i < stars.length; i++){	
			Star star = stars[i];

			if(selectedString.equals("Hammer-Aitoff")) {
				star.setHA(); 
				mapProjFlag = 0;
			}
			else if (selectedString.equals("Mercator")){
				star.setMet();
				mapProjFlag = 1;
			}
			else if (selectedString.equals("Cylindrical Equal Area")){
				star.setCy();
				mapProjFlag = 2;
			}
			else if (selectedString.equals("Orthographic")){
				star.setOA();
				mapProjFlag = 3;
			}

			display.repaint();
		}

	}

	//create planet array if first button press then create array of all planets in system
	/*public void processExoButton(){
			if(!planetsDone){
				createP = new CreatePlanets();
				planetsDone = true;
			}
			Planet[] planets = createP.getPlanetArray();
			Planet[] subPlanetSystem = new Planet[6];
			int planetCounter = 0;
			System.out.println(planets[0].getName());
			for (Planet planet : planets){
				System.out.println(planet.getName());
				if(planet.getParent().toUpperCase().equals(selectedStar.getName().toUpperCase()) || planet.getParent().toUpperCase().equals(selectedStar.getHDid())){
					subPlanetSystem[planetCounter] = planet;
					planetCounter ++;				
				}	
			}
			if (planetCounter == 0)
				JOptionPane.showMessageDialog(null, selectedStar.getName() + " does not have any known planets");
			else{
				Planet[] displayPlanets = new Planet[planetCounter];

				for (int i = 0; i < planetCounter ;i++){
					displayPlanets[i] = subPlanetSystem[i];
				}
				ExoManager exoManager = new ExoManager(displayPlanets, selectedStar);
				exoManager.printPlanetData();
				ExoFrame exoFrame = new ExoFrame(exoManager);
			}
		}*/

	//method to process zooming action
	/*public void mouseWheelMoved(MouseWheelEvent e){
		/*	int notches = e.getWheelRotation();
		int p = e.getX();
		int y = e.getY();
		display.xdis = p/2;
		display.ydis = y/2;
		System.out.println(p + "  " + y);

		if (notches < 0 && display.getScale() < 2) {
			display.incScale();
		}		
		else if (notches > 0 && display.scale > 1) display.decScale();
		else display.setcoords();

		display.repaint();
	}*/
}
