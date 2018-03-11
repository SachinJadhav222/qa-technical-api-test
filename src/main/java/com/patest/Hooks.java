package com.patest;

import com.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;

/**
 * This is cucumber Hooks class contains @Before and @After method, which will be executed before and after each Scenario
 */
public class Hooks {
    Logger logger=Logger.getLogger("Hooks");
    String scenarioName;

    @Before
    public void beforeScenario(Scenario scenario){
        scenarioName=scenario.getName();
        logger.info("Scenario Name: "+scenarioName);
    }
    @After
    public void afterScenarios(Scenario scenario){
        if (scenario.isFailed()) {
            scenario.write("Scenario Failed");
            logger.info("Scenario Failed ");
        } else {
            scenario.write("Scenario Passed");
            logger.info("Scenario Passed ");
        }

    }
}
