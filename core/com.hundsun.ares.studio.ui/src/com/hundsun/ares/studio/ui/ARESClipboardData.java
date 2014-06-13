package com.hundsun.ares.studio.ui;

public final class ARESClipboardData implements IARESClipboardData {
    /* Attribute for the format type */
    final private String formatType;

    /* Attribute for the data */
    final private byte[] data;


    public ARESClipboardData(String formatType, byte[] data) {
        super();

        assert null != formatType;
        assert null != data;
        assert (data.length > 0);

        this.formatType = formatType;
        this.data = data;
    }

    public String getFormatType() {
        return formatType;
    }

    public byte[] getData() {
        return data;
    }
}