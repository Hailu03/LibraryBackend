package com.library.programmingexercise.mapper;

import com.library.programmingexercise.dto.ReturnDto;
import com.library.programmingexercise.dto.ReviewBookDto;

import java.time.LocalDate;

public class ReviewBookMapper {
    public static ReviewBookDto mapToReviewDto(Object[] review){
        return new ReviewBookDto((LocalDate) review[0], (int) review[1], (String) review[2], (int) review[3], (int) review[4]);
    }
}
