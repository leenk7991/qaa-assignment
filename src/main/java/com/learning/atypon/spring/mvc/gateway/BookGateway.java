package com.learning.atypon.spring.mvc.gateway;

import com.learning.atypon.spring.mvc.domain.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BookGateway {
    @Value("${connection.url}")
    private String host = "http://localhost:";

    @Value("${server.port}")
    private int serverPort = 8080;

    private String BASE_URL = host + serverPort + "/books";

    @Autowired
    RestTemplate restTemplate;

    public BookDTO findById(Long bookId) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(BASE_URL);
                //.queryParam("bookId", bookId);

        System.out.println(builder.toUriString());

        BookDTO result = restTemplate.getForObject(BASE_URL.concat("/").concat(bookId.toString()), BookDTO.class);

        System.out.println(result);

        return result;
    }

    public void create(BookDTO bookDTO) {
        restTemplate.postForObject(BASE_URL, bookDTO, BookDTO.class);

    }
}
