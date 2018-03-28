package com.hack23.aws.config.rules;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.amazonaws.services.config.AmazonConfig;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.ConfigEvent;

@RunWith(MockitoJUnitRunner.class)
public class RdsForceSslEnabledTest {

    @Mock
    private Context context;

    @Mock
    private AmazonConfig configClient;

    private ConfigEvent event;
    
    private RdsForceSslEnabled rdsForceSslEnabled;

    @Before
    public void setup() {
        event = new ConfigEvent();
        rdsForceSslEnabled = new RdsForceSslEnabled();
    }
}

