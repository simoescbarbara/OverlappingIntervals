package model;

public class Interval {
	
	//first number of the interval
	private Integer start;
	//last number of the interval 
	private Integer end;
	
	//class constructor 
	public Interval(Integer start, Integer end) {
		super();
		this.start = start;
		this.end = end;
	}   
	  
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}

	
}
