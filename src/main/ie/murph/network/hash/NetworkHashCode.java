package main.ie.murph.network.hash;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class NetworkHashCode {
	private static final Logger LOGGER = LogManager.getLogger(NetworkHashCode.class.getName());
	public int hash = 31;
	
	public static void main() {
		LOGGER.info("++main()");
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hash;
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
		NetworkHashCode other = (NetworkHashCode) obj;
		if (hash != other.hash)
			return false;
		return true;
	}
}
