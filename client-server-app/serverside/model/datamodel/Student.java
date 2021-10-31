package serverside.model.datamodel;


public class Student{
    public static String ignoreValue = "";

    private String fullName;

    private int year;
    private int groupId;

    private int assignmentsAmount;
    private int passedAmount;

    private String programmingLanguage;

    public Student() {}

    public Student(String fullName, int year, int groupId, int assignmentsAmount, int passedAmount, String programmingLanguage) {
        this.fullName = fullName;
        this.year = year;
        this.groupId = groupId;
        this.assignmentsAmount = assignmentsAmount;
        this.passedAmount = passedAmount;
        this.programmingLanguage = programmingLanguage;
    }

    public Object[] toObject() {
        return new Object[]{fullName, year, groupId, assignmentsAmount, passedAmount, programmingLanguage};
    }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public void setProgrammingLanguage(String programmingLanguage) { this.programmingLanguage = programmingLanguage; }

    public void setYear(int year) { this.year = year; }

    public void setGroupId(int groupId) { this.groupId = groupId; }

    public void setAssignmentsAmount(int assignmentsAmount) { this.assignmentsAmount = assignmentsAmount; }

    public void setPassedAmount(int passedAmount) { this.passedAmount = passedAmount; }

    public String getFullName() { return fullName; }

    public String getProgrammingLanguage() { return programmingLanguage; }

    public int getYear() { return year; }

    public int getGroupId() { return groupId; }

    public int getAssignmentsAmount() { return assignmentsAmount; }

    public int getPassedAmount() { return passedAmount; }
}
