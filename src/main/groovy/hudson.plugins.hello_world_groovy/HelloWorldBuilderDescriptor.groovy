package hudson.plugins.hello_world_groovy

import hudson.Extension
import hudson.model.Descriptor.FormException
import hudson.plugins.hello_world_groovy.HelloWorldBuilder
import hudson.tasks.BuildStepDescriptor
import hudson.tasks.Builder
import net.sf.json.JSONObject
import org.kohsuke.stapler.StaplerRequest
import hudson.model.AbstractProject

// Groovy doesn't support the nested classes, so the descriptor needs to be outside,
// and that means the constructor needs the type argument
@Extension
public final class HelloWorldBuilderDescriptor extends BuildStepDescriptor<Builder> {
    private boolean useFrench;

    public HelloWorldBuilderDescriptor() {
        super(HelloWorldBuilder.class)
        load();
    }

    public String getDisplayName() {
        return "Say hello world";
    }

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

    public boolean useFrench() {
        return useFrench;
    }
}
