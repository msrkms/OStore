package com.atlassoftwarepark.ostore.Adepter;

public class NotificationSpinnerItem {
    private String spinnerItemName;
    private String spinnerMessage;
    private int spinnerItemImage;

    public NotificationSpinnerItem(String spinnerItemName, String spinnerMessage, int spinnerItemImage) {
        this.spinnerItemName = spinnerItemName;
        this.spinnerMessage = spinnerMessage;
        this.spinnerItemImage = spinnerItemImage;
    }

    public String getSpinnerItemName() {
        return spinnerItemName;
    }

    public void setSpinnerItemName(String spinnerItemName) {
        this.spinnerItemName = spinnerItemName;
    }

    public String getSpinnerMessage() {
        return spinnerMessage;
    }

    public void setSpinnerMessage(String spinnerMessage) {
        this.spinnerMessage = spinnerMessage;
    }

    public int getSpinnerItemImage() {
        return spinnerItemImage;
    }

    public void setSpinnerItemImage(int spinnerItemImage) {
        this.spinnerItemImage = spinnerItemImage;
    }
}
