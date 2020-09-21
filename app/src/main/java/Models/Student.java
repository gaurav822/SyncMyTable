package Models;

public class Student {

    private String name,email,id,branch,year;

    boolean isVerified;

    public Student(String name, String email, String id, String branch, String year, boolean isVerified) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.branch = branch;
        this.year = year;
        this.isVerified=isVerified;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
