package healers.data.solutions;

import java.util.List;

public class Patient {
    private String age;
    private String bloodGroup;
    private String gender;
    private String height;
    private String location;
    private String name;
    private String weight;
    private List<HistoryEntry> history;
    private List<ReportEntry> reports;

    public Patient() { }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public List<HistoryEntry> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryEntry> history) {
        this.history = history;
    }

    public List<ReportEntry> getReports() {
        return reports;
    }

    public void setReports(List<ReportEntry> reports) {
        this.reports = reports;
    }
}
