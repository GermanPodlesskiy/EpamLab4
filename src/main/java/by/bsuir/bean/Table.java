package by.bsuir.bean;

import java.util.Objects;

/**
 * The type Table.
 */
public class Table {
    private int id;
    private boolean isFree;
    private int number;

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
     * Is free boolean.
     *
     * @return the boolean
     */
    public boolean isFree() {
        return isFree;
    }

    /**
     * Sets free.
     *
     * @param free the free
     */
    public void setFree(boolean free) {
        isFree = free;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param number the number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Instantiates a new Table.
     */
    public Table() {
    }

    /**
     * Instantiates a new Table.
     *
     * @param id the id
     */
    public Table(int id) {
        this.id = id;
    }

    /**
     * Instantiates a new Table.
     *
     * @param isFree the is free
     * @param number the number
     */
    public Table(boolean isFree, int number) {
        this.isFree = isFree;
        this.number = number;
    }

    /**
     * Instantiates a new Table.
     *
     * @param id     the id
     * @param isFree the is free
     * @param number the number
     */
    public Table(int id, boolean isFree, int number) {
        this(id);

        this.isFree = isFree;
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Table table = (Table) obj;

        return isFree == table.isFree &&
                number == table.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isFree, number);
    }

    @Override
    public String toString() {
        return "Table{" +
                "isFree=" + isFree +
                ", number=" + number +
                '}';
    }
}
