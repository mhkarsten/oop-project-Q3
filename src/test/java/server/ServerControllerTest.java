package server;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import server.controller.ServerController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerControllerTest {
    @Autowired
    private ServerController controller;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(controller);
    }
}
