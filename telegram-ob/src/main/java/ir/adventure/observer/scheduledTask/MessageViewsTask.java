package ir.adventure.observer.scheduledTask;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by jalil on 12/11/2017.
 */
@Component
public class MessageViewsTask extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:get-views")
                .bean("telegramClients", "getViews");

        from("timer:get-views?period={{observer.period.allChannel}}")
                .to("direct:get-views");
    }
}
