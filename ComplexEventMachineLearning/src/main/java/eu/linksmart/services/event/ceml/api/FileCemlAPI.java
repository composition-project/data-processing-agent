package eu.linksmart.services.event.ceml.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import eu.linksmart.api.event.ceml.CEMLRequest;
import eu.linksmart.api.event.types.impl.BootstrappingBean;
import eu.linksmart.api.event.types.impl.GeneralRequestResponse;
import eu.linksmart.api.event.types.impl.MultiResourceResponses;
import eu.linksmart.services.event.ceml.core.CEML;
import eu.linksmart.services.event.ceml.core.CEMLManager;
import eu.linksmart.services.event.connectors.FileConnector;
import eu.linksmart.services.event.feeder.BootstrappingBeanFeeder;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by José Ángel Carvajal on 12.10.2016 a researcher of Fraunhofer FIT.
 */
public class FileCemlAPI extends FileConnector {
    public FileCemlAPI(String... filePaths) {
        super(filePaths);
    }

    @Override
    protected void loadStream(String inputStream){
        try {
            List<CEMLRequest> requests = CEML.getMapper().readValue(inputStream,new TypeReference< ArrayList<CEMLManager>>() {} );
            ArrayList<GeneralRequestResponse> responses = new ArrayList<>();
            requests.stream().forEach(i->responses.addAll(CEML.create(i).getResponses()));
            responses.stream().filter(i->i.getStatus()>299).forEach(i->loggerService.error(i.getHeadline()+": "+i.getMessage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
