package server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import server.api.EmissionApi;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmissionApiTest {

    @Test
    void keyStringBuilder() {
        String key = "key=5a927d96eca397b6659a3c361ce32254";
        int test1 = 123;
        String test2 = "&oneTwoThree=" + test1;
        int test3 = 0;
        String test4 = "&zero=" + test3;
        String test5 = null;
        String test6 = "&City=" + test5;

        StringBuilder endResult = new StringBuilder(key + test2);
        ArrayList<ArrayList<Object>> test = new ArrayList<ArrayList<Object>>() {
            {
                add(new ArrayList<Object>() {
                    {
                        add(test1);
                        add(test2);
                    }
                });
                add(new ArrayList<Object>() {
                    {
                        add(test3);
                        add(test4);
                    }
                });
                add(new ArrayList<Object>() {
                    {
                        add(test5);
                        add(test6);
                    }
                });
            }
        };
        Assertions.assertEquals(endResult.toString(), EmissionApi.keyStringBuilder(test).toString());

    }

    @Test
    void getVehicleEmission() {
    }

    @Test
    void getEnergyEmission() {
    }

    @Test
    void getDietEmission() {
    }

    @Test
    void getFlightEmission() {
    }
}