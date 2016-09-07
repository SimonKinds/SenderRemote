package io.kindstrom.senderremote.domain.model;

public class Limits {
    private final Integer low, high;

    public static Limits create(Integer low, Integer high) {
        if((low == null && high == null) ||
                (low != null && high != null && low >= high)) {
            return null;
        }

        return new Limits(low, high);
    }

    private Limits(Integer low, Integer high) {
        this.low = low;
        this.high = high;
    }

    public String inCommandFormat() {
        StringBuilder commandFormat = new StringBuilder();
        if(low != null) {
            if(low < 0) {
                commandFormat.append("-");
            }
            commandFormat.append("L");
            commandFormat.append(Math.abs(low));
        }


        if(high != null) {
            if(low != null) {
                commandFormat.append(" ");
            }
            if(high < 0) {
                commandFormat.append("-");
            }
            commandFormat.append("H");
            commandFormat.append(Math.abs(high));
        }

        return commandFormat.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Limits limits = (Limits) o;

        return low != null ? low.equals(limits.low) : limits.low == null && (high != null ? high.equals(limits.high) : limits.high == null);

    }

    @Override
    public int hashCode() {
        int result = low != null ? low.hashCode() : 0;
        result = 31 * result + (high != null ? high.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Limits{" +
                "low=" + low +
                ", high=" + high +
                '}';
    }
}
