package fr.fpage.ftlinearregression;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static GradientDescent desc;
    private static Graphics graphics;

    public static void main (String[] args) {
        List<Car> cars = new ArrayList<>();
        String legendeX = "", legendeY = "";
        try (CSVReader csvReader = new CSVReader(new FileReader("data.csv"))) {
            for (String[] line : csvReader) {
                try
                {
                    cars.add(new Car(Float.parseFloat(line[0]), Float.parseFloat(line[1])));
                }
                catch (NumberFormatException e) {
                    legendeX = line[0];
                    legendeY = line[1];
                }
            }
        }
        catch (IOException e) {
            System.out.println("Impossible de trouver le fichier data.csv");
            return;
        }
        desc = new GradientDescent(cars);
        graphics = new Graphics("Representation de la droite", legendeX, legendeY, cars, desc);
        try {
            desc.start();
        } catch (InterruptedException e) {
        }
        try {
            CSVWriter writer = new CSVWriter(new FileWriter("../result.csv"));
            List<String[]> result = new ArrayList<>();
            result.add(new String[]{desc.getTh0().toString(), desc.getTh1().toString()});
            result.add(new String[]{Car.maxKm.toString(), Car.maxPrice.toString()});
            writer.writeAll(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Graphics("Evolution des Theta", "Iteration", "Variation", desc.getVariationPoints(), null);
      //  new Graphics("Representation de la droite", legendeX, legendeY, cars, desc);
    }

    public static int estimatePrice(int theta0, int theta1, int mileage)
    {
        return theta0 + (theta1 * mileage);
    }

    public static void repaintGraphic() {
        graphics.repaint();
    }
}
