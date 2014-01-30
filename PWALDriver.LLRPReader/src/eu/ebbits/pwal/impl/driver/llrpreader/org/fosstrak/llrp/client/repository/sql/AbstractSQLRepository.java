/*
 *  
 *  Fosstrak LLRP Commander (www.fosstrak.org)
 * 
 *  Copyright (C) 2008 ETH Zurich
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/> 
 *
 */

package eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.client.repository.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.adaptor.AdaptorManagement;
import eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.adaptor.exception.LLRPRuntimeException;
import eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.client.Constants;
import eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.client.LLRPMessageItem;
import eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.client.ROAccessReportsRepository;
import eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.client.Repository;
import eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.client.RepositoryFactory;
import eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.client.repository.sql.roaccess.DerbyROAccessReportsRepository;

/**
 * The {@link AbstractSQLRepository} represents a common super class for all 
 * SQL based Repositories. The class implements the different methods like 
 * <code>put(LLRPMessageItem)</code> or <code>get(String)</code>  in an 
 * abstract manner, using the strategy pattern. We explain the idea below:<br/>
 * Two examples:<br/>
 * <ol>
 * <li>Create the table to store the LLRP messages:<br/>
 * <code>Statement sCreateTable = conn.createStatement();</code><br/>
 * <code>sCreateTable.execute(sqlCreateTable());</code><br/>
 * <code>sCreateTable.close();</code><br/>
 * As you can see, the {@link AbstractSQLRepository} runs on a prepared 
 * statement (in this case a normal SQL statement would do as well), but this 
 * statement is not hard-coded. The create SQL is obtained via the method 
 * <code>sqlCreateTable()</code>. Depending on the implementing data base, 
 * different SQL statements for the create instruction can be used. Example: a 
 * derby implementation {@link DerbyRepository} uses a different create SQL 
 * than a {@link MySQLRepository} (as the data types differ).
 * </li>
 * <li>Clear all the messages received by an adapter:<br/>
 * <code>PreparedStatement psRemove = conn.prepareStatement(</code><br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<code>sqlRemoveAllAdapterMessages());</code><br/>
 * <code>psRemove.setString(1, adapter);</code><br/>
 * <code>psRemove.executeUpdate();</code><br/>
 * <code>psRemove.close();</code><br/>
 * Here the {@link AbstractSQLRepository} executes a prepared statement. The 
 * name of the adapter to be cleared, is injected from the code in the 
 * {@link AbstractSQLRepository}. Again, the actual query is delivered from the 
 * implementing strategy (example: {@link MySQLRepository}).
 * </li>
 * </ol>
 * <h3>NOTICE:</h3>
 * By default, the {@link AbstractSQLRepository} uses the SQL commands tailored 
 * to the Derby database (as this is the default internal database). So, if 
 * you do not override certain SQL "queries-getter", please be aware that this 
 * might cause trouble with a database differing from Derby.
 * @author sawielan
 *
 */
public abstract class AbstractSQLRepository implements Repository {
    
    
    /** column index of the ID.*/
    public static final int SELECTOR_ID = 1;
    
    /** column index of the message type. */
    public static final int SELECTOR_MESSAGE_TYPE = 2;
    
    /** column index of the reader name. */
    public static final int SELECTOR_READER = 3;
    
    /** column index of the adapter name. */
    public static final int SELECTOR_ADAPTOR = 4;
    
    /** column index of the time-stamp column. */
    public static final int SELECTOR_TIMESTAMP = 5;
    
    /** column index of the status flag. */
    public static final int SELECTOR_STATUS = 6;
    
    /** column index of the comment field. */
    public static final int SELECTOR_COMMENT = 7;
    
    /** column index of the mark. */
    public static final int SELECTOR_MARK = 8;    
    
    /** column index of the comment column. */
    public static final int SELECTOR_CONTENT = 9;
    
    /** the name of the database in the database server. */
    public static final String DB_NAME = "llrpMsgDB";
    
    /** the name of the LLRP message repository table. */
    public static final String TABLE_LLRP_REPOSITORY = "llrp_msg";
    
    private static final String SELECT_ALL_QUERY = "select * from ";
    
    private static final String ORDER_QUERY = "order by MSG_TIME DESC";
    
    // the log4j logger.
    private static Logger log = Logger.getLogger(AbstractSQLRepository.class);
    
    /** whether the repository is healthy or not. */
    private boolean isHealth;
    
    /** the number of table columns. */
    protected static final int NUM_TABLE_COLUMNS = 8;
    
    // ------------------------- JDBC Stuff ------------------------------
    /** the JDBC connection. */
    private Connection conn = null;
    
    /** the database driver to use. NOTICE: the default is derby!. */
    public static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    
    /** the database user name. */
    private String username = "llrp";
    
    /** the database password. */
    private String password = "llrp";
    
    /** the connection URL. */
    private String connectURL;
        
    /** whether to log RO_ACCESS_REPORT. */
    private boolean logROAccess = false;
    
    /** map with additional arguments to be passed to the initializer. */
    private Map<String, String> args = null;
    
    // a handle to the RO_ACCESS_REPORTS logging table. we can use the derby 
    // database always, as there are no conflicts with the data-types.
    private DerbyROAccessReportsRepository repoROAccessReports = null;
    
    /** format for the debug in openConnection**/
    protected static final String OPENING_FORMAT = "Opening %s connection with:\n" +
            "\tusername: %s\n " +
            "\tJDBC connector URL: %s\n"; 

    protected static final String CREATE_TABLE_PREFIX = "CREATE TABLE " + TABLE_LLRP_REPOSITORY + " "
                                    + "(MSG_ID CHAR(32),"
                                    + "MSG_TYPE CHAR(32),"
                                    + "READER CHAR(64),"
                                    + "ADAPTER CHAR(64),"
                                    + "MSG_TIME TIMESTAMP,"
                                    + "STATUS CHAR(64),"
                                    + "COMMENT VARCHAR(64),"
                                    + "MARK CHAR(3),"
                                    + "CONTENT ";

    
    //------------------------getter and setter--------------------------------
    /**
     * Returns the username to use
     * 
       * @return the username as <code>String</code>
       */
      protected String getUsername() {
          return username;
      }

      /**
       * Sets the username to use
       * 
       * @param username - the username to set as <code>String</code>
       */
      protected void setUsername(String username) {
          this.username = username;
      }

      /**
       * Returns the password to use
       * 
       * @return the password to use as <code>String</code>
       */
      protected String getPassword() {
          return password;
      }

      /**
       * Sets the password to use
       * 
       * @param password - the password to set as <code>String</code>
       */
      protected void setPassword(String password) {
          this.password = password;
      }

      
    /**
     * Returns the URL to connect 
     * 
     * @return the URL to connect as <code>String</code> 
     */
    protected String getConnectURL() {
        return connectURL;
    }

    /**
     * Sets the URL to connect
     * 
     * @param connectURL - the connectURL to set as <code>String</code>
     */
    protected void setConnectURL(String connectURL) {
        this.connectURL = connectURL;
    }

    // ------------------------- SQL STATEMENTS -------------------------------
    /**
     * <strong>NOTICE:</strong> this SQL command corresponds to derby SQL!.
     * @return a SQL command that creates the table.
     */
    protected String sqlCreateTable(){
        return CREATE_TABLE_PREFIX +"CLOB)";
    }
    

    /**
     * <strong>NOTICE:</strong> this SQL command corresponds to derby SQL!. So 
     * override, if your database uses different SQL instructions.
     * @return a SQL command that erases all the LLRP messages.
     */
    protected String sqlRemoveAllMessages() {
        return "delete from " + TABLE_LLRP_REPOSITORY;
    }
    
    /**
     * <strong>NOTICE:</strong> this SQL command corresponds to derby SQL!. So 
     * override, if your database uses different SQL instructions.
     * @return a SQL command that drops the LLRP message table.
     */
    protected String sqlDropTable() {
        return "DROP TABLE " + TABLE_LLRP_REPOSITORY;
    }
    
    /**
     * <strong>NOTICE:</strong> this SQL command corresponds to derby SQL!. So 
     * override, if your database uses different SQL instructions.
     * @return a SQL command that removes all the messages that belong to a given adapter.
     */
    protected String sqlRemoveAllAdapterMessages() {
        return "delete from " + TABLE_LLRP_REPOSITORY + " where ADAPTER=?";
    }
    
    /**
     * <strong>NOTICE:</strong> this SQL command corresponds to derby SQL!. So 
     * override, if your database uses different SQL instructions.
     * @return a SQL command that removes all the messages that belong to a given reader.
     */
    protected String sqlRemoveAllReaderMessages() {
        return "delete from " + TABLE_LLRP_REPOSITORY + " where ADAPTER=? and READER=?";
    }
    
    /**
     * <strong>NOTICE:</strong> this SQL command corresponds to derby SQL!. So 
     * override, if your database uses different SQL instructions.
     * @return a SQL command that inserts a new item into the database.
     */
    protected String sqlInsertMessage() {
        return "insert into " + TABLE_LLRP_REPOSITORY + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }
    
    /**
     * <strong>NOTICE:</strong> this SQL command corresponds to derby SQL!. So 
     * override, if your database uses different SQL instructions.
     * @return a SQL command that selects an item by its ID.
     */
    protected String sqlSelectMessageByID() {
        return SELECT_ALL_QUERY + TABLE_LLRP_REPOSITORY + " where MSG_ID=?";
    }
    
    /**
     * <strong>NOTICE:</strong> this SQL command corresponds to derby SQL!. So 
     * override, if your database uses different SQL instructions.
     * @return a SQL command that selects all the messages with the content.
     */
    protected String sqlSelectMessagesWithContent() { 
        return SELECT_ALL_QUERY + TABLE_LLRP_REPOSITORY + " " +
            ORDER_QUERY;
    }

    /**
     * <strong>NOTICE:</strong> this SQL command corresponds to derby SQL!. So 
     * override, if your database uses different SQL instructions.
     * @return a SQL command that selects all the messages without the content.
     */
    protected String sqlSelectMessagesWithoutContent() {
        return "select MSG_ID,MSG_TYPE,READER,ADAPTER,MSG_TIME,STATUS,COMMENT,MARK " +
            "from " + TABLE_LLRP_REPOSITORY + " " +
            ORDER_QUERY;
    }
    
    // ------ adaptor given ------
    /**
     * <strong>NOTICE:</strong> this SQL command corresponds to derby SQL!. So 
     * override, if your database uses different SQL instructions.
     * @return a SQL command that selects all the messages to a given adapter 
     * with the content.
     */
    protected String sqlSelectByAdapterWithContent() { 
        return SELECT_ALL_QUERY + TABLE_LLRP_REPOSITORY + " where ADAPTER=? " +
            ORDER_QUERY;
    }
    
    /**
     * <strong>NOTICE:</strong> this SQL command corresponds to derby SQL!. So 
     * override, if your database uses different SQL instructions.
     * @return a SQL command that selects all the messages to a given adapter 
     * without the content.
     */
    protected String sqlSelectByAdapterWithoutContent() { 
        return "select MSG_ID,MSG_TYPE,READER,ADAPTER,MSG_TIME,STATUS,COMMENT,MARK " +
            "from " + TABLE_LLRP_REPOSITORY + " where ADAPTER=? " +
            ORDER_QUERY;
    }
    
    // ------ reader and adaptor given ------
    /**
     * <strong>NOTICE:</strong> this SQL command corresponds to derby SQL!. So 
     * override, if your database uses different SQL instructions.
     * @return a SQL command that selects all the messages to a given adapter 
     * and a given reader with the content.
     */
    protected String sqlSelectByAdapterAndReaderWithContent() {
        return SELECT_ALL_QUERY + TABLE_LLRP_REPOSITORY + " where ADAPTER=? and READER=? " +
            ORDER_QUERY;
    }
    
    /**
     * <strong>NOTICE:</strong> this SQL command corresponds to derby SQL!. So 
     * override, if your database uses different SQL instructions.
     * @return a SQL command that selects all the messages to a given adapter 
     * and a given reader without the content.
     */
    protected String sqlSelectByAdapterAndReaderWithoutContent() { 
        return "select MSG_ID,MSG_TYPE,READER,ADAPTER,MSG_TIME,STATUS,COMMENT,MARK " +
            "from " + TABLE_LLRP_REPOSITORY + " where ADAPTER=? and READER=? " +
            ORDER_QUERY;
    }

    
    /**
     * Loads the appropriate JDBC driver for this environment/framework. 
     * @return true if the loading went fine, false otherwise.
     */
    protected boolean loadDriver() {
        boolean found = false;
        final String driver = getDBDriver();
        try {
            Class.forName(driver).newInstance();
            log.info(String.format("Loaded the appropriate driver: %s",
                    driver));
            found = true;
        } catch (ClassNotFoundException cnfe) {
            log.warn("Unable to load the JDBC driver " + driver);
            log.warn("Please check your CLASSPATH.");
            cnfe.printStackTrace(System.err);
        } catch (InstantiationException ie) {
            log.warn("Unable to instantiate the JDBC driver " + driver);
            ie.printStackTrace(System.err);
        } catch (IllegalAccessException iae) {
            log.warn("Not allowed to access the JDBC driver " + driver);
            iae.printStackTrace(System.err);
        }
        return found;
    }
    
    /**
     * Returns the class name of the JDBC driver to be used. <strong>NOTICE
     * </strong>: you should override this method if you use a database other 
     * than derby.
     * @return a class name of the JDBC driver to be used.
     */
    protected String getDBDriver() {
        return DB_DRIVER;
    }
    
    /**
     * Opens the JDBC connection to the database.
     * @return a handle to the Connection item.
     * @throws SQLException whenever the connection could not be established.
     */
    protected abstract Connection openConnection() throws SQLException;
    
    /**
     * Default implementation for open connection
     * 
     * @param name - name of the repository as <code>String</code>
     * @param log - logger instance to use as <code>Logger<code>
     * 
     * @return the connection open as <code>Connection</code>
     * 
     * @throws SQLException whenever the connection could not be established.
     */
    protected Connection defaultOpenConnection(String name, Logger log)  throws SQLException {
        log.debug(String.format(OPENING_FORMAT, name, getUsername(), getConnectURL()));
        
        return DriverManager.getConnection(getConnectURL(), getUsername(), getPassword());
    }
    
    @Override
    public void initialize(Map<String, String> args) 
        throws LLRPRuntimeException {

        /** whether to wipe the database at startup or not. */
        boolean wipe = false;
        
        /** whether to wipe the RO_ACCESS_REPORTS database at startup or not. */
        boolean wipeROAccess = false;

        this.args = args;
        
        username = args.get(RepositoryFactory.ARG_USERNAME);
        password = args.get(RepositoryFactory.ARG_PASSWRD);
        connectURL = args.get(RepositoryFactory.ARG_JDBC_STRING);
        try {
            wipe = Boolean.parseBoolean(args.get(RepositoryFactory.ARG_WIPE_DB));
            wipeROAccess = Boolean.parseBoolean(
                args.get(RepositoryFactory.ARG_WIPE_RO_ACCESS_REPORTS_DB));
            logROAccess = Boolean.parseBoolean(
                    args.get(RepositoryFactory.ARG_LOG_RO_ACCESS_REPORT));
        } catch (NumberFormatException e) {
            wipe = false;
            wipeROAccess = false;
            logROAccess = false;
            log.error("wrong boolean value in args table for wipe-db|wipe-ro" + 
                    " - using defaults (false).");
        }
        
        // check if values are set correctly.
        if (null == username) {
            throw new LLRPRuntimeException("username missing in args table.");
        }
        if (null == password) {
            throw new LLRPRuntimeException("password missing in args table.");
        }
        if (null == connectURL) {
            throw new LLRPRuntimeException("connectURL missing in args table.");
        }

        //load the desired JDBC driver
        isHealth = loadDriver();
        log.debug("database driver loaded.");
        
        try {
            conn = openConnection();
        } catch (Exception e) {
            isHealth = false;
            throw new LLRPRuntimeException(e);
        }
        log.info("Connection Established");
    
        // wipe table if erroneous or if user requests it by preferences.
        if (!existsTable() || wipe) {
            dropTable();
            createTable();
        }
    }
    
    @Override
    public Map<String, String> getArgs() {
        return args;
    }
    
    /**
     * checks whether the required tables exist or not.
     * @return true if everything is ok, false otherwise.
     */
    protected boolean existsTable() {
        // we try to make a SQL query. if it fails, we assume the table to be dead...
        ResultSet resultSet = null;
        try {
            DatabaseMetaData dbMeta = conn.getMetaData();
            resultSet = dbMeta.getColumns(
                    null, null, TABLE_LLRP_REPOSITORY, null);
            int n = 0;
            while (resultSet.next()) {
                n++;
            }
            if (n<NUM_TABLE_COLUMNS) {
                throw new SQLException("missing fields");
            }
             
        } catch (SQLException e) {
            log.error("table erroneous or missing. therefore recreate it.");
            return false;
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error(e.getStackTrace(),e);
            }
        }
        return true;
    }
    
    /** 
     * drops the table. 
     */
    protected void dropTable() {
        Statement sDropTable = null;
        try {
            
            sDropTable = conn.createStatement();
            sDropTable.execute(sqlDropTable());
            
            log.info("Existing Table Removed.");
            
        } catch (Exception e) {
            log.info("Table doesn't exist. Remove failed." + e.getMessage());
        } finally {
            try {
                sDropTable.close();
            } catch (SQLException e) {
                log.error(e.getStackTrace(),e);
            }
        }
    }
    
    /**
     * generates the necessary tables.
     */
    protected void createTable() {
        Statement sCreateTable = null;
        try {
            
            sCreateTable = conn.createStatement();
            
            // In first time, the message table will be created. If the table
            // exists. The Exception will be triggered.
            //
            sCreateTable.execute(sqlCreateTable());
            sCreateTable.close();
            
            log.info("New Table Created.");
        } catch (Exception e) {
            log.info("Table exists. " + e.getMessage());
        } finally {
            try {
                sCreateTable.close();
            } catch (SQLException e) {
                log.error(e.getStackTrace(),e);
            }
        }
    }
    
    /**
     * store an LLRP message into the repository.
     * @param aMessage the message to be stored.
     */
    public void put(LLRPMessageItem aMessage) {
        try {
            PreparedStatement psInsert = conn.prepareStatement(sqlInsertMessage());

            psInsert.setString(SELECTOR_ID, aMessage.getId());
            psInsert.setString(SELECTOR_MESSAGE_TYPE, aMessage.getMessageType());
            psInsert.setString(SELECTOR_READER, aMessage.getReader());
            String adaptor = aMessage.getAdapter();
            if (adaptor == null) {
                adaptor = AdaptorManagement.DEFAULT_ADAPTOR_NAME;
            }
            psInsert.setString(SELECTOR_ADAPTOR, adaptor);
            psInsert.setTimestamp(
                    SELECTOR_TIMESTAMP, 
                    aMessage.getTime());
            psInsert.setString(SELECTOR_CONTENT, aMessage.getContent());
            psInsert.setString(SELECTOR_COMMENT, aMessage.getComment());
            psInsert.setString(SELECTOR_MARK, "" + aMessage.getMark());
            psInsert.setString(SELECTOR_STATUS, aMessage.getStatusCode());
            
            psInsert.executeUpdate();
            psInsert.close();
        
            log.debug("Put Message (ID=" + aMessage.getId() + ") into database.");
        } catch (SQLException sqle) {
            log.error(sqle.getStackTrace(),sqle);
        }
    }
    
    /**
     * remove all the messages from the repository.
     */
    public void clearAll() {
        try {
            PreparedStatement psRemoveAll = 
                conn.prepareStatement(sqlRemoveAllMessages());
            psRemoveAll.executeUpdate();
            psRemoveAll.close();
        } catch (SQLException sqle) {
            log.error(sqle.getStackTrace(),sqle);
        }
    }
    
    /**
     * @return true if the repository is ok, false otherwise.
     */
    public boolean isHealth() {
        return isHealth;
    }

    /**
     * the method computes the number of messages stored in the repository 
     * depending on the input parameters:
     * <ol>
     *     <li>(adaptor == null) then compute all messages in the repository.</li>
     *  <li>(adaptor != null) && (reader == null) then compute all the messages 
     *  for the adapter ignoring the name of the reader.</li>
     *  <li>(adaptor != null) && (reader != null) then compute all the messages 
     *  for the adapter where the reader name is equal to reader.</li> 
     * </ol>
     * @param adaptor the name of the adapter.
     * @param reader the name of the reader.
     * @return the number of messages stored in the repository.
     */
    public int count(String adaptor, String reader) {
        Statement stmt = null;    
        ResultSet resultSet = null;
        int rowcount = 0;
        try {
            stmt = conn.createStatement();
            String query = "";
            if (null == adaptor) {
                // all OK
                query = "SELECT COUNT(*) FROM LLRP_MSG";
            } else if (null == reader) {
                // restrict to adaptor
                query = String.format("%s WHERE ADAPTER='%s'", query, adaptor);
            } else {
                query = String.format("%s WHERE ADAPTER='%s' AND READER='%s'", 
                        query, adaptor, reader);
            }
            resultSet = stmt.executeQuery(query);

            // Get the number of rows from the result set
            if (resultSet.next()) {
                rowcount = resultSet.getInt(1);
            }

            stmt.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error("Could not retrieve the number of messages: " + 
                    e.getMessage());
        } finally {
            try {
                stmt.close();
                resultSet.close();
            } catch (SQLException e) {
                log.error(e.getStackTrace(),e);
            }
        }
        return rowcount;
    }

    /**
     * clear the repository from entries to a given adapter.
     * @param adapter the name of the adapter to clean out.
     */
    public void clearAdapter(String adapter) {
        try {
            PreparedStatement psRemove = conn.prepareStatement(
                    sqlRemoveAllAdapterMessages());
            psRemove.setString(1, adapter);
            psRemove.executeUpdate();
            psRemove.close();
        } catch (SQLException sqle) {
            log.error(sqle.getStackTrace(),sqle);
        }        
    }

    /**
     * clear the repository from entries to a given adapter and a given reader.
     * @param adapter the name of the adapter.
     * @param reader the name of the reader.
     */
    public void clearReader(String adapter, String reader) {
        try {
            PreparedStatement psRemove = conn.prepareStatement(
                    sqlRemoveAllReaderMessages());
            psRemove.setString(1, adapter);
            psRemove.setString(2, reader);
            psRemove.executeUpdate();
            psRemove.close();
        } catch (SQLException sqle) {
            log.error(sqle.getStackTrace(),sqle);
        }    
    }
    
    /**
     * Close the database connection.
     */
    public void close() {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            log.error(e.getStackTrace(),e);
        }
    }
    
    /**
     * returns all the messages from the specified adaptor and the reader 
     * limited by num. if you set num to RETRIEVE_ALL all messages get returned.
     * if you set readerName to null, all the messages of all the readers with 
     * adaptor adaptorName will be returned.
     * @param adaptorName the name of the adaptor.
     * @param readerName the name of the reader.
     * @param num how many messages to retrieve.
     * @param content if true retrieve the message content, false no content.
     * @return a list of messages.
     */
    public List<LLRPMessageItem> get(
            String adaptorName, String readerName, int num, boolean content) {
        
        ArrayList<LLRPMessageItem> msgs = new ArrayList<LLRPMessageItem> ();
        ResultSet results = null;
        
        try {
            PreparedStatement st = null;
            String sql = null;
            if ((null == adaptorName) || 
                    (Constants.ROOT_NAME.equals(
                            adaptorName))) {    
                sql = sqlSelectMessagesWithoutContent();
                if (content) {
                    sql = sqlSelectMessagesWithContent();
                }
                st = conn.prepareStatement(sql);
            } else if (readerName != null) {
                sql = sqlSelectByAdapterAndReaderWithoutContent();
                if (content) {
                    sql = sqlSelectByAdapterAndReaderWithContent();
                }
                st = conn.prepareStatement(sql);
                st.setString(1, adaptorName.trim());
                st.setString(2, readerName.trim());
            } else {
                sql = sqlSelectByAdapterWithoutContent();
                if (content) {
                    sql = sqlSelectByAdapterWithContent();
                }
                
                st = conn.prepareStatement(sql);
                st.setString(1, adaptorName.trim());
            }
            // bound the number of items to retrieve
            if (num != Repository.RETRIEVE_ALL) {
                st.setMaxRows(num);
            }
            results = st.executeQuery();
            while (results.next()) {
                LLRPMessageItem item = new LLRPMessageItem();
                item.setAdapter(results.getString(SELECTOR_ADAPTOR));
                item.setComment(results.getString(SELECTOR_COMMENT));
                item.setId(results.getString(SELECTOR_ID));
                item.setMark(results.getInt(SELECTOR_MARK));
                item.setMessageType(results.getString(SELECTOR_MESSAGE_TYPE));
                item.setReader(results.getString(SELECTOR_READER));
                item.setStatusCode(results.getString(SELECTOR_STATUS));
                item.setTime(results.getTimestamp(SELECTOR_TIMESTAMP));
                if (content) {
                    item.setContent(results.getString(SELECTOR_CONTENT));
                }
                msgs.add(item);
            }
            
        } catch (Exception e) {
            log.error(
                    String.format(
                            "could not retrieve from database: %s\n", 
                            e.getMessage())
                    );
        } finally {
            try {
                results.close();
            } catch (SQLException e) {
                log.error(e.getStackTrace(),e);
            }
        }
            
        return msgs;
    }
    
    /**
     * @param aMsgSysId the message id of the item to be retrieved.
     * @return the LLRP message to the given message id.
     */
    public LLRPMessageItem get(String aMsgSysId) {
        
        LLRPMessageItem msg = new LLRPMessageItem();
        ResultSet results = null;
        try {
            PreparedStatement psSelect = conn.prepareStatement(sqlSelectMessageByID());
            psSelect.setString(1, aMsgSysId);
            results = psSelect.executeQuery();
            
            if (results.next()) {
                msg.setId(results.getString(SELECTOR_ID));
                msg.setMessageType(results.getString(SELECTOR_MESSAGE_TYPE));
                msg.setReader(results.getString(SELECTOR_READER));
                msg.setAdapter(results.getString(SELECTOR_ADAPTOR));
                msg.setTime(results.getTimestamp(SELECTOR_TIMESTAMP));
                msg.setContent(results.getString(SELECTOR_CONTENT));
                msg.setComment(results.getString(SELECTOR_COMMENT));
                msg.setMark(results.getInt(SELECTOR_MARK));
                msg.setStatusCode(results.getString(SELECTOR_STATUS));
                
                log.debug("Get Message (ID=" + results.getString(1) + ") from database.");
            }
            
            psSelect.close();
            results.close();
            
        } catch (SQLException sqle) {
            log.error(sqle.getStackTrace(),sqle);
        } finally {
            try {
                results.close();
            } catch (SQLException e) {
                log.error(e.getStackTrace(),e);
            }
        }

        return msg;
    }
    
    /**
     * @return a handle to the database connection. users of the repository are 
     * allowed to use the database for their own purposes.
     */
    public Connection getDBConnection() {
        return conn;
    }
    
    @Override
    public ROAccessReportsRepository getROAccessRepository() {
        if (!logROAccess) { 
            return null; 
        }
        
        // for the RO_ACCESS_REPORTS repository, we can use the derby one as 
        // the SQL set used, works for both MySQL and Derby (only in the case
        // RO_ACCESS_REPORTS repository!).
        if (null == repoROAccessReports) {
            log.debug("No RepoROAccessReports handle yet - Create a new one.");
            repoROAccessReports = new DerbyROAccessReportsRepository();
            try {
                repoROAccessReports.initialize(this);
            } catch (LLRPRuntimeException e) {
                log.error(String.format(
                        "Could not initialize the RO_ACCESS_REPORTS repo: '%s'",
                        e.getMessage()));
            }    
        }
        return repoROAccessReports;
    }
}
