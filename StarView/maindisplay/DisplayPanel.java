package maindisplay;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Random;

import javax.swing.*;

//class to draw star display, taking each star in array and drawing on JPanel object

public class DisplayPanel extends JPanel{

	private StarManager manager;
	private StarController controller;

	public double width, height, xdis, ydis, xdisSearch, ydisSearch;
	public double scale = 1;
	public Color altBG = new Color(1,1,45);
	private Color altSC = new Color(255,253,224);
	private int displayColourInt = 0;
	private Star[] drawArray;
	private boolean firstTime = true;
	private boolean secTime = true;

	double haw = this.width * 0.170;;
	double hah = this.height* 0.272;;
	double metWidScale = this.width * 0.0027;;
	double metHeightScale = this.height *  0.0004;;
	double cylinWSc  = this.width* 0.0024;;
	double cylinHSc = this.height * 0.42;
	double OAWSc = this.width * 0.2238;
	double OAHSc = this.height * 0.3506;
	double OAWidthScale = this.width*0.2238;  //scale for x-displacement for OA proj

	public DisplayPanel(StarManager m, StarController c){

		manager = m;
		controller = c;		
		//setBounds(0, 0,  1698, 974);
		setcoords();
		resetCoords();
		setBackground(altBG);
	}

	public double getXdisplacement(){ return xdis;}
	public double getYdisplacement(){return ydis;}
	public double getScale(){return scale;}
	public double getHAWScale(){return haw;}
	public double getHAHScale(){return hah;}
	public double getMetWScale(){return metWidScale;}
	public double getMetHScale(){return metHeightScale;}
	public double getCylWScale(){return cylinWSc;}
	public double getCylHScale(){return cylinHSc;}
	public double getOAWScale(){return OAWSc;}
	public double getOAHScale(){return OAHSc;}
	public double getOAWidthScale(){return OAWidthScale;}


	//method to change colour of star display
	public void setAltColour(int i){
		displayColourInt = i;
		repaint();
	}

	public int getColourInt(){return displayColourInt;}

	//adds scale factor on to scale instance variable
	public void incScale(){
		scale += 0.25;
	}

	//reduces scale instance variable by scale factor
	public void decScale(){
		scale -= 0.25;
	}

	// method to set (0,0) on display
	public void setcoords(){
		height = this.getHeight();
		width = this.getWidth();
		xdis = (width/2);
		ydis = (height/2);
	}

	//method to set the co-ordinates of the high-lighter circle
	public void setCircleCoords(double x, double y){
		resetCoords();
		xdisSearch += x;
		ydisSearch += y;
	}

	//method to set the co-ordinates back to original origin so that next move calculated from (0,0)
	public void resetCoords(){
		xdisSearch = xdis;
		ydisSearch = ydis;
	}


	//take filter array as parameter and draws each star graphic appropriately on display
	public void	paint(Graphics g){	
		setcoords();
		super.paint(g);

		haw = this.width * 0.170;
		hah = this.height* 0.272;
		metWidScale = this.width * 0.0027;
		metHeightScale = this.height *  0.0004;
		cylinWSc = this.width* 0.0024;
		cylinHSc = this.height * 0.42;
		OAWSc = this.width * 0.2238;
		OAHSc = this.height * 0.3506;
		OAWidthScale = this.width*0.2238;

		int dist = controller.getDistFilterInt();
		int mag = controller.getMagFilterInt();
		int hipp = controller.getHippInt();
		int ty = controller.getTyInt();
		int gl = controller.getglInt();

		Graphics2D searchg = (Graphics2D) g;      //high-lighter circle
		Ellipse2D.Double searchCircle = new Ellipse2D.Double(xdisSearch - 10, ydisSearch - 10, 20, 20);
		searchg.setColor(Color.cyan);
		searchg.scale(scale, scale);
		searchg.draw(searchCircle);

		Graphics2D g2 = (Graphics2D) g;
		g2.scale(scale, scale);
		Ellipse2D.Double point;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		manager.setFilterArray(dist, mag, hipp, ty, gl);
		drawArray = manager.getFiltArray();
		for (Star star : drawArray){
			if(controller.getProjFlag() == 0)

				point = new Ellipse2D.Double(((star.getCartX()*haw) + xdis - (star.getCartZ()/2)), ((star.getCartY())*hah + ydis - (star.getCartZ()/2)), star.getCartZ(), star.getCartZ());
			else if(controller.getProjFlag() == 1)
				point = new Ellipse2D.Double(((star.getCartX()*metWidScale) + xdis - (star.getCartZ()/2)), ((star.getCartY())*metHeightScale + ydis - (star.getCartZ()/2)), star.getCartZ(), star.getCartZ());
			else if(controller.getProjFlag() == 2)
				point = new Ellipse2D.Double(((star.getCartX()*cylinWSc) + xdis - (star.getCartZ()/2)), ((star.getCartY())*cylinHSc + ydis - (star.getCartZ()/2)), star.getCartZ(), star.getCartZ());
			else
			{	if(star.getRA() < 0){

				if (star.getRA() > -90) 
					point = new Ellipse2D.Double(((star.getCartX()*OAWSc) + xdis - OAWidthScale - 5 -(star.getCartZ()/2)), ((star.getCartY()*OAHSc) + ydis - (star.getCartZ()/2)), star.getCartZ(), star.getCartZ());			//ra between 0 and -90
				else
					point = new Ellipse2D.Double(((star.getCartX()*OAWSc) + xdis - (star.getCartZ()/2) +OAWidthScale +5), ((star.getCartY()*OAHSc) + ydis - (star.getCartZ()/2)), star.getCartZ(), star.getCartZ());						//ra between -90 and -180
			}
			else 
				if (star.getRA() < 90)
					point = new Ellipse2D.Double(((star.getCartX()*OAWSc) + xdis - (star.getCartZ()/2) -OAWidthScale -5), ((star.getCartY()*OAHSc) + ydis - (star.getCartZ()/2)), star.getCartZ(), star.getCartZ());
				else
					point = new Ellipse2D.Double(((star.getCartX()*OAWSc) + xdis - (star.getCartZ()/2) + OAWidthScale+5), ((star.getCartY()*OAHSc) + ydis - (star.getCartZ()/2)), star.getCartZ(), star.getCartZ());

			}

			if (displayColourInt == 0)g2.setColor(star.getDisplayColor());
			else if (displayColourInt == 1)g2.setColor(altSC);


			g2.fill(point);
			if(controller.getNameDisBool()){
				if (star.getCommonNameBool()){
					if(controller.getProjFlag() == 0 )
						g2.drawString(star.getName(), (int) ((star.getCartX()*haw + xdis + 5)), (int) ((star.getCartY()*hah + ydis + 5)));
					else if(controller.getProjFlag() == 1)
						g2.drawString(star.getName(), (int) ((star.getCartX()*metWidScale + xdis + 5)), (int) ((star.getCartY()*metHeightScale + ydis + 5)));
					else if(controller.getProjFlag() == 2)
						g2.drawString(star.getName(), (int) ((star.getCartX()*cylinWSc + xdis + 5)), (int) ((star.getCartY()*cylinHSc + ydis + 5)));
					else{
						if(star.getRA() < 0){
							if (star.getRA() > -90) 
								g2.drawString(star.getName(), (int) ((star.getCartX()*OAWSc + xdis + 5 -OAWidthScale - 5)), (int) ((star.getCartY()*OAHSc + ydis + 5)));		
							else
								g2.drawString(star.getName(), (int) ((star.getCartX()*OAWSc + xdis + 5 + OAWidthScale +5)), (int) ((star.getCartY()*OAHSc + ydis + 5)));		
						}
						else
							if (star.getRA() < 90)
								g2.drawString(star.getName(), (int) ((star.getCartX()*OAWSc + xdis + 5 - OAWidthScale -5)), (int) ((star.getCartY()*OAHSc + ydis + 5)));		
							else
								g2.drawString(star.getName(), (int) ((star.getCartX()*OAWSc + xdis + 5 + OAWidthScale + 5)), (int) ((star.getCartY()*OAHSc + ydis + 5)));		

					}
				}

			}
		}
	}

	public void setscale(){
		xdis = xdis * scale;
		ydis = ydis*scale;
	}

}