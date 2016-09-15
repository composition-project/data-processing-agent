package eu.linksmart.api.event.types.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.linksmart.api.event.types.EventEnvelope;
import eu.linksmart.api.event.types.impl.StatementInstance;
import eu.linksmart.api.event.types.Statement;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by José Ángel Carvajal on 13.08.2015 a researcher of Fraunhofer FIT.
 */
public class BootstrappingBean {
    @JsonProperty("statements")
    protected List<Statement> statements;
    @JsonProperty("observations")
    protected Map<String,EventEnvelope[]> observations;
    public BootstrappingBean(){
        statements = null;
        observations = null;
    }

    public List<Statement> getStatements() {

        return statements;

    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public Map<String,EventEnvelope[]> getObservations() {
        return observations;
    }

    public void setObservations(Map<String,EventEnvelope[]> observations) {
        this.observations = observations;
    }

    public Statement getStatement(int index) {

        if(statements!=null)
            return statements.get(index);
        else
            return null;

    }

    public void setStatements(int index, Statement statement) {
        if(statements==null)
            statements = new ArrayList<>();

        statements.set(index, (StatementInstance) statement);

    }
    public EventEnvelope[] getObservations(String topic) {
        if(observations!=null)
            return observations.get(topic);
        else
            return null;
    }

    public void setObservations(String key,EventEnvelope[] observation) {
        if(observations==null)
            observations = new Hashtable<>();

        observations.put(key, observation);

    }
}
