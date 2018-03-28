package com.hack23.aws.config.rules;

import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.amazonaws.services.config.AmazonConfig;
import com.amazonaws.services.config.model.ComplianceType;
import com.amazonaws.services.config.model.Evaluation;
import com.amazonaws.services.config.model.PutEvaluationsRequest;
import com.amazonaws.services.config.model.ResourceType;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.ConfigEvent;

@RunWith(MockitoJUnitRunner.class)
public class LoadBalancerHttpsOnlyTest {

	private static final String RESULT_TOKEN = "resultToken";
    private static final String ACCOUNT_ID = "accountId";
    private static final String ACCOUNT_TYPE = "AWS::::Account";
    
    // AWS::ElasticLoadBalancing::LoadBalancer, AWS::ElasticLoadBalancingV2::LoadBalancer
    /*
    {
    	  "InstancePort" : String,
    	  "InstanceProtocol" : String,
    	  "LoadBalancerPort" : String,
    	  "PolicyNames" :  [ String, ... ],
    	  "Protocol" : String,
    	  "SSLCertificateId" : String
    	}
    */
    
    private static final Date NOW = new Date();
    
    @Mock
    private Context context;

    @Mock
    private AmazonConfig configClient;

    @Rule
    public final EnvironmentVariables environmentVariables = new EnvironmentVariables();
    
    private ConfigEvent event;
    
    private LoadBalancerHttpsOnly loadBalancerHttpsOnly;

    @Before
    public void setup() {
    	environmentVariables.set(LoadBalancerHttpsOnly.AWS_REGION_PROPERTY, "eu-west-1"); 	
        event = new ConfigEvent();
        event.setAccountId(ACCOUNT_ID);
        event.setResultToken(RESULT_TOKEN);
        loadBalancerHttpsOnly = new LoadBalancerHttpsOnly();
    }

    @Test
    public void testHandleCompliant() throws IOException {
    	loadBalancerHttpsOnly.handle(event, context);
        verify(configClient).putEvaluations(buildPutEvaluationsRequest(ComplianceType.COMPLIANT));
    }

    @Test
    public void testHandleNonCompliant() throws IOException {
    	loadBalancerHttpsOnly.handle(event, context);
        verify(configClient).putEvaluations(buildPutEvaluationsRequest(ComplianceType.NON_COMPLIANT));
    }

    private PutEvaluationsRequest buildPutEvaluationsRequest(ComplianceType compliance) {
        Evaluation evaluation = new Evaluation()
                .withComplianceResourceId(ACCOUNT_ID)
                .withComplianceResourceType(ResourceType.AWSElasticLoadBalancingV2LoadBalancer.toString())
                .withOrderingTimestamp(NOW)
                .withComplianceType(compliance);
        return new PutEvaluationsRequest()
                .withEvaluations(evaluation)
                .withResultToken(RESULT_TOKEN);
    }
}
