package main.ie.murph.network.distributed.hashtable.domain;

public class Point {

	private String mark = "";
	
	public Point(String mark){
		this.mark = mark;
	}
	
	public void setMark(String mark){
		this.mark = mark;
	}
	
	public String getMark(String mark){
		return this.mark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (mark == null) {
			if (other.mark != null)
				return false;
		} else if (!mark.equals(other.mark))
			return false;
		return true;
	}
	
	
}
