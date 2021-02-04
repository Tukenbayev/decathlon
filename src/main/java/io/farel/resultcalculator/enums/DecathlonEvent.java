package io.farel.resultcalculator.enums;

public enum DecathlonEvent {

    _100M(25.4347, 18.0, 1.81, EventType.TRACK),
    LONG_JUMP(0.14354, 220.0, 1.4, EventType.FIELD),
    SHOT_PUT(51.39, 1.5, 1.05, EventType.FIELD),
    HIGH_JUMP(0.8465, 75.0, 1.42, EventType.FIELD),
    _400M(1.53775, 82.0, 1.81, EventType.TRACK),
    HURDLES_110M(5.74352, 28.5, 1.92, EventType.TRACK),
    DISCUS_THROW(12.91, 4.0, 1.1, EventType.FIELD),
    POLE_VAULT(0.2797, 100.0, 1.35, EventType.FIELD),
    JAVELIN_THROW(10.14, 7.0, 1.08, EventType.FIELD),
    _1500M(0.03768, 480.0, 1.85, EventType.TRACK);

    private final Double A;
    private final Double B;
    private final Double C;
    private final EventType eventType;

    DecathlonEvent(Double A, Double B, Double C, EventType eventType) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.eventType = eventType;
    }

    public Integer calculateScore(Double performance) {
        if (performance != null) {
            if (this.eventType.equals(EventType.TRACK)) {
                return ((Double)(A * Math.pow(B - performance, C))).intValue();
            } else {
                return ((Double)(A * Math.pow(performance - B, C))).intValue();
            }
        } else {
            return 0;
        }
    }

}
