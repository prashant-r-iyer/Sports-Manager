public class Event {

    private String name;
    private Date date;
    private Time time;
    private int duration;
    private String location;
    private int division;
    private String gender;

    public Event(String name, String date, String time, int duration, String location, int division, String gender) {
        this.name = name;
        this.date = new Date(Integer.parseInt(date.substring(0, 2)), Integer.parseInt((date.substring(3, 5))), Integer.parseInt(date.substring(6, 8)));
        this.time = new Time(Integer.parseInt(time.substring(0, 2)), Integer.parseInt(time.substring(3, 5)));
        this.duration = duration;
        this.location = location;
        this.division = division;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    public String getLocation() {
        return location;
    }

    public int getDivision() {
        return division;
    }

    public String getGender() {
        return gender;
    }

}
