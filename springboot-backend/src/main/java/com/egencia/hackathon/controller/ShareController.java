package com.egencia.hackathon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/share", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
public class ShareController {

	private final static Logger LOGGER = LoggerFactory.getLogger(ShareController.class);

	@ResponseBody
	@RequestMapping(value = "/trips", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Void> process(@RequestBody(required = true) final TripMetaData metaData) {

		LOGGER.info("Sharing trip metadata : {}", metaData);
		return new ResponseEntity<>(HttpStatus.OK);

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
