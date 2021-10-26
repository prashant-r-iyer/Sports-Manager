public class Date {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        String dayString = Integer.toString(day);
        if (day <= 9) {
            dayString = "0" + dayString;
        }
        String monthString = Integer.toString(month);
        if (month <= 9) {
            monthString = "0" + monthString;
        }
        String yearString = Integer.toString(year);
        if (year <= 9) {
            yearString = "0" + yearString;
        }
        return dayString + "/" + monthString + "/" + yearString;
    }
    
}
