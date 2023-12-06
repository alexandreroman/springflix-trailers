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

import com.vmware.tanzu.demos.springflix.trailers.model.MovieTrailer;
import com.vmware.tanzu.demos.springflix.trailers.model.MovieTrailerService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
class TMDBTrailerService implements MovieTrailerService {
    private static final URI YOUTUBE_BASE_URI = URI.create("https://www.youtube.com/watch");
    private final TMDBClient client;
    private final ObservationRegistry observationRegistry;

    TMDBTrailerService(TMDBClient client, ObservationRegistry observationRegistry) {
        this.client = client;
        this.observationRegistry = observationRegistry;
    }

    @Override
    public List<MovieTrailer> getMovieTrailers(String movieId) {
        return Observation.createNotStarted("tmdb.movieTrailers", observationRegistry)
                .highCardinalityKeyValue("movie", movieId)
                .observe(() -> doGetMovieTrailers(movieId));
    }

    private List<MovieTrailer> doGetMovieTrailers(String movieId) {
        return client.getMovieTrailers(movieId).results().stream()
                .filter(t -> "Trailer".equals(t.type()))
                .filter(t -> "YouTube".equals(t.site()))
                .map(t -> new MovieTrailer(movieId,
                        UriComponentsBuilder.fromUri(YOUTUBE_BASE_URI).queryParam("v", t.key()).build().toUri(),
                        t.publishedAt().toLocalDate()))
                .toList();
    }
}
