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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping(value = "/share", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
public class ShareController {

	private final static Logger LOGGER = LoggerFactory.getLogger(ShareController.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/fb/{payload}", method = RequestMethod.GET)
	public String generateFacebookPage(
	        @PathVariable String payload,
            Model model) throws IOException {

        LOGGER.info("Facebook - Base64 Payload: {}", payload);
        TripMetaData tripMetaData = fromPayload(payload);
        String imageUrl = imageUrlOf(tripMetaData);

        model.addAttribute("title", "My trip to " + tripMetaData.getDestination());
        model.addAttribute("description", "Booked with Egencia");
        model.addAttribute("imageUrl", imageUrl);

        LOGGER.info("Model: {}", model);

		return "fb";
	}

    @RequestMapping(value = "/ln/{payload}", method = RequestMethod.GET)
    @ResponseBody
    public LinkedinData generateLinkedinData(
            @PathVariable String payload) throws IOException {

        LOGGER.info("Linkedin - Base64 Payload: {}", payload);
        TripMetaData tripMetaData = fromPayload(payload);
        String imageUrl = imageUrlOf(tripMetaData);

        LinkedinData data = new LinkedinData();
        data.comment = "My trip to " + tripMetaData.getDestination();
        data.content.description = "Booked with Egencia";
        data.content.submittedUrl = "http://www.egencia.fr";
        data.content.submittedImageUrl = imageUrl;

        LOGGER.info("Lindedin Data: {}", objectMapper.writeValueAsString(data));

        return data;
    }

    private TripMetaData fromPayload(String payload) throws IOException {
        String json = new String(Base64.getDecoder().decode(payload));
        LOGGER.info("JSON Payload: {}", json);
        return objectMapper.readValue(json, TripMetaData.class);
    }

    private String imageUrlOf(TripMetaData tripMetaData) {
        FlickrResponse flickrResponse = restTemplate.getForObject(
                "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=a69bcf92807b072b8a3f899d46663387&text={destination}&tags=skyline&per_page=1&page=1&sort=relevance&format=json&nojsoncallback=1",
                FlickrResponse.class,
                tripMetaData.getDestination());
        FlickrResponse.Photo photo = flickrResponse.photos.photo.get(0);
        return "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret + ".jpg";
    }
}
