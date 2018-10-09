package ir.adventure.observer.scheduledTask;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by jalil on 12/11/2017.
 */
@Component
public class ObserverTask extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct:load-new-observers")
                .bean("telegramClients", "loadObservers");

        from("timer:load-new-observers?period={{observer.loadDelay}}")
                .to("direct:load-new-observers");
    }
}
