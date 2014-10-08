package maindisplay;
import java.awt.Color;
import java.awt.geom.Point2D;
import mapprojections.*;

public class Star {

	private final double TEMP_CONSTANT_A = 14.551;			//constants used in setTemperature method
	private final double TEMP_CONSTANT_B = 3.684;			//

	private int starViewID;									//unique starview id
	private String name;									//common name/catalogue id and number if no common name
	private String idNum;									//hipparcos/tycho/gliese id
	private int hippID;										// hipparcos id number used for common name processing
	private double distance;								//distance from sun in pc
	private double apMag;									//apparent (V-band) magnitude
	private double abMag;									//absolute magnitude
	private double mass;									//stellar mass (solar-radi)
	private String stellarClass;							//stellar classification - used for colour
	private double temp;									//stellar temp (K)
	private boolean inSystem;								//boolean indicating presence in star system
	private boolean exoSystem;								//boolean indicating presence of exo-planet system
	private double ra;										//right ascension
	private double dec;										//declination
	private double cartX;									//x coordinate to plot on display
	private double cartY;									//y coordinate to plot on display
	private double cartZ;									//z coordinate to plot on display
	private double colourIndex;								//b-v colour index to calculate temperature
	private Color displayColor;
	private double parallax; 								//milli-arc seconds
	private String ccdmID = "0";
	private boolean hasCommonName;
	private String HDid;									//Henry Draper Catalogue id, 0 if no id present
	private String HRid;									//Harvard Revised Catalogue id, 0 if no id present
	private String catalogueID;								//"H" for Hipparcos, "T" for Tycho, "G" for Gliese
	private double displaySize;

	private MapProjection proj =  new MapProjection();

	private Color O = new Color(219,242,252);
	private Color B = new Color(166,225,252);
	private Color A = new Color(242,249,252);
	private Color F = new Color(251,252,191);
	private Color G = new Color(250,252,133);
	private Color K = new Color(252,211,108);
	private Color M = new Color(255,107,94);
	private Color L = new Color(127,72,67);

	//blank constructor, used in testing
	//public Star(){}

	//constructor taking parameters from specialist StarView Data files
	public Star(String catID, String id, int sid, double vm, double r, double d, double p, double ci, String st, String cid, String hd, String hr){

		catalogueID = catID;
		starViewID = sid;
		idNum = id;
		apMag = vm;

		if (catalogueID.equals("H")) hippID = Integer.parseInt(id);
		setRA(r);
		if(catalogueID.equals("H") || catalogueID.equals("T"))correctPosition();

		dec = d;
		parallax = p;
		colourIndex = ci;
		if (st.equals("DEFAULT"))setStellarClass();
		else		
			stellarClass = st;

		ccdmID = cid;
		if (!cid.equals("0")) inSystem = true;
		HDid = hd;
		HRid = hr;

		setDistance();
		setAbsMag();
		setTemp();
		if (catalogueID.equals("T")) setStellarClass();
		cartX = proj.setHAProjection(ra,dec).x;
		cartY = proj.setHAProjection(ra, dec).y;
		calcZCoord();
		setDisplayColor();
	}

	public int getSVid(){return starViewID;}
	public String getName(){return name;}
	public String getID(){return idNum;}
	public int getHippID(){return hippID;}
	public double getDistance(){return distance;}
	public double getApMag(){return apMag;}
	public double getAbMag(){return abMag;}
	public double getRA(){return ra;}
	public double getDec(){return dec;}
	public double getMass(){return mass;}
	public String getStellarClass(){return stellarClass;}
	public double getTemp(){return temp;}
	public boolean getInSystem(){return inSystem;}
	public boolean inExoSystem(){return exoSystem;}
	public double getCartX(){return cartX;}
	public double getCartY(){return cartY;}
	public double getCartZ(){return cartZ;}
	public double getParallax(){return parallax ;}
	public double getcolourIndex(){return colourIndex;}
	public Color getDisplayColor(){return displayColor;}
	public String getHDid(){return HDid;}
	public String getHRid(){return HRid;}
	public double getDisplaySize(){return displaySize;}
	public String getCatalogueID(){return catalogueID;}
	public String getccdmID(){return ccdmID;}
	public boolean getCommonNameBool(){return hasCommonName;}
	public Color getDisplayColour(){return displayColor;}
	public void setCommonNameBoole(boolean b){hasCommonName = b;}
	public void setBV(double bv){colourIndex = bv;	}
	public void setParallax(double p){parallax = p;}


	public void setName(String s){
		name = s;		
	}

	private void setRA(double r){

		if (r>180) ra = r - 360;
		else
			ra = r;		
	}

	//sets position to Epoch J2000
	private void correctPosition(){
		double changePerYear = 0.01388888;
		double noYears = 9;
		double totalChange = changePerYear * noYears;

		ra += totalChange;	
	}

	//method to set the star temperature using colourIndex and formula log(T) = (14.551 - colourIndex)/3.684
	private void setTemp(){
		double logTemp = (TEMP_CONSTANT_A - colourIndex)/TEMP_CONSTANT_B;
		temp = Math.pow(10, logTemp);
	}

	//method to calculate and set star distance using parallax (d = 1/p, p in arcseconds)
	private void setDistance(){
		double paraAS = parallax/1000;
		distance = 1/(paraAS);
	}

	//method to calc z component - display range ie size for each unit of distance, then multiply be the maxdistance minus actual distance
	private void calcZCoord(){

		int minSize = 2; 								// minimum display size, ie furthest will be 2 pixels wide
		double displayRange = 5;						//range of display values
		double distanceRange = 40;
		double displayRatio = displayRange/distanceRange;
		cartZ = displayRatio * (distanceRange -distance) + minSize;
	}

	//set coordinate for appropriate projection
	private void setMapProjection(Point2D.Double p){
		cartX = p.x;
		cartY = p.y;
	}

	public void setHA(){
		cartX = proj.setHAProjection(ra, dec).x;
		cartY = proj.setHAProjection(ra, dec).y;
	}

	public void setOA(){
		cartX = proj.setOAProjection(ra, dec).x;
		cartY = proj.setOAProjection(ra, dec).y;
	}

	public void setCy(){
		cartX = proj.setCylinProjection(ra, dec).x;
		cartY = proj.setCylinProjection(ra, dec).y;
	}

	public void setMet(){
		cartX = proj.setMetProjection(ra, dec).x;
		cartY = proj.setMetProjection(ra, dec).y;

	}

	//method to set absolute magnitude
	private void setAbsMag(){
		abMag = apMag + 5 - (5*(Math.log10(distance)));
	}

	//set colour of star graphic appropriate to stellar class
	private void setDisplayColor(){

		if(stellarClass.substring(0, 1).equals("O")) displayColor = O;
		else if (stellarClass.substring(0, 1).equals("B"))displayColor = B;
		else if (stellarClass.substring(0, 1).equals("A"))displayColor = A;
		else if (stellarClass.substring(0, 1).equals("F"))displayColor = F;
		else if (stellarClass.substring(0, 1).equals("G"))displayColor =G;
		else if (stellarClass.substring(0, 1).equals("K"))displayColor = K;
		else if (stellarClass.substring(0, 1).equals("M"))displayColor =M;
		else if (stellarClass.substring(0, 1).equals("L"))displayColor =L;
		else displayColor = G;
	}

	//set stellar class for Tycho objects dependent on temperature 
	private void setStellarClass(){

		if (temp > 30000) stellarClass = "O";
		else if (temp > 10000) stellarClass = "B";
		else if (temp > 7500) stellarClass = "A";
		else if (temp > 6000) stellarClass = "F";
		else if (temp > 5000) stellarClass = "G";
		else if (temp > 3500) stellarClass = "K";
		else if (temp < 3500) stellarClass = "M";
		else stellarClass = "DEFAULT";
	}
}
