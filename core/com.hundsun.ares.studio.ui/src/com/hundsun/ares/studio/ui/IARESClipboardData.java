package com.hundsun.ares.studio.ui;

public interface IARESClipboardData {
    /**
     * Retrieves the format type
     * 
     * @return the format type
     */
    public String getFormatType();

    /**
     * Retrieves the data
     * 
     * @return the data as bytes
     */
    public byte[] getData();
}
