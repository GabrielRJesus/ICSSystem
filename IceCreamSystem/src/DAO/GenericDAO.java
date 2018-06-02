package DAO;

import exception.DAOException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author gabri
 */
public interface GenericDAO<T> {
    
    public int insert(T obj,Connection con) throws DAOException;

    public int update(T obj,Connection con) throws DAOException;

    public int delete(T obj,Connection con) throws DAOException;

    public T select(T obj,Connection con) throws DAOException;
}
