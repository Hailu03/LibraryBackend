package com.library.programmingexercise.mapper;

import com.library.programmingexercise.dto.ReadersinfoDto;
import com.library.programmingexercise.entity.ReadersInfo;

import java.util.ArrayList;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ReadersinfoMapper {
    public static ReadersinfoDto maptoReadersinfoDto(ReadersInfo readersinfo) {
        ReadersinfoDto readersinfoDto = new ReadersinfoDto();
        readersinfoDto.setId(readersinfo.getId());
        readersinfoDto.setFirstName(readersinfo.getFirstName());
        readersinfoDto.setLastName(readersinfo.getLastName());
        readersinfoDto.setPhoneNumber(readersinfo.getPhoneNumber());
        readersinfoDto.setAddress(readersinfo.getAddress());
        readersinfoDto.setEmail(readersinfo.getEmail());
        readersinfoDto.setUsername(readersinfo.getUsername());
        readersinfoDto.setPassword(readersinfo.getPassword());
        readersinfoDto.setGender(readersinfo.getGender());

        // Convert byte array image to Base64
        byte[] imageBytes = readersinfo.getImage();
        if (imageBytes != null) {
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            readersinfoDto.setImage(base64Image);
        }

        return readersinfoDto;
    }

    public static List<ReadersinfoDto> mapToListReaderInfoDto(List<ReadersInfo> readersInfos){
        List<ReadersinfoDto> readerInfoDtos = new ArrayList<>();
        for (ReadersInfo readersInfo : readersInfos) {
            readerInfoDtos.add(maptoReadersinfoDto(readersInfo));
        }
        return readerInfoDtos;
    }

    public static ReadersInfo maptoReadersinfo(ReadersinfoDto readersinfodto) {
        return new ReadersInfo(
                readersinfodto.getId(),
                readersinfodto.getAddress(),
                readersinfodto.getEmail(),
                readersinfodto.getFirstName(),
                readersinfodto.getLastName(),
                readersinfodto.getPassword(),
                readersinfodto.getPhoneNumber(),
                readersinfodto.getUsername(),
                readersinfodto.getGender(),
                Base64.getDecoder().decode(readersinfodto.getImage().getBytes(StandardCharsets.UTF_8))
        );
    }
}
