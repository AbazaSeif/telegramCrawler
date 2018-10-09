package ir.adventure.observer.scheduledTask;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by jalil on 12/11/2017.
 */
@Component
public class MessagesTask extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:get-specified-channels-estimate24")
                .bean("telegramClients", "getEstimateView24");

        from("timer:load-new-observers?period={{observer.estimate24.tryDelay}}")
                .to("direct:get-specified-channels-estimate24");
    }
}
