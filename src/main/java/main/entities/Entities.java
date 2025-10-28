package main.entities;

public class Entities {
    public int scanned = 0;

    public double normalizeScore(double score) {
        double normalizeScore = Math.max(0, Math.min(100, score));
        return Math.round(normalizeScore * 100.0) / 100.0;
    }
}
