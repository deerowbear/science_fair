import model.NumberModel;
import service.ConverterServiceImpl;

import java.util.Scanner;


class Main {

    private static final ConverterServiceImpl converterService = new ConverterServiceImpl();

    public static void input() {
        Scanner scanner = new Scanner(System.in);
        NumberModel numberModel = null;
        while (true) {
            System.out.println("Choose a conversion option:");
            System.out.println("1. Decimal to binary");
            System.out.println("2. Decimal to hexadecimal");
            System.out.println("3. Create a list of random numbers and convert to both binary and hexadecimal");
            System.out.println("4. Iterate over 1-256 and convert to both binary and hexadecimal");
            System.out.println("5. Quit");
            int option = converterService.isValidInput(scanner.next());

            if (option != -1) {
                if (option == 5) {
                    break;
                }
                switch (option) {
                    case 1:
                        System.out.print("Enter a decimal number: ");
                        option = converterService.isValidInput(scanner.next());
                        if (option != -1) {
                            numberModel = converterService.setNumberModel(new NumberModel(option));
                            System.out.println("Binary equivalent: " + numberModel.getBinaryValue());
                        }
                        break;
                    case 2:
                        System.out.print("Enter a decimal number: ");
                        option = converterService.isValidInput(scanner.next());
                        if (option != -1) {
                            numberModel = converterService.setNumberModel(new NumberModel(scanner.nextInt()));
                            System.out.println("Hexadecimal equivalent: " + numberModel.getHexValue());
                        }
                        break;
                    case 3:
                        converterService.setNumberModel(new NumberModel(scanner.nextInt()));
                        break;
                    case 4:
                        converterService.setNumberModel(new NumberModel(scanner.nextInt()));
                        break;
                    default:
                        System.out.println("Invalid input, please try again.");
                }
            }


        }
        scanner.close();
    }

    public static void main(String[] args) {
        input();
    }

}