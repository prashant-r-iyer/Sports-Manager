import org.apache.poi.ss.usermodel.Row;

public class MemberManager {

    //TODO: figure out how to use sort/search
    //TODO: serial number for linked list

    public static DoublyLinkedList memberList = new DoublyLinkedList();

    public static boolean validateLogin(FileObject memberFile, String name, String password) {
        int rowCount = 1;
        while (rowCount <= memberFile.getNumRows()) {
            Row currentRow = memberFile.getSheet().getRow(rowCount);
            if (currentRow.getCell(0).getStringCellValue().equals(name) && currentRow.getCell(4).getStringCellValue().equals(password)) {
                return true;
            }
            rowCount++;
        }
        return false;
    }

    public static void addMember(FileObject memberFile, StudentMember member) {
        memberFile.addRow(member);
    }

    public static void removeMember(FileObject memberFile, StudentMember member) {
        memberFile.removeRow(member);
    }

    // public static void createList(FileObject memberFile) {
    //     int rowCount = 1;
    //     while (rowCount <= memberFile.getNumRows()) {
    //         Row currentRow = memberFile.getSheet().getRow(rowCount);
    //         StudentMember student = new StudentMember(currentRow.getCell(0).getStringCellValue(), Integer.valueOf(currentRow.getCell(1).getStringCellValue()), currentRow.getCell(2).getStringCellValue(), Integer.valueOf(currentRow.getCell(4).getStringCellValue()));
    //         memberList.addNode(student);
    //         rowCount++;
    //     }
    // }
    
}
