package fr.fpage.ftlinearregression;

import java.util.ArrayList;
import java.util.List;

public class GradientDescent {

    public static int ITERATION = 2000000;

    private final List<Car> cars;
    private Float th0 = 0f, th1 = 0f;
    private final List<GraphicPoint> variationPoints = new ArrayList<>();
    private final Float LEARNINGRATE = 0.0001f;

    public GradientDescent(List<Car> cars) {
        this.cars = cars;
    }

    private float[] cost()
    {
        float tmpTh0 = 0f, tmpTh1 = 0f;
        for (Car car : this.cars) {
            tmpTh0 += this.estimatePrice(car.getNormalizeKm()) - car.getNormalizePrice();
            tmpTh1 += (this.estimatePrice(car.getNormalizeKm()) - car.getNormalizePrice()) * car.getNormalizeKm();
        }
        return new float[]{(1f/ ((float) cars.size())) * tmpTh0, (1f/ ((float) cars.size())) * tmpTh1};
    }

    private float estimatePrice(float km)
    {
        return this.th0 + this.th1 * km;
    }

    private void calcNewTheta()
    {
        float[] deriveeThetaPartielles = this.cost();
        this.th0 = this.th0 - LEARNINGRATE * deriveeThetaPartielles[0];
        this.th1 = this.th1 - LEARNINGRATE * deriveeThetaPartielles[1];
    }

    public Float getTh0() {
        return th0;
    }

    public Float getTh1() {
        return th1;
    }

    public List<GraphicPoint> getVariationPoints() {
        return variationPoints;
    }

    public void start() throws InterruptedException {
        float lastTh0 = 0;
        int i = 0;
        while (i < ITERATION)
        {
            if (i % (ITERATION/500) == 0) {
                if (i < ITERATION / 2)
                    Thread.sleep(100);
                else
                    Thread.sleep(30);
                Main.repaintGraphic();
            }
            this.variationPoints.add(new GraphicPoint(i, this.th0-lastTh0));
            lastTh0 = this.th0;
            this.calcNewTheta();
            i++;
        }
        Main.repaintGraphic();
    }
}
