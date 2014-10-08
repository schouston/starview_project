package commonnames;

public class CommonName {

	private String name;
	private int hippID;


	public CommonName(String n, int id){
		name = n;
		hippID = id;
	}

	public String getName(){ return name;}
	public int getID(){return hippID;}

}
