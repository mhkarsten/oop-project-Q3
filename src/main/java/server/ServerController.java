package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class ServerController {

    @Autowired
    private UserDAO userDAO;

    //Initial Connect
    @RequestMapping("/")
    @ResponseBody
    public String connect() {

        return "You are connected";
    }

    //Gets for user info
    @RequestMapping(value = "/user",//
        method )


    //Get for CO2

    //Get for achieves


    //Get for login
}
