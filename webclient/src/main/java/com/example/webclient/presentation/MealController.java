package com.example.webclient.presentation;

import com.example.webclient.presentation.dto.api.OpenApiDataDto;
import com.example.webclient.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meal")
public class MealController {

    private final MealService mealService;

    @GetMapping
    public ResponseEntity<OpenApiDataDto> getAllMeal(
            @RequestParam String year,
            @RequestParam String month,
            @RequestParam String day
    ) {
        return new ResponseEntity<>(mealService.getAllMeal(year, month, day), HttpStatus.OK);
    }

}
