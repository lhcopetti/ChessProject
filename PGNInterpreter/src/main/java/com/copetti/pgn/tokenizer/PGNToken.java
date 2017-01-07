package com.copetti.pgn.tokenizer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
public class PGNToken {

	private @Getter TokenTypes tokenType;
	private @Getter String tokenValue;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tokenType == null) ? 0 : tokenType.hashCode());
		result = prime * result + ((tokenValue == null) ? 0 : tokenValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PGNToken))
			return false;
		PGNToken other = (PGNToken) obj;
		if (tokenType == null) {
			if (other.tokenType != null)
				return false;
		} else if (!tokenType.equals(other.tokenType))
			return false;
		if (tokenValue == null) {
			if (other.tokenValue != null)
				return false;
		} else if (!tokenValue.equals(other.tokenValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return tokenType + " " + tokenValue;
	}

}
