package com.mulesoft.training;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.tck.junit4.rule.DynamicPort;

public class HelloMavenTest extends FunctionalTestCase {

	@Rule
	public DynamicPort myPort = new DynamicPort("http.port"); 	
	
    @Test
    public void mavenFlowReturnsHelloMaven() throws Exception {
    	System.out.println("\nTestcase #1 => Http Port: "+myPort.getNumber());
        runFlowAndExpect("mavenFlow", "Hello Maven");
    }
    
    @Test
    public void retrieveFlightsAddsAppropriateHeader() throws Exception {
    	System.out.println("\nTestcase #2 => Http Port: "+myPort.getNumber());
      MuleEvent event = runFlow("retrieveFlights");
      String contentType = event.getMessage().getOutboundProperty("Content-Type");
      assertEquals("application/json", contentType);
    }
    
    @Override
    protected String[] getConfigFiles() {
    	String configFiles[] = {"maven-project-mahpatro.xml", "common-resources.xml"};
         return configFiles;
    }

}
