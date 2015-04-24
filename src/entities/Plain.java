package entities;

public class Plain{
    // huge>500>big>300>medium>100>small    
    
	String model;
    int wingId;
    int numberPassengers;
    float length;
    Boolean hasVideo;
   enums. PlainSize size;
    
    
    public Plain(String model, int wingId, int numberPassengers,
			float length, Boolean hasVideo, enums.PlainSize size) {
		super();
		this.model = model;
		this.wingId = wingId;
		this.numberPassengers = numberPassengers;
		this.length = length;
		this.hasVideo = hasVideo;
		this.size = size;
	}
    public Plain() {
		super();
		this.model = null;
		this.wingId = 0;
		this.numberPassengers = 0;
		this.length = 0;
		this.hasVideo = null;
		this.size = null;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getWingId() {
		return wingId;
	}
	public void setWingId(int wingId) {
		this.wingId = wingId;
	}
	public int getNumberPassengers() {
		return numberPassengers;
	}
	public void setNumberPassengers(int numberPassengers) {
		this.numberPassengers = numberPassengers;
	}
	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
	public Boolean getHasVideo() {
		return hasVideo;
	}
	public void setHasVideo(Boolean hasVideo) {
		this.hasVideo = hasVideo;
	}
	public enums.PlainSize getSize() {
		return size;
	}
	public void setSize(enums.PlainSize size) {
		this.size = size;
	}

}