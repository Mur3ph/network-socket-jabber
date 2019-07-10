package main.ie.murph.network.distributed.hashtable.domain;

import main.ie.murph.network.distributed.hashtable.domain.Enum.Mark;
import main.ie.murph.network.distributed.hashtable.domain.Enum.Position;

public class Point {

	private Mark mark;
	private Position position;
	
	public Point(Mark mark){
		this.mark = mark;
	}
	
	public void setMark(Mark mark){
		this.mark = mark;
	}
	
	public Mark getMark(Mark mark){
		return this.mark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
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
		if (mark != other.mark)
			return false;
		if (position != other.position)
			return false;
		return true;
	}
	
	
}
