package com.example.webclient.presentation.dto.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OpenApiInfoDto {
    private String date;
    private String breakfast;

    private String lunch;
    private String dinner;

    private String week;

    public OpenApiInfoDto(OpenApiInfoDto data) {
        final SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.date = data.getDate();
        this.breakfast = data.getBreakfast();
        this.dinner = data.getDinner();
        this.lunch = data.getLunch();
        final Date parsedDate;
        try {
            parsedDate = parseFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 E요일");
        this.week = dateFormat.format(parsedDate);
    }

}
