package hudson.plugins.hello_world_groovy

import hudson.Launcher
import hudson.model.AbstractBuild
import hudson.model.BuildListener
import hudson.tasks.Builder
import org.kohsuke.stapler.DataBoundConstructor
import org.kohsuke.stapler.StaplerRequest;

/**
 * Sample {@link Builder}.
 *
 * <p>
 * When the user configures the project and enables this builder,
 * {@link HelloWorldBuilderDescriptor#newInstance(StaplerRequest)} is invoked
 * and a new {@link HelloWorldBuilder} is created. The created
 * instance is persisted to the project configuration XML by using
 * XStream, so this allows you to use instance fields (like {@link #name})
 * to remember the configuration.
 *
 * <p>
 * When a build is performed, the {@link #perform(AbstractBuild,Launcher,BuildListener)} method
 * will be invoked.
 *
 * @author Kohsuke Kawaguchi
 */
public class HelloWorldBuilder extends Builder {
    /**
     * We'll use this from the <tt>config.jelly</tt>.
     */
    final String name;

    /**
     * This annotation tells Hudson to call this constructor, with
     * values from the configuration form page with matching parameter names.
     */
    @DataBoundConstructor
    public HelloWorldBuilder(String name) {
        this.name = name;
    }

    @Override
    public boolean perform(AbstractBuild build, Launcher launcher, BuildListener listener) {
        if(descriptor.useFrench())
            listener.getLogger().println("Bonjour, ${name}!");
        else
            listener.getLogger().println("Hello, ${name}!");
        return true;
    }
}
