package edu.au.swinburne.maintenance;


import com.google.common.collect.Maps;
import junitx.util.PrivateAccessor;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.event.impl.jobs.JobImpl;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobExecutionContext;
import org.apache.sling.testing.mock.sling.junit.SlingContext;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.osgi.framework.BundleContext;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyMapOf;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActivatePagesTest {


    private static final int MAX_AGE_IN_DAYS = 7;

    private ActivatePages fixture = new ActivatePages();


    @Mock
    BundleContext context;

    @Rule
    public final SlingContext ctx = new SlingContext();

    @Mock
    private ActivatePages.Config configuration;

    @Mock
    private Job job;

    @Mock
    private ResourceResolverFactory resourceResolverFactory;

    @Mock
    private ResourceResolver resourceResolver;

    @Mock(answer = Answers.RETURNS_MOCKS)
    private JobExecutionContext jobContext;


    @Before
    public void setUp() throws Exception {

        setUpJob();
        PrivateAccessor.setField(fixture, "resolverFactory", resourceResolverFactory);
        when(
                resourceResolverFactory
                        .getServiceResourceResolver(anyMapOf(
                                String.class, Object.class))).thenReturn(
                resourceResolver);

    }

    public void setUpJob() {

        Map<String, Object> parameters = Maps.<String, Object>newHashMap();
        parameters.put("age", MAX_AGE_IN_DAYS * 24 * 60);
        job = new JobImpl("not-relevant", "not-relevant_123", "123", parameters);

    }

    @Test
    public void process() {

        fixture.activate(context, configuration);
        fixture.process(job, jobContext);
        assertNotNull("result", jobContext.result().message(String.format("SUCCESS")).succeeded());

    }

}
