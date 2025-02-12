package by.zemich.bargainms.domain.valueobject;

public enum Interval {
    ONE_SECOND("1s"),
    ONE_MINUTE("1m"),
    THREE_MINUTES("3m"),
    FIVE_MINUTES("5m"),
    FIFTEEN_MINUTES("15m"),
    THIRTY_MINUTES("30m"),
    ONE_HOUR("1h"),
    TWO_HOURS("2h"),
    FOUR_HOURS("4h"),
    SIX_HOURS("6h"),
    EIGHT_HOURS("8h"),
    TWELVE_HOURS("12h"),
    ONE_DAY("1d"),
    THREE_DAYS("3d"),
    ONE_WEEK("1w"),
    ONE_MONTH("1M");

    private final String value;

    Interval(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Interval fromValue(String value) {
        for (Interval interval : values()) {
            if (interval.value.equals(value)) {
                return interval;
            }
        }
        throw new IllegalArgumentException("Unknown interval: " + value);
    }
}
