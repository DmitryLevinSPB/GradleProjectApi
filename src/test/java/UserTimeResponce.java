public class UserTimeResponce extends TimeTestPojo {

    private String updatedAt;

    public UserTimeResponce() {
    }

    public UserTimeResponce (String name, String job, String updatedAt) {
        super(name, job);
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}

