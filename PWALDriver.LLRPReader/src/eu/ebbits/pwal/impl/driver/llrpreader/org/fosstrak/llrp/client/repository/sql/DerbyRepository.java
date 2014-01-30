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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import eu.ebbits.pwal.impl.driver.llrpreader.org.fosstrak.llrp.adaptor.exception.LLRPRuntimeException;

/**
 * The LLRP message repository implementation based on Sun JavaDB.
 * Please make sure the derby.jar in the build path before you can
 * start the database.
 *
 * @author Haoning Zhang
 * @author sawielan
 * @version 1.0
 */

public class DerbyRepository extends AbstractSQLRepository {
    
    // the JDBC protocol.
    private static final String DB_PROTOCOL = "jdbc:derby:";
    
    // whether to create the DB or not.
    private static final String DB_CREATE = ";create=true";
    
    /** the name of the property for the repository location in the args map. */
    public static final String ARG_REPO_LOCATION = "argRepoLocation";
    
    /** the location of the repository. */
    private String repoLocation;
    
    /**
     * construct a new java DB repository.
     */
    public DerbyRepository() {
    }
    
    @Override
    public void initialize(Map<String, String> args) 
        throws LLRPRuntimeException {
        
        super.initialize(args);
        
        String argRepoLoc = null;
        if ((null == args) || (null == args.get(ARG_REPO_LOCATION))) {
            argRepoLoc = DB_NAME;
        } else {
            argRepoLoc = args.get(ARG_REPO_LOCATION);
        }
        repoLocation = argRepoLoc + DB_NAME;
    }

    @Override
    protected Connection openConnection() throws SQLException {
         return DriverManager.getConnection(DB_PROTOCOL + repoLocation + DB_CREATE);
    }
}

