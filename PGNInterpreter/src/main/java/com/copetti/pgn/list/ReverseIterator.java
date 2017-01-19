package com.copetti.pgn.list;

import java.util.Iterator;
import java.util.List;

public class ReverseIterator<T> implements Iterable<T>, Iterator<T> {

	private List<T> list;
	private int pos;

	public ReverseIterator(List<T> list) {
		this.list = list;
		pos = list.size();
	}

	@Override
	public Iterator<T> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return pos >= 0;
	}

	@Override
	public T next() {
		return list.get(--pos);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
