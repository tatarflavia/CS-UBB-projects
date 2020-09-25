package Domain;

public class Asset {
    private int id;
    private int userId;
    private String name;
    private String description;
    private int value;

    public Asset(int id, int userId, String name, String description, int value) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }
}
