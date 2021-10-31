package serverside.model.datamodel;

import serverside.model.datamodel.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentPageModel {
    private int rowsOnPage;
    private ArrayList<Student> tableArray;

    public StudentPageModel(int rowsOnPage) {
        tableArray = new ArrayList<Student>(0);
        this.rowsOnPage = rowsOnPage;
    }

    public int getRowsOnPage() { return rowsOnPage; }

    public void setRowsOnPage(int rowsOnPage) { this.rowsOnPage = rowsOnPage; }

    public List<Object> getPage(int page) {
        if (tableArray.size() / rowsOnPage + 1 < page) return null;
        if (page <= 0) return null;

        int startIdx = (page-1)*rowsOnPage;
        int endIdx = Math.min(page*rowsOnPage, tableArray.size());

        return tableArray.subList(startIdx, endIdx).stream()
                                                   .map(Student::toObject)
                                                   .collect(Collectors.toList());
    }

    public ArrayList<Student> getTableArray() { return tableArray; }

    public void setTableArray(ArrayList<Student> tableArray) { this.tableArray = tableArray; }

    public int size() { return tableArray.size(); }
}
