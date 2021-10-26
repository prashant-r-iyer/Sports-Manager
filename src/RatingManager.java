import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;

public class RatingManager {

    public static void updateRatings(String name, String date, String rating, String feedback, int numCells) {
        FileObject ratingFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/ratingFile.xlsx");
        FileObject feedbackFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/feedbackFile.xlsx");

        int targetRatingRow = 0;
        int targetRatingColumn = 0;
        int targetFeedbackRow = 0;
        int targetFeedbackColumn = 0;
        Row firstRatingRow = ratingFileObject.getSheet().getRow(0);
        Row firstFeedbackRow = feedbackFileObject.getSheet().getRow(0);

        for (int row = 1; row <= ratingFileObject.getNumRows(); row++) {
            Row currentRatingRow = ratingFileObject.getSheet().getRow(row);
            Row currentFeedbackRow = feedbackFileObject.getSheet().getRow(row);
            if (currentRatingRow.getCell(0).getStringCellValue().equals(name)) {
                targetRatingRow = row;
            }
            if (currentFeedbackRow.getCell(0).getStringCellValue().equals(name)) {
                targetFeedbackRow = row;
            }
        }
        for (int column = 1; column <= numCells; column++) {
            if (firstRatingRow.getCell(column).getStringCellValue().equals(date)) {
                targetRatingColumn = column;
            }
            if (firstFeedbackRow.getCell(column).getStringCellValue().equals(date)) {
                targetFeedbackColumn = column;
            }
        }
        
        ratingFileObject.getSheet().getRow(targetRatingRow).getCell(targetRatingColumn).setCellValue(rating);
        feedbackFileObject.getSheet().getRow(targetFeedbackRow).getCell(targetFeedbackColumn).setCellValue(feedback);

        ratingFileObject.close();
        feedbackFileObject.close();
    }

    public static String[] getNameArray(String dateInput) {
        FileObject memberFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/memberFile.xlsx");
        int numMembers = memberFileObject.getNumRows();
        FileObject eventFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/eventFile.xlsx");
        int numEvents = eventFileObject.getNumRows();

        ArrayList<String> namesList = new ArrayList<String>();
        for (int eventRow = 1; eventRow <= numEvents; eventRow++) {
            Row currentEventRow = eventFileObject.getSheet().getRow(eventRow);
            if (currentEventRow.getCell(1).getStringCellValue().equals(dateInput)) {
                for (int memberRow = 1; memberRow <= numMembers; memberRow++) {
                    Row currentMemberRow = memberFileObject.getSheet().getRow(memberRow);
                    if (currentMemberRow.getCell(3).getStringCellValue().equals(currentEventRow.getCell(5).getStringCellValue()) && currentMemberRow.getCell(2).getStringCellValue().equals(currentEventRow.getCell(6).getStringCellValue())) {
                        namesList.add(currentMemberRow.getCell(0).getStringCellValue());
                    }
                }
            }
        }
        memberFileObject.close();
        eventFileObject.close();

        String[] nameArray = new String[namesList.size()];
        for (int nameCount = 0; nameCount < namesList.size(); nameCount++) {
            nameArray[nameCount] = namesList.get(nameCount);
        }

        return nameArray;
    }
    
    public static void updateTableLength(int numMembers, int numEvents) {
        FileObject ratingFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/ratingFile.xlsx");
        FileObject feedbackFileObject = new FileObject("C:/Users/prash/VSCodeProjects/Sports-Manager/src/feedbackFile.xlsx");
        for (int row = 0; row <= numMembers; row++) {
            int ratingCount = 1;
            int feedbackCount = 1;
            while (ratingFileObject.getSheet().getRow(row).getCell(numEvents + ratingCount) != null) {
                ratingFileObject.getSheet().getRow(row).removeCell(ratingFileObject.getSheet().getRow(row).getCell(numEvents + ratingCount));
                ratingCount++;
            }
            while (feedbackFileObject.getSheet().getRow(row).getCell(numEvents + feedbackCount) != null) {
                feedbackFileObject.getSheet().getRow(row).removeCell(feedbackFileObject.getSheet().getRow(row).getCell(numEvents + feedbackCount));
                feedbackCount++;
            }
        }

        for (int row = 1; row <= ratingFileObject.getNumRows(); row++) {
            if (row > numMembers) {
                ratingFileObject.removeRow(row);
                feedbackFileObject.removeRow(row);
            }
        }
        ratingFileObject.close();
        feedbackFileObject.close();
    }
}
