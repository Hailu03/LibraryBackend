package com.library.programmingexercise.mapper;

import com.library.programmingexercise.dto.UserBorrowedBookDto;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UserBorrowedBookMapper {
    public static List<UserBorrowedBookDto> mapToUserBorrowedBookDtoList(List<Object[]> userBorrowedBooks) {
        List<UserBorrowedBookDto> userBorrowedBookDtoList = new ArrayList<>();

        for (Object[] borrowedBook : userBorrowedBooks) {
            UserBorrowedBookDto userBorrowedBookDto = new UserBorrowedBookDto();

            userBorrowedBookDto.setRentID((Integer) borrowedBook[0]);
            byte[] imageBytes = (byte[]) borrowedBook[1];
            // Assuming the first element of the Object array is the image as Blob
            if (imageBytes != null) {
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                userBorrowedBookDto.setImage(base64Image);
            }
            userBorrowedBookDto.setBookTitle((String) borrowedBook[2]);

            // Convert java.sql.Date to java.time.LocalDate
            Date reserveDateSql = (Date) borrowedBook[3];
            Date dueDateSql = (Date) borrowedBook[4];
            LocalDate reserveDate = reserveDateSql.toLocalDate();
            LocalDate dueDate = dueDateSql.toLocalDate();

            userBorrowedBookDto.setReserveDate(reserveDate);
            userBorrowedBookDto.setDueDate(dueDate);

            userBorrowedBookDtoList.add(userBorrowedBookDto);
        }

        return userBorrowedBookDtoList;
    }
}
