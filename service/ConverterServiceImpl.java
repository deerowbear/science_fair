package service;

import model.NumberModel;

public class ConverterServiceImpl implements ConvertService{

    /**
     *
     * @param number
     * @return String representation of the binary number
     */
    private String decimalToBinary(int number) {
        return convert(number, 2, Boolean.FALSE);
    }

    /**
     *
     * @param number
     * @return string representation of the hex value
     */
    private  String decimalToHex(int number) {
        return convert(number, 16, Boolean.TRUE);
    }

    /**
     *
     * @param value
     * @return int
     */
    public int isValidInput(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            System.out.println("Invalid input. Please check your input and choose valid input.");
        }
        return -1;
    }

    /**
     *
     * @param number
     * @param base
     * @param isHex
     * @return a value that is converted based on number, base, isHex
     */
    private String convert(int number, int base, boolean isHex) {
        String value = "";
        while (number > 0) {
            if(isHex) {
                value = toHex(number % base) + value;
            } else {
                value = number % base + value;
            }
            number /= base;
        }
        return value;
    }

    /**
     *
     * @param modulus
     * @return return a char value based on if it is less
     * than 9 otherwise evaluate everything else
     */
    private char toHex(int modulus) {
        if (modulus <= 9) {
            return (char)('0' + modulus);
        } else {
            return (char)('A' + modulus - 10);
        }
    }

    @Override
    public NumberModel setNumberModel(NumberModel numberModel) {
        numberModel.setBinaryValue(this.decimalToBinary(numberModel.getOrginalValue()));
        numberModel.setHexValue(this.decimalToHex(numberModel.getOrginalValue()));
        if (numberModel.getBinaryValue().length() <= 8) {
            numberModel.setEightBits(Boolean.TRUE);
        }
        return numberModel;
    }
}
