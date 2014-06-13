package com.hundsun.ares.studio.core.model.util;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * @author gongyf
 *
 */
public class RevisionHistoryVersion implements Comparable<RevisionHistoryVersion> {
	
	private final int			major;
	private final int			minor;
	private final int			micro;
	private final int		qualifier;
	private static final String	SEPARATOR		= ".";	
	
	public static final RevisionHistoryVersion	emptyVersion	= new RevisionHistoryVersion(0, 0, 0);
	
	public RevisionHistoryVersion(int major, int minor, int micro) {
		this(major, minor, micro, 0);
	}
	
	
	public RevisionHistoryVersion(int major, int minor, int micro, int qualifier) {

		this.major = major;
		this.minor = minor;
		this.micro = micro;
		this.qualifier = qualifier;
	}
	
	public RevisionHistoryVersion(String version) {
		int maj = 0;
		int min = 0;
		int mic = 0;
		int qual = 0; 

		try {
			StringTokenizer st = new StringTokenizer(version, SEPARATOR, true);
			maj = Integer.parseInt(st.nextToken());

			if (st.hasMoreTokens()) {
				st.nextToken(); // consume delimiter
				min = Integer.parseInt(st.nextToken());

				if (st.hasMoreTokens()) {
					st.nextToken(); // consume delimiter
					mic = Integer.parseInt(st.nextToken());

					if (st.hasMoreTokens()) {
						st.nextToken(); // consume delimiter
						qual = Integer.parseInt(st.nextToken());

						if (st.hasMoreTokens()) {
							throw new IllegalArgumentException("invalid format"); //$NON-NLS-1$
						}
					}
				}
			}
		} catch (NumberFormatException e) {
			// 似乎不需要处理
		}
		catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid format"); //$NON-NLS-1$
		}

		major = maj;
		minor = min;
		micro = mic;
		qualifier = qual;
	}
	
	/**
	 * @return the major
	 */
	public int getMajor() {
		return major;
	}
	
	/**
	 * @return the micro
	 */
	public int getMicro() {
		return micro;
	}
	
	/**
	 * @return the minor
	 */
	public int getMinor() {
		return minor;
	}
	
	/**
	 * @return the qualifier
	 */
	public int getQualifier() {
		return qualifier;
	}
	
	public int hashCode() {
		return (major << 24) + (minor << 16) + (micro << 8)
				+ qualifier;
	}
	
	public boolean equals(Object object) {
		if (object == this) { // quicktest
			return true;
		}

		if (!(object instanceof RevisionHistoryVersion)) {
			return false;
		}

		RevisionHistoryVersion other = (RevisionHistoryVersion) object;
		return (major == other.major) && (minor == other.minor)
				&& (micro == other.micro) && qualifier == other.qualifier;
	}
	
	public int compareTo(RevisionHistoryVersion other) {
		if (other == this) { // quicktest
			return 0;
		}

		int result = major - other.major;
		if (result != 0) {
			return result;
		}

		result = minor - other.minor;
		if (result != 0) {
			return result;
		}

		result = micro - other.micro;
		if (result != 0) {
			return result;
		}

		return qualifier - other.qualifier;
	}
	
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(major);
		result.append(SEPARATOR);
		result.append(minor);
		result.append(SEPARATOR);
		result.append(micro);
		result.append(SEPARATOR);
		result.append(qualifier);
		return result.toString();
	}
}