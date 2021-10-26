public class Coach extends TeamMember {

    public Coach(String name, String password) {
        super(name, password);
    }

    public static boolean coachAccess() {
        return true;
    }
    
}
