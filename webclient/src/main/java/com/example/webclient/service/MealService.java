package com.example.webclient.service;

import com.example.webclient.presentation.dto.api.OpenApiDataDto;

public interface MealService {

    OpenApiDataDto getAllMeal(String year, String month, String day);

    String mealUriBuild(String year, String month, String day);
}
