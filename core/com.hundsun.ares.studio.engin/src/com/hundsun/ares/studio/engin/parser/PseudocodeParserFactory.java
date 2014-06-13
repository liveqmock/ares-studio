package com.hundsun.ares.studio.engin.parser;

public class PseudocodeParserFactory implements IPseudocodeParserFactory{

	public static PseudocodeParserFactory instance = new PseudocodeParserFactory();
	
	@Override
	public IPseudocodeParser createParser() {
		return new PseudoCodeParser();
	}

}
