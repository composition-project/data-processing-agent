package eu.linksmart.services.event.datafusion.handler;

import eu.linksmart.services.event.datafusion.handler.base.BaseEventHandler;
import eu.linksmart.services.event.datafusion.intern.Utils;
import eu.linksmart.api.event.components.ComplexEventSyncHandler;
import eu.linksmart.api.event.types.Statement;
import org.slf4j.Logger;


/**
 * Created by José Ángel Carvajal on 16.12.2015 a researcher of Fraunhofer FIT.
 */
public class ComplexEventSynchHandler extends BaseEventHandler implements ComplexEventSyncHandler {
    protected transient Logger loggerService = Utils.initLoggingConf(this.getClass());
    protected Statement statement;


    public ComplexEventSynchHandler(Statement statement) {
        super(statement);
        this.statement =statement;

    }

    @Override
    protected void processMessage(Object events) {
        if(events!=null)
            statement.setSynchronousResponse(events);
    }
    @Override
    public void update(Object event) {
        eventExecutor.stack(event);
    }
}
