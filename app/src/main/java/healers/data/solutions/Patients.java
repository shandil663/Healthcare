package healers.data.solutions;

public class Patients {

    String hosname,docname,ill,time,name,age;

    public Patients() {
    }

    public Patients(String hosname, String docname, String ill, String time, String name, String age) {
        this.hosname = hosname;
        this.docname = docname;
        this.ill = ill;
        this.time = time;
        this.name = name;
        this.age = age;
    }

    public String getHosname() {
        return hosname;
    }

    public void setHosname(String hosname) {
        this.hosname = hosname;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getIll() {
        return ill;
    }

    public void setIll(String ill) {
        this.ill = ill;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

