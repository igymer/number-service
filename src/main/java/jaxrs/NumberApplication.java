package jaxrs;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.*;

@ApplicationPath("/api")
public class NumberApplication extends Application {

    public NumberApplication() {
        super();
    }

    @Override
    public Set<Class<?>> getClasses() {
        return Collections.singleton(NumberRestService.class);
    }
}
