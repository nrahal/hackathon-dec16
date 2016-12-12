package com.egencia.hackathon.controller;

import com.egencia.hackathon.model.PublicationPageModel;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Base64;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/share", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
public class ShareController {

	private final static Logger LOGGER = LoggerFactory.getLogger(ShareController.class);

	@RequestMapping(value = "/facebook/{tripMetaDataBase64}", method = RequestMethod.GET)
	public ModelAndView generateFacebookPage(@PathVariable String tripMetaDataBase64) throws IOException {

		String tripMetaDataJson = new String(Base64.getDecoder().decode(tripMetaDataBase64));
		TripMetaData tripMetaData = new ObjectMapper().readValue(tripMetaDataJson, TripMetaData.class);
		PublicationPageModel publicationPageModel = buildPublication(tripMetaData);

		return new ModelAndView("facebook", "publication", publicationPageModel);
	}

	private PublicationPageModel buildPublication(TripMetaData tripMetaData) {
		PublicationPageModel model = new PublicationPageModel();

		model.setDestinationName(tripMetaData.getDestination());

		return model;
	}



	@RequestMapping("/fb/{payload}/{bust}")
	public String greeting(
                           @RequestParam(value="payload", required=false, defaultValue="World") String payload,
                           @RequestParam(value="bust", required=false) String bust,
                           Model model) {
		model.addAttribute("currentUrl", "http://54.171.123.75/gc/share/fb");
		return "fb";
	}

}
