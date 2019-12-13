package by.bsuir.bean;

import java.util.Objects;

/**
 * The type Client.
 */
public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private double money;
    private Table table;

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets money.
     *
     * @return the money
     */
    public double getMoney() {
        return money;
    }

    /**
     * Sets money.
     *
     * @param money the money
     */
    public void setMoney(double money) {
        if (money < 0) {
            return;
        }

        this.money = money;
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * Sets table.
     *
     * @param table the table
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * Instantiates a new Client.
     */
    public Client() {
    }

    /**
     * Instantiates a new Client.
     *
     * @param id the id
     */
    public Client(int id) {
        this.id = id;
    }

    /**
     * Instantiates a new Client.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param money     the money
     */
    public Client(String firstName, String lastName, double money) {
        this.firstName = firstName;
        this.lastName = lastName;
        setMoney(money);
    }

    /**
     * Instantiates a new Client.
     *
     * @param id        the id
     * @param firstName the first name
     * @param lastName  the last name
     * @param money     the money
     */
    public Client(int id, String firstName, String lastName, double money) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        setMoney(money);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        if (!super.equals(obj)) {
            return false;
        }

        Client client = (Client) obj;
        return id == client.id &&
                money == client.money &&
                Objects.equals(table, client.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, money, table);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", money=" + money +
                ", table=" + table.toString() +
                '}';
    }
}
