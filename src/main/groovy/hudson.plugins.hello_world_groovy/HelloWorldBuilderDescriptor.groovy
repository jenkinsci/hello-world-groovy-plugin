package hudson.plugins.hello_world_groovy

import hudson.Extension
import hudson.model.Descriptor.FormException
import hudson.plugins.hello_world_groovy.HelloWorldBuilder
import hudson.tasks.BuildStepDescriptor
import hudson.tasks.Builder
import net.sf.json.JSONObject
import org.kohsuke.stapler.StaplerRequest
import hudson.model.AbstractProject

/**
* Descriptor for {@link HelloWorldBuilder}.
* The class is marked as public so that it can be accessed from views.
*
* <p>
* See <tt>views/hudson/plugins/hello_world/HelloWorldBuilder/*.jelly</tt>
* for the actual HTML fragment for the configuration screen.
*/
// this annotation tells Hudson that this is the implementation of an extension point
@Extension
public final class HelloWorldBuilderDescriptor extends BuildStepDescriptor<Builder> {
    /**
     * To persist global configuration information,
     * simply store it in a field and call save().
     *
     * <p>
     * If you don't want fields to be persisted, use <tt>transient</tt>.
     */
    private boolean useFrench;

    public HelloWorldBuilderDescriptor() {
        super(HelloWorldBuilder.class)
        load();
    }

    /**
     * This human readable name is used in the configuration screen.
     */
    @Override
    public String getDisplayName() {
        return "Say hello world";
    }

    /**
     * Applicable to any kind of project.
     */
    @Override
    public boolean isApplicable(Class<? extends AbstractProject> type) {
        return true;
    }

    @Override
    public boolean configure(StaplerRequest staplerRequest, JSONObject json) throws FormException {
        // to persist global configuration information,
        // set that to properties and call save().
        useFrench = json.getBoolean("useFrench");
        save();
        return true; // indicate that everything is good so far
    }

    /**
     * This method returns true if the global configuration says we should speak French.
     */
    public boolean useFrench() {
        return useFrench;
    }
}
