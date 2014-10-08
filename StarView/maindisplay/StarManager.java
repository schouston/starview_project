package maindisplay;
import java.awt.geom.Point2D;
import javax.swing.JOptionPane;


//class to store and manipulate array of Star Objects
public class StarManager{

	private final int SIZE;								//size of complete array of star objects
	private Star[] starArray;							//array of star objects
	private Star [] filterArray;
	public static int starArrayCounter = 0;				//counter for number of star objects entered
	private int searchIndex = 0;						//gives index of last object search for
	//public boolean filterSelected = false;
	public boolean searchBool;
	int fcounter;

	public StarManager(int s){
		SIZE = s;
		starArray = new Star[SIZE];
	}

	//method to insert a star object into star array
	public void addStar(Star s){
		Star star = s;
		starArray[starArrayCounter] = star;
		starArrayCounter ++;
	}

	public int getStarViewID(){return starArrayCounter;}

	//method to format String of star data for star at position i of array
	public String printStarData(int i){

		int index = i;
		Star star = filterArray[index];

		String name = "Name: ";
		String svid = "StarView ID: ";
		String hippid = "Catalogue ID: ";
		String apmag = "Apparent Mag: ";
		String abmag = "Absolute Mag: ";
		String dist = "Distance: ";
		String parsec = "pc";
		String r = "Right Ascension: ";
		String d = "Declination: ";
		String temp = "Temperature: ";
		String type = "Spectral Type: ";
		String hd = "Henry Draper: ";
		String inSystem = ""; 
		String kelvin = "K";

		if (star.getccdmID().equals("0")) inSystem = "Not in Star System";
		else if (star.getccdmID().equals("1")) inSystem = "In Unresolved Star System";
		else inSystem = "In Star System";



		String starData = String.format("%s %s" + '\n' + '\n'+ "%s %d" + '\n' + "%s %s" + '\n' + "%s%s"+ '\n' +"%s%.2f%s" + '\n' + "%s%.2f" + '\n' + "%s%.2f" + '\n' + "%s%.2f" + '\n' + "%s%.2f" + '\n' + "%s%.0f %s" + '\n'+
				"%s%s" + '\n' + "%s" + '\n'  ,name, star.getName(), svid, star.getSVid(), hippid, star.getID(),  hd, star.getHDid(), dist, star.getDistance(), parsec, apmag, star.getApMag(), abmag, star.getAbMag(), r, star.getRA(), d, star.getDec(), temp,
				star.getTemp(), kelvin, type, star.getStellarClass(), inSystem);

		return starData;
	}

	//method to format a String object for data on the Sun
	public String printSunData(){

		String name = "Name: ";
		String svid = "StarView ID: ";
		String apmag = "Apparent Mag: ";
		String abmag = "Absolute Mag: ";
		String dist = "Distance: ";
		String r = "Right Ascension: ";
		String d = "Declination: ";
		String temp = "Temperature: ";
		String type = "Spectral Type: ";

		String starData = String.format("%s%s" + '\n' + '\n'+ "%s %s" + '\n' + "%s %s" + '\n' + "%s%s" + '\n' + "%s%s" + '\n' + "%s%s" + '\n' + "%s%s" + '\n' + "%s%s" + '\n' + "%s%s" + '\n' + "%s"+ '\n'
				,name, "Sun", svid, "0", dist, "0pc", apmag, "-26.74", abmag, "4.83", r, "0", d, "0", temp,
				"5778 K", type, "G2V", "Not in Star System");		
		return starData;
	}

	//method to return complete star array
	public Star[] getStarArray(){
		return starArray;
	}

	//method returning a point corresponding to display coordinates of found star object
	public Point2D.Double searchStarArray(String s){
		searchBool = false;
		Point2D.Double nullpoint = new Point2D.Double(0, 0);
		for (int i= 0; i<fcounter; i++){
			Star star = filterArray[i];
			if (star.getName().toUpperCase().equals(s.toUpperCase())){
				String message = String.format("%s%s%.2f%s%.2f", star.getName(), " is at position Right Ascension ", star.getRA(),  " and Declination ", star.getDec() );
				JOptionPane.showMessageDialog(null, message);
				Point2D.Double point = new Point2D.Double(star.getCartX(), star.getCartY());
				searchIndex = i;
				searchBool = true;
				return point;
			}
		}
		return nullpoint;
	}

	//method to get the coordinates of star at specific index of star array
	public Point2D.Double getcoords(int i){
		Star star = starArray[i];
		Point2D.Double point = new Point2D.Double(star.getCartX(), star.getCartY());
		return point;
	}

	//method to return the array index last star to have been search for 
	public int getSearchIndex(){
		return searchIndex;
	}

	//method to select appropriate star objects to the filter array
	public void setFilterArray(int d, int m, int h, int t, int g){

		Star[] fArray = new Star[SIZE];
		fcounter = 0;
		int hippInt = h;
		int tyInt = t;
		int glInt = g;

		for (int i = 0; i < SIZE; i++){

			if (starArray[i].getApMag()<= m && starArray[i].getDistance() <= d){

				if(hippInt == 1){
					if(starArray[i].getCatalogueID().equals("H")){
						fArray[fcounter] = starArray[i];
						fcounter ++;
					}
				}
				if(tyInt == 1){
					if(starArray[i].getCatalogueID().equals("T")){
						fArray[fcounter] = starArray[i];
						fcounter ++;
					}
				}
				if(glInt == 1){
					if(starArray[i].getCatalogueID().equals("G")){
						fArray[fcounter] = starArray[i];
						fcounter ++;
					}
				}
			}
		}

		filterArray = new Star[fcounter];

		for (int j = 0; j < fcounter; j++){
			filterArray[j] = fArray[j];
		}
	}

	//method to return array of filtered star objects
	public Star[] getFiltArray(){return filterArray;}
}