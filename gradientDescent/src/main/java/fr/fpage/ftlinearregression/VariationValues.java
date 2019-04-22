package fr.fpage.ftlinearregression;

import java.util.ArrayList;
import java.util.List;

public class VariationValues {

    public static List<VariationValues> values = new ArrayList<VariationValues>();

    private float iteration, variation;

    public VariationValues(float iteration, float variation)
    {
        this.iteration = iteration;
        this.variation = variation;
        values.add(this);
    }

    public float getIteration() {
        return iteration;
    }

    public float getVariation() {
        return variation;
    }
}
