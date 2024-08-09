package healers.data.solutions;

public class item {

    String Title;
    String Time;

    public item(String title, String time) {
        Title = title;
        Time = time;
    }



    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
