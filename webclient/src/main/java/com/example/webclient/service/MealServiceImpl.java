package com.example.webclient.service;

import com.example.webclient.presentation.dto.api.OpenApiDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final UriBuilder uriBuilder;
    private final WebClient webClient;


    @Override
    public OpenApiDataDto getAllMeal(String year, String month, String day) {
        return webClient.get()
                .uri(mealUriBuild(year, month, day))
                .retrieve()
                .bodyToMono(OpenApiDataDto.class)
                .block()
                .regeneration();
    }

    @Override
    public String mealUriBuild(String year, String month, String day) {

        return uriBuilder
                .queryParam("year", year)
                .queryParam("month",month)
                .queryParam("day", day)
                .build()
                .toString();
    }

}
