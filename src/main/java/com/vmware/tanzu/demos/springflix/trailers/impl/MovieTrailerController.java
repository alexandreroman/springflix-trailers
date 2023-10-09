/*
 * Copyright (c) 2023 VMware, Inc. or its affiliates
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vmware.tanzu.demos.springflix.trailers.impl;

import com.vmware.tanzu.demos.springflix.trailers.model.MovieTrailerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@RestController
class MovieTrailerController {
    private final Logger logger = LoggerFactory.getLogger(MovieTrailerController.class);
    private final MovieTrailerService movieTrailerService;

    MovieTrailerController(MovieTrailerService movieTrailerService) {
        this.movieTrailerService = movieTrailerService;
    }

    @GetMapping(value = "/api/v1/trailers/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MovieTrailers> getMovieTrailers(@PathVariable(value = "movieId") String movieId) {
        logger.info("Looking up trailers for movie: {}", movieId);

        final var trailers = movieTrailerService.getMovieTrailers(movieId)
                .stream().map(t -> new MovieTrailers.Item(t.videoUri(), t.publishedAt())).toList();
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(Duration.ofMinutes(10)))
                .body(new MovieTrailers(movieId, trailers));
    }

    record MovieTrailers(String id, List<Item> trailers) {
        record Item(URI videoUri, LocalDate publishedAt) {
        }
    }
}
