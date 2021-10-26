import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileObject {

    private String fileName;
    private int lastRowIndex;
    private File fileObject;
    private FileInputStream fis;
    private FileOutputStream fos;
    private XSSFWorkbook wb;
    public XSSFSheet sheet;

    public FileObject(String fileName) {
        this.fileName = fileName;
        try {
        this.fileObject = new File(fileName);
        this.fis = new FileInputStream(fileObject);
        this.wb = new XSSFWorkbook(fis);
        this.sheet = wb.getSheetAt(0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.lastRowIndex = sheet.getLastRowNum();
    }

    public void printAll() {
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                System.out.println(cell.getStringCellValue() + "\t\t\t");
            }
            System.out.println();
        }
    }

    public XSSFSheet getSheet() {
        return sheet;
    }

    // index of last row / number of content rows excluding heading
    public int getNumRows() {
        return sheet.getLastRowNum();
    }

    public int getNumCells() {
        return sheet.getRow(0).getLastCellNum();
    }

    public String getValue(int row, int cell) {
        Row rowObject = sheet.getRow(row);
        Cell cellObject = rowObject.getCell(cell);
        return cellObject.getStringCellValue();
    }

    public void addRow(StudentMember member) {
        ArrayList<String> memberDetails = new ArrayList<>();
        memberDetails.add(member.getName());
        memberDetails.add(Integer.toString(member.getAge()));
        memberDetails.add(member.getGender());
        memberDetails.add(Integer.toString(member.getDivision()));
        memberDetails.add(member.getPassword());
        addRow(memberDetails);
    }

    public void addRow(Event event) {
        ArrayList<String> eventDetails = new ArrayList<>();
        eventDetails.add(event.getName());
        eventDetails.add(event.getDate().toString());
        eventDetails.add(event.getTime().toString());
        eventDetails.add(Integer.toString(event.getDuration()));
        eventDetails.add(event.getLocation());
        eventDetails.add(Integer.toString(event.getDivision()));
        eventDetails.add(event.getGender());
        addRow(eventDetails);
    }

    public void addRow(ArrayList<String> rowList) {
        Row newRow = sheet.createRow(lastRowIndex + 1);
        addCellItems(newRow, rowList, 0, rowList.size());
    }

    private void addCellItems(Row row, ArrayList<String> rowList, int count, int rowLength) {
        Cell newCell = row.createCell(count);
        newCell.setCellValue(rowList.get(0));
        rowList.remove(0);
        if (count <= rowLength - 2) {
            addCellItems(row, rowList, count + 1, rowLength);
        }
    }

    public void removeRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < lastRowIndex) {
            sheet.shiftRows(rowIndex + 1, lastRowIndex, -1);
        }
        if (rowIndex == lastRowIndex) {
            Row removingRow = sheet.getRow(rowIndex);
            if (removingRow != null) {
                sheet.removeRow(removingRow);
            }
        }
    }

    public void removeRow(String key, int keyCell) {
        for (int rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {
            Row currentRow = sheet.getRow(rowIndex);
            if (currentRow.getCell(keyCell).getStringCellValue().equals(key)) {
                removeRow(rowIndex);
                break;
            }
        }
    }

    public void removeRow(StudentMember member) {
        for (int rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {
            Row currentRow = sheet.getRow(rowIndex);
            if (currentRow.getCell(0).getStringCellValue().equals(member.getName()) && currentRow.getCell(1).getStringCellValue().equals(Integer.toString(member.getAge())) && currentRow.getCell(2).getStringCellValue().equals(member.getGender()) && currentRow.getCell(3).getStringCellValue().equals(Integer.toString(member.getDivision())) && currentRow.getCell(4).getStringCellValue().equals(member.getPassword())) {
                removeRow(rowIndex);
                break;
            }
        }
    }

    public void removeRow(Event event) {
        for (int rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {
            Row currentRow = sheet.getRow(rowIndex);
            if (currentRow.getCell(0).getStringCellValue().equals(event.getName()) && currentRow.getCell(1).getStringCellValue().equals(event.getDate().toString()) && currentRow.getCell(2).getStringCellValue().equals(event.getTime().toString()) && currentRow.getCell(3).getStringCellValue().equals(Integer.toString(event.getDuration())) && currentRow.getCell(4).getStringCellValue().equals(event.getLocation())) {
                removeRow(rowIndex);
                break;
            }
        }
    }

    public String[][] get2DArray(int columns) {
        String[][] memberDetails = new String[lastRowIndex + 1][columns];
        for (int rowIndex = 0; rowIndex <= lastRowIndex; rowIndex++) {
            Row currentRow = sheet.getRow(rowIndex);
            for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                memberDetails[rowIndex][columnIndex] = currentRow.getCell(columnIndex).getStringCellValue();
                
            }
        }
        return memberDetails;
    }

    public ArrayList<String> findDetails(String name, String password) {
        ArrayList<String> details = new ArrayList<String>();
        for (int rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {
            Row currentRow = sheet.getRow(rowIndex);
            for (int columnIndex = 0; columnIndex < 5; columnIndex++) {
                if (currentRow.getCell(0).getStringCellValue().equals(name) && currentRow.getCell(4).getStringCellValue().equals(password)) {
                    details.add(currentRow.getCell(0).getStringCellValue());
                    details.add(currentRow.getCell(1).getStringCellValue());
                    details.add(currentRow.getCell(2).getStringCellValue());
                    details.add(currentRow.getCell(3).getStringCellValue());
                    details.add(currentRow.getCell(4).getStringCellValue());
                    break;
                }
                
            }
        }
        return details;
    }

    public void close() {
        try {
            fis.close();
            fos = new FileOutputStream(fileName);
            wb.write(fos);
            wb.close();
            fos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
