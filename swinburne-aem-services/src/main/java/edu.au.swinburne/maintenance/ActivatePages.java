package edu.au.swinburne.maintenance;

import com.day.cq.replication.Replicator;
import com.day.cq.replication.ReplicationOptions;
import com.day.cq.replication.AgentFilter;
import com.day.cq.replication.Agent;
import com.day.cq.replication.ReplicationActionType;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobExecutionContext;
import org.apache.sling.event.jobs.consumer.JobExecutionResult;
import org.apache.sling.event.jobs.consumer.JobExecutor;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple job process to activate pages .This job will be available
 * in AEM maintenance console.Users configure this job to run daily
 * or bi-weekly basis and can also run this job on demand basis as well .
 */
@Component(
        immediate = true,
        service = JobExecutor.class,
        property = {
                "job.topics=com/adobe/granite/maintenance/job/ClearCache",
                "granite.maintenance.name=ActivatePages",
                "granite.maintenance.title=Activate Pages",
                "granite.maintenance.isStoppable:Boolean=true",
                "granite.maintenance.mandatory:Boolean=true",
                "windowSchedule=weekly",
                "service.description =Task to activate pages/assets",
                "service.vendor=Isobar"
        }
)
@Designate(ocd = ActivatePages.Config.class)
public final class ActivatePages implements JobExecutor {

    /**
     *  OSGI Configurations.
     */
    @ObjectClassDefinition (name = "Job Configuration",
                            description = "This is configuration for Job")
    public static @interface Config {


        /**
         *  Resource paths.
         *  @return  list resource paths to be flushed
         */
        @AttributeDefinition(
                name = "Paths to activate",
                description = "Enter resource paths"
        )
        String[] resource_paths() default
                {"/content/swinburne-showcase", "/content/swinburne"};


        /**
         *  Replication agent id.
         *  @return  replication agent id (for example "publish")
         */
        @AttributeDefinition(
                name = "Agent ID",
                description = "Enter replication agent id"
        )
        String agent_id() default "publish";

    }

    /**
     *  Default logger.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *  List of resource paths for activation.
     */
    private String[] resourcePaths;

    /**
     * Replication agent id.
     */
    private String agentID;

    /**
     *  Replicator service.
     */
    @Reference
    private Replicator replicator;

    /**
     *  Resource Resolver service.
     */
    @Reference
    private ResourceResolverFactory resolverFactory;

    /**
     *  Default constructor.
     */
    public ActivatePages() {
    }

    /**
     * The entry point when the bundle is started.
     *
     * @param  bundleContext object created at the start of bundle
     * @param  configuration bundle configuration object
     */
    @Activate
    protected void activate(final BundleContext bundleContext,
                            final ActivatePages.Config configuration) {

        this.resourcePaths = configuration.resource_paths();
        this.agentID = configuration.agent_id();
    }

    /**
     * Execute the job.
     * <p>
     * Activates resources provided in the configuration.
     * This method uses replication agent id.
     *
     * @param  job  an absolute URL giving the base location of the image
     * @param  context the location of the image, relative to the url argument
     */
    @Override
    public JobExecutionResult process(final Job job,
                                      final JobExecutionContext context) {

        JobExecutionResult result = null;

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(ResourceResolverFactory.SUBSERVICE, "replicate-service");


        try {
            ResourceResolver adminResolver =
                    resolverFactory.getServiceResourceResolver(param);

            Session adminSession = adminResolver.adaptTo(Session.class);
            if (replicator != null) {
                ReplicationOptions options = new ReplicationOptions();

                options.setFilter(new AgentFilter() {
                    public boolean isIncluded(final Agent agent) {

                        // the name of your  replication agent (i.e. publish)
                        return agent.getId().equals(agentID);
                    }
                });

                for (String pagePath : resourcePaths) {

                    // activating pages
                    replicator.replicate(adminSession,
                            ReplicationActionType.ACTIVATE, pagePath, options);

                    logger.info("Activating Page >>{}", pagePath);

                    result = context.result().message(String.
                            format("Activated pages.")).succeeded();
                }
            }

        } catch (Exception e) {

            logger.error("Job execution failed:: {}", e.getMessage());

            // send failure message to aem console
            result = context.result().message(String.format("ERROR")).failed();
        }
        return result;

    }

    /**
     * The entry point when the bundle is stopped.
     *
     * @param  bundleContext object created at the start of bundle
     */
    @Deactivate
    protected void deactivate(final BundleContext bundleContext) {
    }

}
