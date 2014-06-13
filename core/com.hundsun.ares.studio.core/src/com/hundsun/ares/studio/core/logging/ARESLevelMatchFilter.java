package com.hundsun.ares.studio.core.logging;

import org.apache.log4j.Level;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * ARESµÈ¼¶Æ¥Åä¹ýÂËÆ÷¡£
 * 
 * @author mawb
 */
public class ARESLevelMatchFilter extends Filter {
	
	Level levelToMatch;
	
	/**
	 * @param level
	 */
	public ARESLevelMatchFilter(Level levelToMatch) {
		super();
		this.levelToMatch = levelToMatch;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.log4j.varia.LevelMatchFilter#decide(org.apache.log4j.spi.LoggingEvent)
	 */
	@Override
	public int decide(LoggingEvent event) {
		if(levelToMatch == null) {
			return Filter.NEUTRAL;
		}
		    
		if(levelToMatch.equals(event.getLevel())) {
			return Filter.ACCEPT;
		} 
		
		return Filter.DENY;
	}
	
}
