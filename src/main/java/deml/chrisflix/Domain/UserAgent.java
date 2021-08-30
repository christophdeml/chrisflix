package deml.chrisflix.Domain;

public class UserAgent {
    private String id;
    private String username;
    private String useragent;
    private int executionCount;

    public static UserAgent create(final String id, final String username, final String useragent) {
        return new UserAgent(id, username, useragent, 0);
    }

    private UserAgent(final String id, final String username, final String useragent, int executionCount) {
        this.id = id;
        this.username = username;
        this.useragent = useragent;
        this.executionCount = executionCount;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(final String useragent) {
        this.useragent = useragent;
    }

    public int getExecutionCount() {
        return executionCount;
    }

    public void incrementExecutionCount() {
        executionCount++;
    }

    public void resetExecutionCount() {
        executionCount = 0;
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", useragent='" + useragent + '\'' +
                ", executionCount=" + executionCount +
                '}';
    }
}
