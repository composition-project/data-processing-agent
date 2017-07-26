package eu.linksmart.services.payloads.ogc.sensorthing;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import eu.linksmart.services.payloads.ogc.sensorthing.internal.Interval;
import eu.linksmart.services.payloads.ogc.sensorthing.internal.serialize.IntervalDateDeserializer;
import eu.linksmart.services.payloads.ogc.sensorthing.internal.serialize.IntervalDateSerializer;
import eu.linksmart.services.payloads.ogc.sensorthing.linked.*;
import eu.linksmart.services.payloads.ogc.sensorthing.linked.ObservedProperty;
import eu.linksmart.services.payloads.ogc.sensorthing.linked.Sensor;
import eu.linksmart.services.payloads.ogc.sensorthing.linked.Thing;
import org.geojson.Polygon;

import java.util.List;
import java.util.Map;

/**
 * Created by José Ángel Carvajal on 25.07.2017 a researcher of Fraunhofer FIT.
 */
@JsonDeserialize(as = DatastreamImpl.class)
@JsonSerialize(as = DatastreamImpl.class)
public interface Datastream extends CommonControlInfoDescription {
    @JsonGetter("observations")
    List<Observation> getObservations();

    @JsonSetter("observations")
    void SetObservations(List<Observation> observation);

    @JsonPropertyDescription("TBD.")
    @JsonGetter(value = "Observations@iot.navigationLink")
    String getObservationsNavigationLink();

    @JsonSetter(value = "Observations@iot.navigationLink")
    void setObservationsNavigationLink(String value);

    @JsonPropertyDescription("TBD.")
    @JsonGetter(value = "ObservedProperty@iot.navigationLink")
    String getObservedPropertNavigationLink();

    @JsonPropertyDescription("TBD.")
    @JsonSetter(value = "ObservedProperty@iot.navigationLink")
    void setObservedPropertyNavigationLink(String value);

    @JsonGetter(value = "sensor")
    Sensor getSensor();

    @JsonSetter(value = "sensor")
    void setSensor(Sensor sensor);

    @JsonPropertyDescription("TBD.")
    @JsonGetter(value = "Sensor@iot.navigationLink")
    String getSensorNavigationLink();

    @JsonPropertyDescription("TBD.")
    @JsonSetter(value = "Sensor@iot.navigationLink")
    void setSensorNavigationLink(String value);

    // @JsonGetter("observations") // <--- this is intentional
    Thing getThing();

    @JsonSetter(value = "thing")
    void setThing(Thing thing);

    // @JsonPropertyDescription("TBD.")
     @JsonGetter(value = "Thing@iot.navigationLink")
     String getThingNavigationLink();

    //@JsonPropertyDescription("TBD.")
    @JsonSetter(value = "Thing@iot.navigationLink")
    void setThingNavigationLink(String value);

    /**
     * Adds a single {@link eu.almanac.ogc.sensorthing.api.datamodel.Observation} to the set of events belonging to
     * this {@link Datastream} instance.
     *
     * @param observation
     *            The observation to add.
     */
    void addObservation(Observation observation);

    /**
     * Removes a single {@link Observation} from the set of events
     * belonging to this {@link Datastream} instance.
     *
     * @param observation
     *            The observation to remove.
     * @return true if removal was successful, false otherwise
     */
    boolean removeObservation(Observation observation);

    /**
     * Provides the {@link eu.almanac.ogc.sensorthing.api.datamodel.ObservedProperty} instance describing the property to
     * which events belonging to this data stream belong.
     *
     * @return the observedProperty
     */
    @JsonGetter("observedProperty")
    ObservedProperty getObservedProperty();

    /**
     * Sets the {@link eu.almanac.ogc.sensorthing.api.datamodel.ObservedProperty} instance describing the property to
     * which events belonging to this data stream belong.
     *
     * @param observedProperty
     *            the observedProperty to set
     */
    @JsonSetter("observedProperty")
    void setObservedProperty(ObservedProperty observedProperty);

    @JsonGetter(value = "observationType")
    @JsonPropertyDescription("TBD")
    String getObservationType();

    @JsonSetter(value = "observationType")
    @JsonPropertyDescription("TBD.")
    void setObservationType(String observationType);

    @JsonGetter(value = "unitOfMeasurement")
    @JsonPropertyDescription("TBD")
    Map<String,Object> getUnitOfMeasurement();

    @JsonSetter(value = "unitOfMeasurement")
    @JsonPropertyDescription("TBD.")
    void setUnitOfMeasurement(Map<String, Object> unitOfMeasurement);

    @JsonGetter(value = "phenomenonTime")
    @JsonSerialize(using = IntervalDateSerializer.class)
    @JsonPropertyDescription("TBD")
    Interval getPhenomenonTime();

    @JsonSetter(value = "phenomenonTime")
    @JsonDeserialize(using = IntervalDateDeserializer.class)
    @JsonPropertyDescription("TBD.")
    void setPhenomenonTime(Interval phenomenonTime);

    @JsonGetter(value = "resultTime")
    @JsonSerialize(using = IntervalDateSerializer.class)
    @JsonPropertyDescription("TBD")
    Interval getResultTime();

    @JsonSetter(value = "resultTime")
    @JsonDeserialize(using = IntervalDateDeserializer.class)
    @JsonPropertyDescription("TBD.")
    void setResultTime(Interval resultTime);

    @JsonGetter(value = "observedArea")
    @JsonPropertyDescription("TBD")
    Polygon getObservedArea();

    @JsonSetter(value = "observedArea")
    @JsonPropertyDescription("TBD.")
    void setObservedArea(Polygon observedArea);
}
