package com.devito.dropwizardTest;

import com.devito.dropwizardTest.health.TemplateHealthCheck;
import com.devito.dropwizardTest.resource.DropWizardTestResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by thomas.devito on 7/9/14.
 */
public class DropWizardTestApplication extends Application<DropWizardTestConfiguration> {

    public static void main(String[] args) throws Exception {
        new DropWizardTestApplication().run(args);
    }


    @Override
    public void initialize(Bootstrap<DropWizardTestConfiguration> dropWizardTestConfigurationBootstrap) {

    }

    @Override
    public void run(DropWizardTestConfiguration dropWizardTestConfiguration, Environment environment) throws Exception {
        final DropWizardTestResource dropWizardTestResource = new DropWizardTestResource(dropWizardTestConfiguration.getTemplate(),
                dropWizardTestConfiguration.getDefaultName());

        final TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck(dropWizardTestConfiguration.getTemplate());

        environment.healthChecks().register("template", templateHealthCheck);
        environment.jersey().register(dropWizardTestResource);
    }
}
