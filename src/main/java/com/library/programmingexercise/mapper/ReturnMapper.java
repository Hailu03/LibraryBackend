package com.library.programmingexercise.mapper;

import com.library.programmingexercise.dto.ReturnDto;

import java.time.LocalDate;

public class ReturnMapper {
    public static ReturnDto mapToReturnDto(Object[] returnBook){
        return new ReturnDto(
                (Integer) returnBook[0],
                (Integer) returnBook[1],
                (Integer) returnBook[2],
                (String) returnBook[3],
                (String) returnBook[4],
                (String) returnBook[5],
                (LocalDate) returnBook[6]
        );
    }
}