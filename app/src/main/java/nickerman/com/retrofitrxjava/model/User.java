package nickerman.com.retrofitrxjava.model;

public class User {
    public int userId ;
    public int id ;
    public String title ;
    public String body ;

    public User(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public User() {
    }
}
