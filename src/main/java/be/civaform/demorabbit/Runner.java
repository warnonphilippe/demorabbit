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
            //testDemo2();
            //testDemo3();
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
            dto5.setErreur(true);
            producer.envoyerMessageDemo(dto5);
        }

    private void testDemo2(){
        TenantContext.setCurrentApplication("testapp");
        TenantContext.setCurrentTenant("jhipster");

        Arrays.asList(1, 2, 3, 4).stream()
                .map(i -> createDemoDTO(i))
                .forEach(dto -> {
                            try {
                                producer.envoyerMessageDemo2(dto);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );

        // simulation d'envois de message pour le tenant jhipster2
        TenantContext.setCurrentTenant("jhipster2");
        DemoDTO dto5 = createDemoDTO(5);
        producer.envoyerMessageDemo2(dto5);
    }

    private void testDemo3(){
        TenantContext.setCurrentApplication("testapp");
        TenantContext.setCurrentTenant("jhipster");

        Arrays.asList(1, 2).stream()
                .map(i -> createDemoDTO(i))
                .forEach(dto -> {
                            try {
                                producer.envoyerMessageDemo3Key1(dto);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );

        Arrays.asList(3, 4).stream()
                .map(i -> createDemoDTO(i))
                .forEach(dto -> {
                            try {
                                producer.envoyerMessageDemo3Key2(dto);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );

        // simulation d'envois de message pour le tenant jhipster2
        TenantContext.setCurrentTenant("jhipster2");
        DemoDTO dto5 = createDemoDTO(5);
        producer.envoyerMessageDemo3BadKey(dto5);
    }

        private DemoDTO createDemoDTO(int idx){
            return new DemoDTO(idx, "Message nr " + idx);
        }

}
