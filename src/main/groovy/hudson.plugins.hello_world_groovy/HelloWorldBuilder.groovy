package hudson.plugins.hello_world_groovy

import hudson.Launcher
import hudson.model.AbstractBuild
import hudson.model.BuildListener
import hudson.tasks.Builder
import org.kohsuke.stapler.DataBoundConstructor

public class HelloWorldBuilder extends Builder {
    final String name;

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
