package entities;



public class Pilot{
    
 
    String fNamel;
    String Lname;
    int license;
   enums.PlainSize maxSizePlain;
     int maxTimeFlight;
     
     
	public Pilot(String fNamel, String lname, int license,
			enums.PlainSize maxSizePlain, int maxTimeFlight) {
		super();
		this.fNamel = fNamel;
		Lname = lname;
		this.license = license;
		this.maxSizePlain = maxSizePlain;
		this.maxTimeFlight = maxTimeFlight;
	}
	public Pilot() {
		super();
		this.fNamel = null;
		Lname = null;
		this.license = 0;
		this.maxSizePlain = null;
		this.maxTimeFlight = 0;
	}
	public String getfNamel() {
		return fNamel;
	}
	public void setfNamel(String fNamel) {
		this.fNamel = fNamel;
	}
	public String getLname() {
		return Lname;
	}
	public void setLname(String lname) {
		Lname = lname;
	}
	public int getLicense() {
		return license;
	}
	public void setLicense(int license) {
		this.license = license;
	}
	public enums.PlainSize getMaxSizePlain() {
		return maxSizePlain;
	}
	public void setMaxSizePlain(enums.PlainSize maxSizePlain) {
		this.maxSizePlain = maxSizePlain;
	}
	public int getMaxTimeFlight() {
		return maxTimeFlight;
	}
	public void setMaxTimeFlight(int maxTimeFlight) {
		this.maxTimeFlight = maxTimeFlight;
	}
}