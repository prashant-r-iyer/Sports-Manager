public class DoublyLinkedList {

    public LinkedListNode head;
    
    public DoublyLinkedList() {
        head = null;
    }

    public void addNode(TeamMember data) {
        LinkedListNode node = new LinkedListNode(data);
        node.nextNode = head;
        node.previousNode = null;
        if (head != null) {
            head.previousNode = node;
        }
        head = node;
    }

    public void removeNode(LinkedListNode node) {
        if (head == null || node == null) {
            return;
        }

        if (head == node) {
            head = node.nextNode;
        }

        if (node.nextNode != null) {
            node.nextNode.previousNode = node.previousNode;
        }

        if (node.previousNode != null) {
            node.previousNode.nextNode = node.nextNode;
        }

        return;
    }
    
}
