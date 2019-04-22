package fr.fpage.ftlinearregression.linearfunction;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class LinearFunction {

    public static void main (String[] args) {
        int mileage = 0;
        Float theta0 = 0f, theta1 = 0f, maxKm = 0f, maxPrice = 0f;
        try {
            mileage = Integer.parseInt(args[0]);
        }
        catch (ArrayIndexOutOfBoundsException | NumberFormatException e)
        {
            System.out.println("./ft_linear_regression [value]");
            return;
        }
        try (CSVReader csvReader = new CSVReader(new FileReader("../result.csv"))) {
            String[] csvInfo = csvReader.readNext();
            theta0 = Float.parseFloat(csvInfo[0]);
            theta1 = Float.parseFloat(csvInfo[1]);
            csvInfo = csvReader.readNext();
            maxKm = Float.parseFloat(csvInfo[0]);
            maxPrice = Float.parseFloat(csvInfo[1]);
        }
        catch (IOException | NumberFormatException e) {}
        Float calcPrice = (theta0 + (theta1 * (mileage/maxKm)));
        System.out.println("Price: " + (calcPrice * maxPrice));
    }
}
