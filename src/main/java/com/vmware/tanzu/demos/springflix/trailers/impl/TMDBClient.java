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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@HttpExchange(accept = MediaType.APPLICATION_JSON_VALUE)
interface TMDBClient {
    @GetExchange("3/movie/{movieId}/videos")
    TResponse getMovieTrailers(@PathVariable(value = "movieId") String movieId);

    record TResponse(
            List<TMovieTrailer> results
    ) {
    }

    record TMovieTrailer(
            @JsonProperty(required = true)
            String type,
            @JsonProperty(required = true)
            String key,
            @JsonProperty(required = true)
            String site,
            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            @JsonProperty(required = true, value = "published_at")
            LocalDateTime publishedAt
    ) {
    }
}
