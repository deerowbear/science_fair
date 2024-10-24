package model;

public class NumberModel {

    Integer orginalValue;
    String binaryValue;
    String hexValue;
    boolean isEightBits = Boolean.FALSE;

    public Integer getOrginalValue() {
        return orginalValue;
    }

    public NumberModel(Integer orginalValue) {
        this.orginalValue = orginalValue;
    }

    public String getBinaryValue() {
        return binaryValue;
    }

    public void setBinaryValue(String binaryValue) {
        this.binaryValue = binaryValue;
    }

    public String getHexValue() {
        return hexValue;
    }

    public void setHexValue(String hexValue) {
        this.hexValue = hexValue;
    }

    public boolean isEightBits() {
        return isEightBits;
    }

    public void setEightBits(boolean eightBits) {
        isEightBits = eightBits;
    }

}
