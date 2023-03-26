package com.example.webclient.presentation.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OpenApiDataDto {

    @JsonProperty("data")
    private OpenApiInfoDto openApiInfoDto;

    public OpenApiDataDto regeneration() {
        this.openApiInfoDto = new OpenApiInfoDto(openApiInfoDto);
        return this;
    }

}
