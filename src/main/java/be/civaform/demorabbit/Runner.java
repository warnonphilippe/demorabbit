package be.civaform.demorabbit;


import be.civaform.demorabbit.dto.DemoDTO;
import be.civaform.demorabbit.producer.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
    public class Runner implements CommandLineRunner {

        @Autowired
        private MessageSender producer;

        @Autowired
        private ConfigurableApplicationContext context;

        @Override
        public void run(String... args) throws Exception {
            testDemo();
        }

        private void testDemo(){
            TenantContext.setCurrentApplication("testapp");
            TenantContext.setCurrentTenant("jhipster");

            Arrays.asList(1, 2, 3, 4).stream()
                    .map(i -> createDemoDTO(i))
                    .forEach(dto -> {
                                try {
                                    producer.envoyerMessageDemo(dto);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                    );

            // simulation d'envois de message pour le tenant jhipster2
            TenantContext.setCurrentTenant("jhipster2");
            DemoDTO dto5 = createDemoDTO(5);
            producer.envoyerMessageDemo(dto5);
        }

        private DemoDTO createDemoDTO(int idx){
            return new DemoDTO(idx, "Message nr " + idx);
        }

}
