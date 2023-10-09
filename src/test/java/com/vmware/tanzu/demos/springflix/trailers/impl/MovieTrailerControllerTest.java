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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.time.LocalDate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 0)
class MovieTrailerControllerTest {
    @Autowired
    private TestRestTemplate client;

    @Test
    void testGetMovieTrailers() {
        stubFor(get(urlEqualTo("/3/movie/466420/videos"))
                .willReturn(aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("""
                                {
                                  "id": 466420,
                                  "results": [
                                    {
                                      "iso_639_1": "en",
                                      "iso_3166_1": "US",
                                      "name": "Ernest Burkhart",
                                      "key": "vqTF_cdtu7I",
                                      "site": "YouTube",
                                      "size": 1080,
                                      "type": "Featurette",
                                      "official": true,
                                      "published_at": "2023-10-02T12:59:48.000Z",
                                      "id": "651ac0ae74507d00ff964e80"
                                    },
                                    {
                                      "iso_639_1": "en",
                                      "iso_3166_1": "US",
                                      "name": "\\"Handsome Devil\\" Clip",
                                      "key": "Yb4mgjfs3Rs",
                                      "site": "YouTube",
                                      "size": 1080,
                                      "type": "Clip",
                                      "official": true,
                                      "published_at": "2023-09-28T14:59:55.000Z",
                                      "id": "6515a03a9b8616011c47da0d"
                                    },
                                    {
                                      "iso_639_1": "en",
                                      "iso_3166_1": "US",
                                      "name": "Greed. Power. Betrayal.",
                                      "key": "n_zN0KKl7yI",
                                      "site": "YouTube",
                                      "size": 1080,
                                      "type": "Teaser",
                                      "official": true,
                                      "published_at": "2023-09-27T12:30:47.000Z",
                                      "id": "6514803693bd6900acc53ccd"
                                    },
                                    {
                                      "iso_639_1": "en",
                                      "iso_3166_1": "US",
                                      "name": "An Inside Look",
                                      "key": "3YuLJXRSn2k",
                                      "site": "YouTube",
                                      "size": 1080,
                                      "type": "Behind the Scenes",
                                      "official": true,
                                      "published_at": "2023-09-25T13:59:56.000Z",
                                      "id": "6511ea21e1faed00e3f72343"
                                    },
                                    {
                                      "iso_639_1": "en",
                                      "iso_3166_1": "US",
                                      "name": "Scorsese. DiCaprio. De Niro. Gladstone.",
                                      "key": "myGT3mocWE0",
                                      "site": "YouTube",
                                      "size": 1080,
                                      "type": "Teaser",
                                      "official": true,
                                      "published_at": "2023-09-25T12:00:41.000Z",
                                      "id": "651b38a9c50ad2012c1a1922"
                                    },
                                    {
                                      "iso_639_1": "en",
                                      "iso_3166_1": "US",
                                      "name": "The deeper you go, the darker it gets.",
                                      "key": "4-Y3kBiq8qE",
                                      "site": "YouTube",
                                      "size": 1080,
                                      "type": "Teaser",
                                      "official": true,
                                      "published_at": "2023-09-24T03:30:01.000Z",
                                      "id": "651b38b6c50ad200eabf4644"
                                    },
                                    {
                                      "iso_639_1": "en",
                                      "iso_3166_1": "US",
                                      "name": "DiCaprio’s best role yet!",
                                      "key": "Or5vYmBzJsk",
                                      "site": "YouTube",
                                      "size": 1080,
                                      "type": "Teaser",
                                      "official": true,
                                      "published_at": "2023-09-22T12:00:22.000Z",
                                      "id": "650df73d2b531d013b7fc4c9"
                                    },
                                    {
                                      "iso_639_1": "en",
                                      "iso_3166_1": "US",
                                      "name": "Scorsese’s most innovative and best movie in decades",
                                      "key": "hemfPXxEfPY",
                                      "site": "YouTube",
                                      "size": 1080,
                                      "type": "Teaser",
                                      "official": true,
                                      "published_at": "2023-09-20T06:53:27.000Z",
                                      "id": "650b538dcadb6b00e11fc062"
                                    },
                                    {
                                      "iso_639_1": "en",
                                      "iso_3166_1": "US",
                                      "name": "Official Trailer 2",
                                      "key": "f1oqFhyjebw",
                                      "site": "YouTube",
                                      "size": 1080,
                                      "type": "Trailer",
                                      "official": true,
                                      "published_at": "2023-09-13T12:59:57.000Z",
                                      "id": "6501c3436a222700e0f2da7d"
                                    },
                                    {
                                      "iso_639_1": "en",
                                      "iso_3166_1": "US",
                                      "name": "Official Trailer",
                                      "key": "EP34Yoxs3FQ",
                                      "site": "YouTube",
                                      "size": 1080,
                                      "type": "Trailer",
                                      "official": true,
                                      "published_at": "2023-07-05T12:59:51.000Z",
                                      "id": "64a56f96a0be28014fa6fb65"
                                    },
                                    {
                                      "iso_639_1": "en",
                                      "iso_3166_1": "US",
                                      "name": "Official Teaser Trailer",
                                      "key": "EG0si5bSd6I",
                                      "site": "YouTube",
                                      "size": 1080,
                                      "type": "Trailer",
                                      "official": true,
                                      "published_at": "2023-05-18T13:14:58.000Z",
                                      "id": "646626e3c3514c0100fce1aa"
                                    }
                                  ]
                                }
                                """)));

        final var mt1 = new MovieTrailer("466420", URI.create("https://www.youtube.com/watch?v=f1oqFhyjebw"), LocalDate.of(2023, 9, 13));
        final var mt2 = new MovieTrailer("466420", URI.create("https://www.youtube.com/watch?v=EP34Yoxs3FQ"), LocalDate.of(2023, 7, 5));
        final var mt3 = new MovieTrailer("466420", URI.create("https://www.youtube.com/watch?v=EG0si5bSd6I"), LocalDate.of(2023, 5, 18));

        final var resp = client.getForEntity("/api/v1/trailers/466420", MovieTrailerController.MovieTrailers.class).getBody();
        assertThat(resp).isNotNull();
        assertThat(resp.id()).isEqualTo("466420");
        assertThat(resp.trailers()).containsExactlyInAnyOrder(
                new MovieTrailerController.MovieTrailers.Item(mt1.videoUri(), mt1.publishedAt()),
                new MovieTrailerController.MovieTrailers.Item(mt2.videoUri(), mt2.publishedAt()),
                new MovieTrailerController.MovieTrailers.Item(mt3.videoUri(), mt3.publishedAt())
        );
    }
}
