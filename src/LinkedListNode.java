public class LinkedListNode {

    private TeamMember data;
    public LinkedListNode previousNode;
    public LinkedListNode nextNode;
    
    public LinkedListNode(TeamMember data) {
        this.data = data;
    }

    public TeamMember getData() {
        return data;
    }
    
}
