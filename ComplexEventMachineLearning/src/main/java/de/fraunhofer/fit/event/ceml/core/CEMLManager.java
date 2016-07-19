package de.fraunhofer.fit.event.ceml.core;

import eu.almanac.event.datafusion.feeder.StatementFeeder;
import eu.linksmart.api.event.ceml.CEMLRequest;
import eu.linksmart.api.event.datafusion.JsonSerializable;
import eu.linksmart.api.event.ceml.LearningStatement;
import eu.linksmart.api.event.ceml.data.DataDescriptors;
import eu.linksmart.api.event.ceml.evaluation.Evaluator;
import eu.linksmart.api.event.ceml.model.Model;
import eu.linksmart.api.event.datafusion.Statement;
import eu.linksmart.gc.utils.configuration.Configurator;
import eu.linksmart.gc.utils.function.Utils;
import eu.linksmart.gc.utils.logging.LoggerService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by José Ángel Carvajal on 18.07.2016 a researcher of Fraunhofer FIT.
 */
public class CEMLManager implements CEMLRequest {

    protected String name;
    private Configurator conf = Configurator.getDefaultConfig();
    private LoggerService loggerService = Utils.initDefaultLoggerService(CEMLManager.class);
    private int leadingModel =0;

    protected DataDescriptors descriptors;
    protected Model model;
    protected Evaluator evaluator;
    protected ArrayList<Statement> auxiliaryStatements;
    protected ArrayList<LearningStatement> learningStatements;
    protected ArrayList<Statement> deployStatements;

    @Override
    public DataDescriptors getDescriptors() {
        return descriptors;
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public Evaluator getEvaluator() {
        return evaluator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<LearningStatement> getLearningStreamStatements() {
        return learningStatements;
    }

    @Override
    public Collection< Statement> getDeploymentStreamStatements() {
        return deployStatements;
    }

    @Override
    public Collection< Statement> getAuxiliaryStreamStatements() {
        return auxiliaryStatements;
    }

    @Override
    public Statement getStreamStatement(String StatementId) {
        return null;
    }

    @Override
    public void deploy() throws Exception {

    }

    @Override
    public void undeploy() throws Exception {

    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public JsonSerializable build() throws Exception {

        if(descriptors==null||model==null||evaluator==null)
            throw new Exception("The descriptors, model and evaluator are mandatory fields!");
        descriptors.build();

        int i=0;
        for (Statement statement: auxiliaryStatements) {
            statement.setCEHandler("");
            statement.setName("AuxiliaryStream:" + name + "[" + String.valueOf(i) + "]");
            statement.build();
            i++;
        }
        for (LearningStatement statement: learningStatements) {
            statement.setRequest(this);
            statement.setName("LearningStream:" + name + "[" + String.valueOf(i) + "]");
            statement.build();
        }

        for (Statement statement: deployStatements){
            statement.setName("DeploymentStream:" + name + "[" + String.valueOf(i) + "]");
            statement.build();

        }

        model.setDescriptors(descriptors);

        model.build();

        evaluator.build();

        StatementFeeder.feedStatements(auxiliaryStatements);
        ArrayList<Statement> arrayList = new ArrayList<>();
        arrayList.addAll(learningStatements);
        StatementFeeder.feedStatements(arrayList);
        StatementFeeder.feedStatements(deployStatements);

        return this;
    }
}