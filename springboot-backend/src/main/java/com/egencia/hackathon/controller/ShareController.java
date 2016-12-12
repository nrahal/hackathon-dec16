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
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping(value = "/share", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
public class ShareController {

	private final static Logger LOGGER = LoggerFactory.getLogger(ShareController.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

	@RequestMapping(value = "/fb/{payload}/{bust}", method = RequestMethod.GET)
	public String generateFacebookPage(
	        @PathVariable String payload,
            Model model) throws IOException {

        LOGGER.info("Base64 Payload: {}", payload);

        String json = new String(Base64.getDecoder().decode(payload));
        LOGGER.info("JSON Payload: {}", json);

        TripMetaData tripMetaData = objectMapper.readValue(json, TripMetaData.class);

        model.addAttribute("title", "Mon voyage à " + tripMetaData.getDestination() + " avec Egencia");
        model.addAttribute("description", "Mon voyage à " + tripMetaData.getDestination() + " avec Egencia");
        model.addAttribute("currentUrl", "http://54.171.123.75/gc/share/fb");

        LOGGER.info("Model: {}", model);

		return "fb";
	}

}
