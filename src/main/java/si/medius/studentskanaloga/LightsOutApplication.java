package si.medius.studentskanaloga;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;
@OpenAPIDefinition(
        info = @Info(
                title="Lights Out API",
                version = "1.0.0",
                contact = @Contact(
                        name = "GitHub page",
                        url = "https://github.com/tjasad/"))
)
public class LightsOutApplication extends Application {


}
