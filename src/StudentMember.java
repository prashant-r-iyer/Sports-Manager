public class StudentMember extends TeamMember {

    private int age;
    private String gender;
    private int division;

    public StudentMember(String name, int age, String gender, int division, String password) {
        super(name, password);
        this.age = age;
        this.gender = gender;
        this.division = division;
    }

    public StudentMember(String name, String password) {
        super(name, password);
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public int getDivision() {
        return division;
    }

    public boolean coachAccess() {
        return false;
    }

}
