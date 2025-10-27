package main.entities;

public class Water {
    private double salinity;
    private double pH;
    private double purity;
    private int turbidity;
    private double contaminantIndex;
    private boolean isFrozen;

    public void setWater(double salinity, double pH, double purity, int turbidity, double contaminantIndex, boolean isFrozen) {
        this.salinity = salinity;
        this.pH = pH;
        this.purity = purity;
        this.turbidity = turbidity;
        this.contaminantIndex = contaminantIndex;
        this.isFrozen = isFrozen;
    }

    public double waterQuality() {
        double purity_score = this.purity / 100;
        double pH_score = 1 - Math.abs(this.pH - 7.5) / 7.5;
        double salinity_score = 1 - (salinity / 350);
        double turbidity_score = 1 - (turbidity / 150);
        double contaminant_score = 1 - (contaminantIndex / 100);
        double frozen_score = isFrozen ? 0 : 1;

        return (0.3 * purity_score + 0.2 * pH_score + 0.15 * salinity_score +
                0.1 * turbidity_score + 0.15 *  contaminant_score + 0.2 * frozen_score) * 100;
    }
}
