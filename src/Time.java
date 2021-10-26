public class Time {

    private int hour;
    private int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String toString() {
        String hourString = Integer.toString(hour);
        if (hour <= 9) {
            hourString = "0" + hourString;
        }
        String minuteString = Integer.toString(minute);
        if (minute <= 9) {
            minuteString = "0" + minuteString;
        }
        return hourString + ":" + minuteString;
    }
    
}
