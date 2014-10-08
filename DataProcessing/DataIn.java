package dataprocessing;

public abstract class DataIn {
	
	protected String IN_FILE;
	protected String OUT_FILE;
	protected String id;
	protected double mag;
	protected double ra;
	protected double dec;
	protected double par;
	protected double colourInd;
	protected String specType;
	protected String ccdmID;
	protected String catalogueID;

	protected String HDid = "0";
	protected String HRid = "0";
	protected String HIPid;
	 
	 
	 void processFile(String s, String o){
		 
	 }

}
