package by.bsuir.daoInterfaces;

import by.bsuir.bean.Table;

import java.util.ArrayList;

/**
 * The interface Table dao.
 */
public interface TableDao {
    /**
     * Gets tables.
     *
     * @return the tables
     */
    ArrayList<Table> getTables();

    /**
     * Gets table.
     *
     * @param id the id
     * @return the table
     */
    Table getTable(int id);
}
