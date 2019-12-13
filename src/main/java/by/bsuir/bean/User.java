package by.bsuir.bean;

/**
 * The type User.
 */
public class User {

    private String name;
    private String password;

    /**
     * Instantiates a new User.
     *
     * @param name     the name
     * @param password the password
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

}