package com.example.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.moviecatalogservice.model.CatalogItem;
import com.example.moviecatalogservice.model.Movie;
import com.example.moviecatalogservice.model.Rating;
import com.example.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, 
								UserRating.class);
		
		return ratings.getUserRating().stream().map(rating -> {
			//for each movie id call movie info service and get details
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);

			//put them all together
			return new CatalogItem(movie.getName(), "Test", rating.getRating());
		}).collect(Collectors.toList());
		
	}
}

/*
 * @Autowired private WebClient.Builder webClientBuilder;
 */
/*
 * Movie movie = webClientBuilder.build() .get()
 * .uri("http://localhost:8082/movies/"+ rating.getMovieId()) .retrieve()
 * .bodyToMono(Movie.class) .block();
 */
