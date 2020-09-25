package Domain;

public class User {
    private int userID;
    private String name;
    private String email;
    private String picture;
    private int age;
    private String homeTown;
    private String password;

    public User(int userID, String name, String email, String picture, int age, String homeTown,String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.age = age;
        this.homeTown = homeTown;
        this.password=password;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }

    public int getAge() {
        return age;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public String getPassword() {
        return password;
    }
}
