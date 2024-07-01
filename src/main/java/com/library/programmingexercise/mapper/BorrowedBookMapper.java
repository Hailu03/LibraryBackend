package com.library.programmingexercise.mapper;

import com.library.programmingexercise.dto.BorrowedBookDto;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowedBookMapper {
    public static BorrowedBookDto mapToBorrowedBookDto(Object[] borrowedBook){
        return new BorrowedBookDto(
                (Integer) borrowedBook[0],
                (String) borrowedBook[1],
                (String) borrowedBook[2],
                (String) borrowedBook[3],
                (LocalDate) borrowedBook[4],
                (LocalDate) borrowedBook[5]
        );
    }

    public static List<BorrowedBookDto> mapToBorrowedBookDtoList(List<Object[]> borrowedBooks) {
        List<BorrowedBookDto> borrowedBookDtoList = new ArrayList<>();
        for (Object[] borrowedBook : borrowedBooks) {
            BorrowedBookDto borrowedBookDto = new BorrowedBookDto();
            borrowedBookDto.setReaderId((Integer) borrowedBook[0]);
            borrowedBookDto.setFirstName((String) borrowedBook[1]);
            borrowedBookDto.setLastName((String) borrowedBook[2]);
            borrowedBookDto.setBookTitle((String) borrowedBook[3]);

            // Convert java.sql.Date to java.time.LocalDate
            Date reserveDateSql = (Date) borrowedBook[4];
            Date dueDateSql = (Date) borrowedBook[5];
            LocalDate reserveDate = reserveDateSql.toLocalDate();
            LocalDate dueDate = dueDateSql.toLocalDate();

            borrowedBookDto.setReserveDate(reserveDate);
            borrowedBookDto.setDueDate(dueDate);

            borrowedBookDtoList.add(borrowedBookDto);
        }
        return borrowedBookDtoList;
    }
}