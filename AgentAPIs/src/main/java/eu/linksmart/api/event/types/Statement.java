package eu.linksmart.api.event.types;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
/**
 *  Copyright [2013] [Fraunhofer-Gesellschaft]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
/**
 * This interface represents an data processing statement, the implementation may varies with the underlying implementation of the CEPEngine.
 * The property statement is completely CEPEngine dependent. The other features are common to all the data processing framework of the agents.
 * This interface is a generalization of any statement of a CEPEngine.<p>
 *
 *
 * @author Jose Angel Carvajal Soto
 * @since       0.03
 * @see  eu.linksmart.api.event.components.CEPEngine
 * @see eu.linksmart.api.event.types.JsonSerializable
 *
 * */
public interface Statement extends JsonSerializable, PersistentRequest {
    /***
     * Name of the statement
     *
     * @return the name of the statement as string
     * */
    String getName();
    /***
     * The native statement for specific underling implementation of a CEP engine (e.g. EPL)
     *
     * @return  returns the statement in the native CEP language as string
     * */
    String getStatement();
    /***
     * return the scope. The scope is the output endpoints (e.g. broker, http server) where the events will be published
     *
     * @return  aliases of the endpoints as string
     * */
    List<String> getScope();
    /***
     * The output message handler (broker/http server) number i of the statement
     *
     * @param index of the broker selected to return
     *
     * @return  The selected broker URL or alias as string
     * */
    String getScope(int index);
    /***
     * The output topics/paths where the events will be published/posted
     *
     * @return  The output topics/paths as string
     * */
    List<String> getOutput();
    /***
     * The output value is a optional value. This return if the value has been defined or not
     *
     * @return  true if there is output has being defined, false otherwise
     * */
    boolean haveOutput();
    /***
     * The Scope value is a optional value. This return if the value has been defined or not
     *
     * @return  true if there is Scope has being defined, false otherwise
     * */
    boolean haveScope();

    /***
     * Return the handler selected to process the result of the complex event, @Default ComplexEventHandlerImpl.
     * Note: The value "" or null is a valid response, this value represent silent events, events that just happen inside the CEP engine.
     *
     * @return  the handler cannonic name of ComplexEventHandler of the statement, @Default ComplexEventHandlerImpl..
     * */
    String getCEHandler();
    /***
     * Return the IDs selected to agents which are targeted to process this statement. If the array is empty means all receivers @Default Empty String[].
     *
     * @return  List of targeted Agents address to process the statement, otherwise empty (all available agents):
     * */
    List<String> getTargetAgents();
    /***
     * Return the state of the Statement, which determines how the statement will be at runtime.
     *
     * @return  Lifecycle Statement State @see StatementLifecycle .
     * */
    StatementLifecycle getStateLifecycle();
    /***
     * In case the LifeCycle of the statement is Sync, the response will be collected by this function instead of an handler.
     *
     * @return the response as an object (the type an semantic depends on the statement).
     * */
    Object getSynchronousResponse();
    /**
     * Alike to the equals from the Object class.
     * The frameworks needs to determinate if two statements are logically equal or not.
     *
     * @see java.lang.Object
     * */
    boolean equals(Object org);
    /**
     * Alike to the hashCode from the Object class.
     * The frameworks needs to determinate if two statements are logically equal or not,
     * and if equal is overwritten then hashCode must be overwrite too.
     *
     *
     * @see java.lang.Object
     * */
    int hashCode();

    /***
     * Setts the scope. The scope is the output endpoints (e.g. broker, http server) where the events will be published
     *
     * @param scope are a list of  aliases of the endpoints where will be published
     * */
    void setScope(List<String> scope);
    /***
     * The output topics/paths where the events will be published/posted
     *
     * @param output array of output topics/paths as string
     * */
    void setOutput(List<String> output);
    /***
     * Set the statement. The native statement for specific underling implementation of a CEP engine (e.g. EPL)
     *
     * @param statement in the native CEP language as string
     * */
    void setStatement(String statement);
    /***
     * sets the name of the statement. The user defined name of the statement
     *
     * @param name is the new given name of the statement as string
     * */
    void setName(String name);
    /***
     * setts the handler selected to process the result of the complex event, @Default ComplexEventHandlerImpl.
     * Note: The value "" or null is a valid response, this value represent silent events, events that just happen inside the CEP engine.
     *
     *
     * @param  CEHandler is the canonical name of ComplexEventHandler of the statement, @Default ComplexEventHandlerImpl..
     *
     * */
    void setCEHandler(String CEHandler);
    /***
     * sets the state of the Statement, which determines how the statement will be at runtime.
     *
     * @see StatementLifecycle .
     * @param  stateLifecycle is the Lifecycle Statement State
     *
     * */
    void setStateLifecycle(StatementLifecycle stateLifecycle);
    /***
     * In case the LifeCycle of the statement is Sync, the response will be set by this function instead of an handler.
     *
     * @param response the response as an object (the type an semantic depends on the statement).
     * */
    void setSynchronousResponse(Object response) ;

    /***
     * setts the IDs of the selected agents which are targeted to process this statement. If the array is empty means all receivers @Default Empty String[].
     *
     * @param targetAgents is the list of targeted agents address to process the statement, otherwise empty (all available agents):
     * */
    void setTargetAgents(List<String> targetAgents);
    /***
     *
     *
     * @param registrable if the statement should or should not be register outside agent
     * */
    void toRegister(boolean registrable);
    /***
     *
     *
     * @return if the statement should be register outside agent
     * */
    boolean isRegistrable();
    /***
     *
     *
     * @return if the output of the statement are REST endpoints instead of MQTT topics
     * */
    boolean isRESTOutput();
    /***
     * setts if the output of the statement are REST endpoints instead of MQTT topics
     *
     * @param active true the outputs are REST endpoints, false the outputs are topics
     * */
    void isRESTOutput(boolean active);
    /***
     * Returns the last compound event result of this statement
     *
     * @return the result of last processing made in this statement. In case of there is none then null
     * */
    Object getLastOutput();
    /***
     * Set the last compound event result of this statement
     *
     * @param lastOutput the result of last processing made in this statement. In case of there is none then null
     * */
    void setLastOutput(Object lastOutput);

    /**
     *
     * @return the type of event this statement will produce
     */
    String getResultType();
    /**
     * @param type set the type this statement will produce
     * */
    void setResultType(String type);

    enum StatementLifecycle {
        /**
         * RUN Execute the statement adding a Handler, which adds a actuate or reacts to the triggered statement.
         */
        RUN,
        /**
         * ONCE Execute once, generating a default the response at the moment
         */
        ONCE,
        /**
         * SYNCHRONOUS similar to ONCE, but returns the generated request.
         */
        SYNCHRONOUS,
        /**
         * PAUSE deploy the query in but do not start it
         */
        PAUSE,
        /**
         * REMOVE makes sens in an existing Statement.
         * This will remove the Statement form the CEP engine realising all other resources related to it
         */
        REMOVE
    }

}
