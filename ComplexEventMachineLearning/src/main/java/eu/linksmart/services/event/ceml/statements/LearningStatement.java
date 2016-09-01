package eu.linksmart.services.event.ceml.statements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.linksmart.api.event.datafusion.exceptions.StatementException;
import eu.linksmart.api.event.datafusion.exceptions.TraceableException;
import eu.linksmart.api.event.datafusion.exceptions.UnknownUntraceableException;
import eu.linksmart.api.event.datafusion.exceptions.UntraceableException;
import eu.linksmart.ceml.handlers.ListLearningHandler;
import eu.linksmart.ceml.handlers.MapLearningHandler;
import eu.linksmart.ceml.core.CEMLManager;
import eu.linksmart.api.event.datafusion.types.impl.StatementInstance;
import eu.linksmart.api.event.ceml.CEMLRequest;
import eu.linksmart.api.event.datafusion.types.JsonSerializable;

/**
 * Created by José Ángel Carvajal on 19.07.2016 a researcher of Fraunhofer FIT.
 */
public class LearningStatement extends StatementInstance implements eu.linksmart.api.event.ceml.LearningStatement {
    @JsonIgnore
    @Override
    public CEMLRequest getRequest() {
        return manager;
    }
    @JsonIgnore
    @Override
    public void setRequest(CEMLRequest request) {
        manager =request;
    }

    @Override
    public JsonSerializable build() throws TraceableException, UntraceableException{

        if(manager==null||name==null||statement==null)
            throw new StatementException(this.getClass().getName(),this.getClass().getCanonicalName(), "The name, CEMLRequest and statements are mandatory fields for a Statment!");
        try {

            if(manager.getDescriptors().isLambdaTypeDefinition())

                CEHandler = ListLearningHandler.class.getCanonicalName();
            else{


                CEHandler = MapLearningHandler.class.getCanonicalName();

            }
        }catch (Exception e){
            throw new UnknownUntraceableException(e.getMessage(),e);
        }

        return this;

    }
    @JsonIgnore
    private CEMLRequest manager =null;
    public LearningStatement(String name, CEMLManager manager , String statement){
        super(name,statement,new String[]{"default"});

        this.statement =statement;
        this.manager =manager;
        if(manager.getDescriptors().isLambdaTypeDefinition())
            CEHandler = ListLearningHandler.class.getCanonicalName();

        CEHandler= MapLearningHandler.class.getCanonicalName();
        this.name =name;
    }
    public LearningStatement(){
        super();
    }

}