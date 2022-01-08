package com.example.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratingsdataservice.model.Rating;
import com.example.ratingsdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

	@RequestMapping("{movieId}")
	public Rating getRating(@PathVariable("movieId") int movieId) {	
		return new Rating(movieId, 4);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getRating(@PathVariable("userId") int userId, String users) {	
		List<Rating> ratings =  Arrays.asList(
				new Rating(1234, 4),
				new Rating(2345, 3)
				);
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
	}
}
