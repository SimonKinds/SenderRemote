package io.kindstrom.senderremote.domain.model;

public class Duration {
    private final int hours, minutes, seconds;

    public Duration(Integer hours, Integer minutes, Integer seconds) {
        this.seconds = seconds % 60;
        this.minutes = minutes % 60 + seconds / 60;
        this.hours = hours + (minutes + seconds / 60) / 60;
    }

    public String inCommandFormat() {
        String duration = "";
        if(hours != 0) {
            duration += "T" + hours;
        }
        if(minutes != 0) {
            duration += "M" + minutes;
        }
        if(seconds != 0) {
            duration += "S" + seconds;
        }

        return duration;
    }

    @Override
    public String toString() {
        return "Duration{" +
                "hours=" + hours +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Duration duration = (Duration) o;

        return hours == duration.hours && minutes == duration.minutes && seconds == duration.seconds;

    }

    @Override
    public int hashCode() {
        int result = hours;
        result = 31 * result + minutes;
        result = 31 * result + seconds;
        return result;
    }
}
