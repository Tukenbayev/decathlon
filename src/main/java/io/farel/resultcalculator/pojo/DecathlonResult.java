package io.farel.resultcalculator.pojo;

public class DecathlonResult implements Comparable<DecathlonResult>{

    public String athleteName;

    /**
     *  results from input file
     */
    public Double _100m;
    public Double longJump;
    public Double shotPut;
    public Double highJump;
    public Double _400m;
    public Double hurdles110m;
    public Double discusThrow;
    public Double poleVault;
    public Double javelinThrow;
    public Double _1500m;

    /**
     *  output after calculation
     */
    public Integer totalScore = 0;
    public String place;

    public void addScore(int score) {
        this.totalScore += score;
    }

    @Override
    public String toString() {
        return "DecathlonResult{" +
                "athleteName='" + athleteName + '\'' +
                ", totalScore=" + totalScore +
                ", place='" + place + '\'' +
                '}';
    }

    @Override
    public int compareTo(DecathlonResult o) {
        return Integer.parseInt(place.substring(0, 1)) - Integer.parseInt(o.place.substring(0, 1));
    }
}
