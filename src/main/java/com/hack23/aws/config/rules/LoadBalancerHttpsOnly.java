package com.hack23.aws.config.rules;

import java.io.IOException;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.ConfigEvent;

public class LoadBalancerHttpsOnly {

	public static final String AWS_REGION_PROPERTY = "AWS_DEFAULT_REGION";

	public void handle(ConfigEvent event, Context context) throws IOException {
		Regions region = Regions.fromName(System.getenv(AWS_REGION_PROPERTY));
	}

}
