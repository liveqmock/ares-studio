package com.hundsun.ares.studio.internal.core;

import com.hundsun.ares.studio.internal.core.ARESElement;

public class MementoTokenizer {
	
	private static final String ARESPROJECT = Character.toString(ARESElement.AEM_ARESPROJECT);
	private static final String MODULEROOT = Character.toString(ARESElement.AEM_MODULEROOT);
	private static final String MODULE = Character.toString(ARESElement.AEM_MODULE);
	private static final String COMPILATIONUNIT = Character.toString(ARESElement.AEM_RESOURCE);
	private static final String LIB = Character.toString(ARESElement.AEM_LIB);

	private final char[] memento;
	private final int length;
	private int index = 0;

	public MementoTokenizer(String memento) {
		this.memento = memento.toCharArray();
		this.length = this.memento.length;
	}

	public boolean hasMoreTokens() {
		return this.index < this.length;
	}

	public String nextToken() {
		int start = this.index;
		StringBuffer buffer = null;
		switch (this.memento[this.index++]) {
			case ARESElement.AEM_ESCAPE:
				buffer = new StringBuffer();
				buffer.append(this.memento[this.index]);
				start = ++this.index;
				break;
			case ARESElement.AEM_ARESPROJECT:
				return ARESPROJECT;
			case ARESElement.AEM_MODULEROOT:
				return MODULEROOT;
			case ARESElement.AEM_MODULE:
				return MODULE;
			case ARESElement.AEM_RESOURCE:
				return COMPILATIONUNIT;
			case ARESElement.AEM_LIB:
				return LIB;
		}
		loop: while (this.index < this.length) {
			switch (this.memento[this.index]) {
				case ARESElement.AEM_ESCAPE:
					if (buffer == null) buffer = new StringBuffer();
					buffer.append(this.memento, start, this.index - start);
					start = ++this.index;
					break;
				case ARESElement.AEM_ARESPROJECT:
				case ARESElement.AEM_MODULEROOT:
				case ARESElement.AEM_MODULE:
				case ARESElement.AEM_RESOURCE:
				case ARESElement.AEM_LIB:
					break loop;
			}
			this.index++;
		}
		if (buffer != null) {
			buffer.append(this.memento, start, this.index - start);
			return buffer.toString();
		} else {
			return new String(this.memento, start, this.index - start);
		}
	}

}
