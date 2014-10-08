package mapprojections;
import java.awt.geom.Point2D;

//class to calculate appropriate map projection
public class MapProjection {

	private double SCALING_FACTOR = 1;
	private double dec;
	private double ra;
	private double cartX;
	private double cartY;

	public MapProjection(){}

	public MapProjection(double r, double d){
		ra = r;
		dec = d;
	}

	//calculate x coordinate for HA Equal Area Projection
	private double calcHAXCoord(){

		double operand1 = 2 * (Math.sqrt(2))* (Math.cos(Math.toRadians(dec))) * (Math.sin(Math.toRadians(ra/2)));
		double operand2 = Math.sqrt(1 + (Math.cos(Math.toRadians(dec)) * Math.cos(Math.toRadians(ra/2))));
		double x = (operand1/operand2);	
		cartX = x * SCALING_FACTOR;
		return cartX;
	}
	//calculate y coordinate for HA Projection
	private double calcHAYCoord(){

		double operand1 = Math.sqrt(2)* Math.sin(Math.toRadians(dec));
		double operand2 = Math.sqrt(1 + (Math.cos(Math.toRadians(dec)) * Math.cos(Math.toRadians(ra/2))));
		double y = (operand1/operand2);
		cartY = (y) * (-1) * SCALING_FACTOR; 						//reflected about y-axis
		return cartY;
	}

	public Point2D.Double setHAProjection(double r, double d){
		ra = r;
		dec = d;
		SCALING_FACTOR = 1;
		double x = calcHAXCoord();
		double y = calcHAYCoord();
		Point2D.Double point = new Point2D.Double(x,y);
		return point;
	}

	//Lamberts Cylindical Equal Area
	public Point2D.Double setCylinProjection(double r, double d){    
		ra = r;
		dec = d;
		double x = ra ;
		cartX =  x ;

		double y = Math.sin(Math.toRadians(dec));
		cartY = -y;

		Point2D.Double point = new Point2D.Double(cartX, cartY);

		return point;
	}
	
	//sets Orthographic - Azimuthal projection
	public Point2D.Double setOAProjection(double r, double d){
		ra = r;
		dec = d;
		SCALING_FACTOR = 1;

		double x = Math.cos(Math.toRadians(dec)) * Math.sin(Math.toRadians(ra));
		double y = Math.sin(Math.toRadians(dec)) ;
		if (ra < 0){
			if (ra > -90) cartX = x *  SCALING_FACTOR;			//ra between 0 and -90
			else
				cartX =  x * SCALING_FACTOR;					//ra between -90 and -180
		}
		else {
			if (ra < 90)											//ra between 0 and 90
				cartX =  x * SCALING_FACTOR ;
			else cartX = x * SCALING_FACTOR ;			// ra betwwn 90 and 180
		}
		cartY =  y * - SCALING_FACTOR;
		Point2D.Double point = new Point2D.Double(cartX, cartY);
		return point;
	}

	//set Mercator Projection
	public Point2D.Double setMetProjection(double r, double d){
		ra = r;
		dec = d;
	
		double y2 = 1700 / (2* Math.PI) * Math.log(Math.tan((Math.PI / 4) + (Math.toRadians(dec)/2)));
				
		cartX = ra ;
		cartY = -y2 ;

		Point2D.Double point = new Point2D.Double(cartX, cartY);
		return point;
	}
}
