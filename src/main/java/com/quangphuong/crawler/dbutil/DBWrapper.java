/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quangphuong.crawler.dbutil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author quangphuong
 */
@Component
public class DBWrapper {

    private static final String selectAll = "SELECT * FROM ";
    private static final String groupByClause = " GROUP BY(@)";
    private static final String whereClause = " WHERE ";
    private static final String insertClause = "INSERT INTO ";
    private static final String valuesClause = " VALUES ";
    private static final String updateClause = "UPDATE ";
    private static final String setClause = " SET ";

    private static final String fulltextClause = "SELECT *, MATCH(@)";
    private static final String searchByBoolClause = " AGAINST ('@1' IN BOOLEAN MODE) AS rank FROM @2 ORDER BY rank DESC LIMIT 5";
    private static final String suggestClause = " AGAINST ('@1' IN BOOLEAN MODE) AS rank FROM @2 ORDER BY rank,seen DESC LIMIT 8";
    private static final String searchRangeByBoolClause = " AGAINST ('@1' IN BOOLEAN MODE) AS rank FROM @2 ORDER BY rank DESC LIMIT @L1,@L2";

    private boolean isDisconnect;
    private boolean useDistinct = false;
    private String distinctField = "";
    private String customWhereClause = "";
    private int lowerBound = 0;
    private int upperBound = 0;
    private Connection connection;

    public DBWrapper() {
        this.isDisconnect = true;
    }

    public DBWrapper(boolean isDisconnect) {
        this.isDisconnect = isDisconnect;
        if (!isDisconnect) {
            connection = DBHandler.openConnection();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Object> getEntities(Class<?> entity) {
        String sql = selectAll.concat(entity.getSimpleName());
        Connection con = DBHandler.openConnection();
        List<Object> listResult = new ArrayList();
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            Field[] attributes = entity.getDeclaredFields();

            while (resultSet.next()) {
                List<Object> listFields = new ArrayList();
                List<Class<?>> listFieldTypes = new ArrayList();
                for (Field attribute : attributes) {
                    attribute.setAccessible(true);
                    if (!attribute.isAnnotationPresent(Ignore.class)) {
                        Object obj = resultSet.getObject(attribute.getName());
                        listFields.add(obj);
                        listFieldTypes.add(attribute.getType());
                    }
                }
                Object result = entity.getConstructor(
                        (Class<?>[]) listFieldTypes.toArray(new Class[0])).newInstance(listFields.toArray());
                listResult.add(result);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listResult;
    }

    public Object getEntity(Class<?> entity, Object... id) {
        String sql = selectAll.concat(entity.getSimpleName());
        sql = sql.concat(whereClause);
        Connection con = DBHandler.openConnection();
        Object result = null;
        try {
            Statement statement = con.createStatement();
            Field[] attributes = entity.getDeclaredFields();
            int count = 0;
            for (int i = 0; i < attributes.length; i++) {
                Field attribute = attributes[i];
                if (attribute.isAnnotationPresent(Id.class)) {
                    if (i == 0) {
                        sql = sql.concat(attribute.getName() + "=" + "\'" + id[count] + "\'");
                    } else {
                        sql = sql.concat(" AND " + attribute.getName() + "=" + "\'" + id[count] + "\'");
                    }
                    count++;
                }
            }
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                List<Object> listFields = new ArrayList();
                List<Class<?>> listFieldTypes = new ArrayList();
                for (Field attribute : attributes) {
                    if (!attribute.isAnnotationPresent(Ignore.class)){
                        Object obj = resultSet.getObject(attribute.getName());
                        listFields.add(obj);
                        listFieldTypes.add(obj.getClass());
                    }
                }
                result = entity.getConstructor(
                        (Class<?>[]) listFieldTypes.toArray(new Class[0])).newInstance(listFields.toArray());
            }
        } catch (Exception ex) {
            Logger.getLogger(DBWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public Object getEntityByCondition(Object entity) {
        String sql = selectAll.concat(entity.getClass().getSimpleName());
        sql = sql.concat(whereClause);
        Connection con = null;
        if (this.isDisconnect) {
            con = DBHandler.openConnection();
        }
        Object result = null;
        try {
            Statement statement;
            if (this.isDisconnect) {
                statement = con.createStatement();
            } else {
                statement = connection.createStatement();
            }
            Field[] attributes = entity.getClass().getDeclaredFields();
            int count = 0;
            for (Field attribute : attributes) {
                attribute.setAccessible(true);
                if (attribute.get(entity) != null) {
                    String value = attribute.get(entity).toString();

                    if (count == 0) {
                        sql = sql.concat(attribute.getName() + "=" + "\'" + value + "\'");
                    } else {
                        sql = sql.concat(" AND " + attribute.getName() + "=" + "\'" + value + "\'");
                    }
                    count++;
                }
            }
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                List<Object> listFields = new ArrayList();
                List<Class<?>> listFieldTypes = new ArrayList();
                for (Field attribute : attributes) {
                    if(!attribute.isAnnotationPresent(Ignore.class)) {
                        Object obj = resultSet.getObject(attribute.getName());
                        listFields.add(obj);
                        listFieldTypes.add(attribute.getType());
                    }
                }
                result = entity.getClass().getConstructor(
                        (Class<?>[]) listFieldTypes.toArray(new Class[0])).newInstance(listFields.toArray());
            }
        } catch (Exception ex) {
            Logger.getLogger(DBWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (this.isDisconnect && con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public List<Object> getEntitiesByCondition(Object entity) {
        String sql = selectAll.concat(entity.getClass().getSimpleName());
        if (!"".equals(customWhereClause)) {
            sql = sql.concat(customWhereClause);
        } else {
            sql = sql.concat(whereClause);
        }
        Connection con = null;
        if (this.isDisconnect) {
            con = DBHandler.openConnection();
        }
        List<Object> result = new ArrayList<Object>();
        try {
            Statement statement;
            if (this.isDisconnect) {
                statement = con.createStatement();
            } else {
                statement = connection.createStatement();
            }
            Field[] attributes = entity.getClass().getDeclaredFields();
            if ("".equals(customWhereClause)) {
//                Field[] attributes = entity.getClass().getDeclaredFields();
                int count = 0;
                for (Field attribute : attributes) {
                    attribute.setAccessible(true);
                    if (!attribute.isAnnotationPresent(Ignore.class) 
                            && !attribute.isAnnotationPresent(AutoIncrement.class) && attribute.get(entity) != null) {
                        String value = attribute.get(entity).toString();

                        if (count == 0) {
                            sql = sql.concat(attribute.getName() + "=" + "\'" + value + "\'");
                        } else {
                            sql = sql.concat(" AND " + attribute.getName() + "=" + "\'" + value + "\'");
                        }
                        count++;
                    }
                }
            }
            if (useDistinct) {
                sql = sql.concat(groupByClause.replace("@", distinctField));
            }
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                List<Object> listFields = new ArrayList();
                List<Class<?>> listFieldTypes = new ArrayList();
                for (Field attribute : attributes) {
                    attribute.setAccessible(true);
                    if(!attribute.isAnnotationPresent(Ignore.class)){
                        Object obj = resultSet.getObject(attribute.getName());
                        listFields.add(obj);
                        listFieldTypes.add(attribute.getType());
                    }
                }
                Object obj = entity.getClass().getConstructor(
                        (Class<?>[]) listFieldTypes.toArray(new Class[0])).newInstance(listFields.toArray());
                result.add(obj);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (this.isDisconnect && con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public List<Object> getEntitiesByCondition(Object entity, boolean useDistinct, String distinctField) {
        this.useDistinct = useDistinct;
        this.distinctField = distinctField;
        try {
            return getEntitiesByCondition(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.useDistinct = false;
            this.distinctField = "";
        }
        return null;
    }

    public List<Object> getEntitiesByCustom(Object entity, String whereClause) {
        this.customWhereClause = whereClause;
        try {
            return getEntitiesByCondition(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.customWhereClause = "";
        }
        return null;
    }

    public List<Object> getEntitiesByCustom(Object entity, String whereClause, boolean useDistinct, String distinctField) {
        this.useDistinct = useDistinct;
        this.distinctField = distinctField;
        this.customWhereClause = whereClause;
        try {
            return getEntitiesByCondition(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.useDistinct = false;
            this.distinctField = "";
            this.customWhereClause = "";
        }
        return null;
    }

    public boolean insertEntity(Object entity) {
        String sql = insertClause.concat(entity.getClass().getSimpleName());
        Connection con = null;
        if (this.isDisconnect) {
            con = DBHandler.openConnection();
        }
        try {
            Statement statement;
            if (this.isDisconnect) {
                statement = con.createStatement();
            } else {
                statement = connection.createStatement();
            }
            String column = "(";
            String values = "(";
            Field[] attributes = entity.getClass().getDeclaredFields();
            int count = 0;
            for (Field attribute : attributes) {
                if (!attribute.isAnnotationPresent(AutoIncrement.class) && !attribute.isAnnotationPresent(Ignore.class)) {
                    attribute.setAccessible(true);
                    String value = "";
                    if (attribute.get(entity) != null) {
                        value = attribute.get(entity).toString();
                    }
                    if (count == 0) {
                        column = column.concat(attribute.getName());

                        values = values.concat("\'" + value + "\'");
                    } else {
                        column = column.concat("," + attribute.getName());
                        values = values.concat("," + "\'" + value + "\'");
                    }
                    count++;
                }
            }
            column = column.concat(")");
            values = values.concat(")");
            sql = sql.concat(column + valuesClause + values);
            System.out.println(sql);
            int result = statement.executeUpdate(sql);
            if (result > 0) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(DBWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (this.isDisconnect && con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void insertEntities(List<Object> entities) {
        Connection con = DBHandler.openConnection();
        try {
            for (Object entity : entities) {
                insertEntity(entity);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBWrapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean updateEntity(Object entity) {
        String sql = updateClause.concat(entity.getClass().getSimpleName());
        Connection con = null;
        if (this.isDisconnect) {
            con = DBHandler.openConnection();
        }
        try {
            Statement statement;
            if (this.isDisconnect) {
                statement = con.createStatement();
            } else {
                statement = connection.createStatement();
            }
            String set = setClause;
            String where = whereClause;
            Field[] attributes = entity.getClass().getDeclaredFields();
            int count = 0;
            int idCount = 0;
            for (Field attribute : attributes) {
                attribute.setAccessible(true);
                if (attribute.get(entity) != null && !attribute.isAnnotationPresent(Ignore.class)/* && !attribute.isAnnotationPresent(AutoIncrement.class) */) {
                    String value = attribute.get(entity).toString();
                    if (attribute.isAnnotationPresent(Id.class) /*|| attribute.isAnnotationPresent(Mark.class) */) {
                        if (idCount == 0) {
                            where = where.concat(
                                    attribute.getName() + "=" + "\'" + value + "\'");
                        } else {
                            where = where.concat(" AND "
                                    + attribute.getName() + "=" + "\'" + value + "\'");
                        }
                        idCount++;
                    } else {
                        if (count == 0) {
                            set = set.concat(attribute.getName() + "=" + "\'" + value + "\'");
                        } else {
                            set = set.concat("," + attribute.getName() + "=" + "\'" + value + "\'");
                        }
                        count++;
                    }
                }
            }

            sql = sql.concat(set + where);
            System.out.println(sql);
            int result = statement.executeUpdate(sql);
            if (result > 0) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(DBWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (this.isDisconnect && con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Object> searchFullText(Object entity, String searchVal) {
        String sql = "";
        Connection con = null;
        if (this.isDisconnect) {
            con = DBHandler.openConnection();
        }
        List<Object> result = new ArrayList<Object>();
        try {
            Statement statement;
            if (this.isDisconnect) {
                statement = con.createStatement();
            } else {
                statement = connection.createStatement();
            }
            Field[] attributes = entity.getClass().getDeclaredFields();

            int count = 0;
            String fullTextFields = "";
            String searchClause = "";
            for (Field attribute : attributes) {
                attribute.setAccessible(true);
                if (!attribute.isAnnotationPresent(AutoIncrement.class) && attribute.isAnnotationPresent(FullTextIndex.class)) {
                    if (count == 0) {
                        fullTextFields = fullTextFields.concat(attribute.getName());
                    } else {
                        fullTextFields = fullTextFields.concat("," + attribute.getName());
                    }
                    count++;
                }
            }
            sql = fulltextClause.replace("@", fullTextFields);
            if (lowerBound != 0 || upperBound != 0) {
                searchClause = searchRangeByBoolClause.replace("@1", searchVal.replace("*", "").replace(" ", "* ").concat("*"));
                searchClause = searchClause.replace("@L1", String.valueOf(lowerBound)).replace("@L2", String.valueOf(upperBound));
            } else {
                searchClause = searchByBoolClause.replace("@1", searchVal.replace("*", "").replace(" ", "* ").concat("*"));
            }
            searchClause = searchClause.replace("@2", entity.getClass().getSimpleName());
            sql = sql.concat(searchClause);
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                List<Object> listFields = new ArrayList();
                List<Class<?>> listFieldTypes = new ArrayList();
                for (Field attribute : attributes) {
                    Object obj = resultSet.getObject(attribute.getName());
                    listFields.add(obj);
                    listFieldTypes.add(attribute.getType());
                }
                Object obj = entity.getClass().getConstructor(
                        (Class<?>[]) listFieldTypes.toArray(new Class[0])).newInstance(listFields.toArray());
                result.add(obj);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (this.isDisconnect && con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public List<Object> searchFullTextRange(Object entity, String searchVal, int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        return searchFullText(entity, searchVal);
    }
    
    public List<Object> suggest(Object entity, String searchVal) {
        String sql = "";
        Connection con = null;
        if (this.isDisconnect) {
            con = DBHandler.openConnection();
        }
        List<Object> result = new ArrayList<Object>();
        try {
            Statement statement;
            if (this.isDisconnect) {
                statement = con.createStatement();
            } else {
                statement = connection.createStatement();
            }
            Field[] attributes = entity.getClass().getDeclaredFields();

            int count = 0;
            String fullTextFields = "";
            String searchClause = "";
            for (Field attribute : attributes) {
                attribute.setAccessible(true);
                if (!attribute.isAnnotationPresent(AutoIncrement.class) && attribute.isAnnotationPresent(FullTextIndex.class)) {
                    if (count == 0) {
                        fullTextFields = fullTextFields.concat(attribute.getName());
                    } else {
                        fullTextFields = fullTextFields.concat("," + attribute.getName());
                    }
                    count++;
                }
            }
            sql = fulltextClause.replace("@", fullTextFields);
            if (lowerBound != 0 || upperBound != 0) {
                searchClause = searchRangeByBoolClause.replace("@1", searchVal.replace("*", "").replace(" ", "* ").concat("*"));
                searchClause = searchClause.replace("@L1", String.valueOf(lowerBound)).replace("@L2", String.valueOf(upperBound));
            } else {
                searchClause = suggestClause.replace("@1", searchVal.replace("*", "").replace(" ", "* ").concat("*"));
            }
            searchClause = searchClause.replace("@2", entity.getClass().getSimpleName());
            sql = sql.concat(searchClause);
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                List<Object> listFields = new ArrayList();
                List<Class<?>> listFieldTypes = new ArrayList();
                for (Field attribute : attributes) {
                    Object obj = resultSet.getObject(attribute.getName());
                    listFields.add(obj);
                    listFieldTypes.add(attribute.getType());
                }
                Object obj = entity.getClass().getConstructor(
                        (Class<?>[]) listFieldTypes.toArray(new Class[0])).newInstance(listFields.toArray());
                result.add(obj);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (this.isDisconnect && con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
