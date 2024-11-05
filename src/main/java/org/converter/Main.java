package org.converter;

import org.converter.model.NumberModel;
import org.converter.service.convert.ConverterServiceImpl;
import org.converter.service.excel.ExcelServiceImpl;

import java.util.Scanner;

public class Main {

    private static final ConverterServiceImpl converterService = new ConverterServiceImpl();
    private static final ExcelServiceImpl excelService = new ExcelServiceImpl();


    public static void input () {
        Scanner scanner = new Scanner(System.in);
        NumberModel numberModel = null;
        while (true) {
            System.out.println("Choose a conversion option:");
            System.out.println("1. Decimal to binary");
            System.out.println("2. Decimal to hexadecimal");
            System.out.println("3. Create a list of random numbers and convert to both binary and hexadecimal");
            System.out.println("4. Iterate over 0-256 and convert to both binary and hexadecimal");
            System.out.println("5. Quit");
            Integer option = converterService.isValidInput(scanner.next());
            if (option == null) {
                continue;
            }
            if (option == 5) {
                System.out.println("Existing program...");
                break;
            }
            switch (option) {
                case 1:
                    System.out.print("Enter a decimal number: ");
                    option = converterService.isValidInput(scanner.next());
                    if (option != null) {
                        numberModel = converterService.setNumberModel(new NumberModel(option));
                        System.out.println("Binary equivalent: " + numberModel.getBinaryValue());
                    }
                    break;
                case 2:
                    System.out.print("Enter a decimal number: ");
                    option = converterService.isValidInput(scanner.next());
                    if (option != null) {
                        numberModel = converterService.setNumberModel(new NumberModel(option));
                        System.out.println("Hexadecimal equivalent: " + numberModel.getHexValue());
                    }
                    break;
                case 3:
                    excelService.exportExcel(converterService.getRandomNumberOfModels(1000));
                    break;
                case 4:
                    excelService.exportExcel(converterService.getNumberOfModels(256));
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
            }
        }
        scanner.close();
    }

    public static void main (String[]args){
        input();
    }
}