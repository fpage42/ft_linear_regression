package fr.fpage.ftlinearregression;

public class Car extends GraphicPoint {

    static public Float maxPrice = 0f, maxKm = 0f;

    public Car(float km, float price) {
        super(km, price);
        if (maxKm < this.value1)
            maxKm = this.value1;
        if (maxPrice < this.value2)
            maxPrice = this.value2;
    }

    public float getNormalizeKm()
    {
        return this.value1 / maxKm;
    }

    public float getNormalizePrice()
    {
        return this.value2 / maxPrice;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + value2 +
                ", km=" + value1 +
                '}';
    }
}
