package server.controller;

import static java.lang.Integer.parseInt;
import static server.api.EmissionApi.getDietEmission;
import static server.api.EmissionApi.getEnergyEmission;
import static server.api.EmissionApi.getFlightEmission;
import static server.api.EmissionApi.getTrainEmission;
import static server.api.EmissionApi.getVehicleEmission;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;
import server.model.DietEmission;
import server.model.EnergyEmission;
import server.model.FlightEmission;
import server.model.TrainEmission;
import server.model.VehicleEmission;

import java.util.HashMap;

/**
 * The type Emission controller.
 */
@RestController
public class EmissionController {

    /**
     * Vehicle emission response entity.
     *
     * @param parameters the parameters
     * @return the response entity
     */
    @PostMapping(value = "/vehicleEmission",
        produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<VehicleEmission> vehicleEmission(@RequestBody HashMap parameters) {

        return new ResponseEntity<VehicleEmission>(getVehicleEmission(
            (int) parseInt((String) parameters.get("daily_distance")),
            (int) parseInt((String) parameters.get("daily_duration")),
            (String) parameters.get("sizeClass")
        ), HttpStatus.OK);
    }

    /**
     * Energy emission response entity.
     *
     * @param parameters the parameters
     * @return the response entity
     */
    @PostMapping(value = "/energyEmission",
        produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<EnergyEmission> energyEmission(@RequestBody HashMap parameters) {

        return new ResponseEntity<EnergyEmission>(getEnergyEmission(
            (int) parseInt((String) parameters.get("greenEnergy")),
            (String) parameters.get("airConditionerUse"),
            (String) parameters.get("dishwasherUse"),
            (int) parseInt((String) parameters.get("naturalGasCost"))
        ), HttpStatus.OK);
    }

    /**
     * Flight emission response entity.
     *
     * @param parameters the parameters
     * @return the response entity
     */
    @PostMapping(value = "/flightEmission",
        produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<FlightEmission> flightEmission(@RequestBody HashMap parameters) {

        return new ResponseEntity<FlightEmission>(getFlightEmission(
            (String) parameters.get("startingPort"),
            (String) parameters.get("endingPort")
        ), HttpStatus.OK);
    }

    /**
     * Diet emission response entity.
     *
     * @param parameters the parameters
     * @return the response entity
     */
    @PostMapping(value = "/dietEmission",
        produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<DietEmission> dietEmission(@RequestBody HashMap parameters) {

        return new ResponseEntity<DietEmission>(getDietEmission(
            Float.parseFloat((String)parameters.get("fishShare")),
            Float.parseFloat((String)parameters.get("redMeatShare")),
            Float.parseFloat((String)parameters.get("poultryShare")),
            Integer.parseInt((String)parameters.get("size"))
        ), HttpStatus.OK);
    }

    /**
        * Train emission response entity.
        *
        * @param parameters the parameters
     * @return the response entity
     */
    @PostMapping(value = "/trainEmission",
        produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<TrainEmission> trainEmission(@RequestBody HashMap parameters) {

        return new ResponseEntity<TrainEmission>(getTrainEmission(
            (int) parseInt((String) parameters.get("distance")),
            (int) parseInt((String) parameters.get("duration"))
        ), HttpStatus.OK);
    }
}
