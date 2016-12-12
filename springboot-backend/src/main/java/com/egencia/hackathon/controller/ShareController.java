package com.egencia.hackathon.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping(value = "/share", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
public class ShareController {

	private final static Logger LOGGER = LoggerFactory.getLogger(ShareController.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/fb/{payload}/{bust}", method = RequestMethod.GET)
	public String generateFacebookPage(
	        @PathVariable String payload,
            @PathVariable String bust,
            Model model) throws IOException {
        TripMetaData tripMetaData;

    try {
        LOGGER.info("Base64 Payload: {}", payload);

        String json = new String(Base64.getDecoder().decode(payload));
        LOGGER.info("JSON Payload: {}", json);

        tripMetaData = objectMapper.readValue(json, TripMetaData.class);

        FlickrResponse flickrResponse = restTemplate.getForObject(
                "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=a69bcf92807b072b8a3f899d46663387&text={destination}&per_page=1&page=1&format=json&nojsoncallback=1",
                FlickrResponse.class,
                tripMetaData.getDestination());
    }
    catch (Exception e){
        LOGGER.error("ARGGFHH",e);
        //Fake for dev only
        tripMetaData = new TripMetaData();
        tripMetaData.setDestination("TOUR EGEE");

    }
        model.addAttribute("title", "Mon voyage à " + tripMetaData.getDestination() + " avec Egencia");
        model.addAttribute("description", "Mon voyage à " + tripMetaData.getDestination() + " avec Egencia");

        model.addAttribute("currentUrl", "https://54.171.123.75/gc/share/fb/"+payload+"/"+bust);

        model.addAttribute("img_url","https://54.171.123.75/gc/static/Paris.jpg");
        LOGGER.info("Model: {}", model);

		return "fb";
	}

}
