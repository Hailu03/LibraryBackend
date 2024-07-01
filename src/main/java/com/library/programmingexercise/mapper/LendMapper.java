package com.library.programmingexercise.mapper;

import com.library.programmingexercise.dto.LendDto;

import java.time.LocalDate;

public class LendMapper {
    public static LendDto mapToLendDto(Object[] lend){
        return new LendDto(
                (Integer) lend[0],
                (Integer) lend[1],
                (String) lend[2],
                (String) lend[3],
                (String) lend[4],
                (LocalDate) lend[5],
                (LocalDate) lend[6]
        );
    }
}