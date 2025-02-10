package by.zemich.bargainms.domain.valueobject;

import lombok.Getter;

@Getter
public enum TimeInForce {
    GTC("Good Till Cancel"),
    IOC("Immediate or Cancel"),
    FOK("Fill or Kill"),
    GTX("Good Till Crossing (Post Only)");

    private String description;

    TimeInForce(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TimeInForce{" +
                "description='" + description + '\'' +
                '}';
    }
}
