package com.egencia.hackathon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
