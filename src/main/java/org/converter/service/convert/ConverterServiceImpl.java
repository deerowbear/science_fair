package org.converter.service.convert;

import org.converter.model.NumberModel;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConverterServiceImpl implements ConverterService {
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
    public Integer isValidInput(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            System.out.println("Invalid input. Please check your input and choose valid input.");
        }
        return null;
    }

    /**
     * Create a list of number models based on range
     * @param range
     * @return
     */
    @Override
    public List<NumberModel> getNumberOfModels(int range) {
        return IntStream.rangeClosed(0, range)  // Creates a stream of numbers from 0 to 'range' inclusive
                .mapToObj(i -> this.setNumberModel(new NumberModel(i)))  // Converts each int to a NumberModel
                .collect(Collectors.toList());  // Collects the results into a List
    }

    @Override
    public List<NumberModel> getRandomNumberOfModels(int range) {
        Set<NumberModel> numberModels = new HashSet<>();
        Random random = new Random();
        while (numberModels.size() != range) {
            numberModels.add(this.setNumberModel(new NumberModel(random.nextInt(10001))));
        }
        return new ArrayList<>(numberModels);
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
        if(number == 0) return "0";
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

    /**
     * Fill in the numberModel object
     * @param numberModel
     * @return
     */
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
