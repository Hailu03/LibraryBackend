package com.library.programmingexercise.service.impl;

import com.library.programmingexercise.dto.ReadersinfoDto;
import com.library.programmingexercise.entity.*;
import com.library.programmingexercise.exception.ResourceNotFoundException;
import com.library.programmingexercise.mapper.BookMapper;
import com.library.programmingexercise.mapper.ReadersinfoMapper;
import com.library.programmingexercise.repository.BookRepository;
import com.library.programmingexercise.repository.ReviewBookRepository;
import com.library.programmingexercise.service.ReadersinfoService;
import com.library.programmingexercise.repository.ReaderInfoRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.io.Reader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReadersinfoServiceImpl implements ReadersinfoService {
    private ReaderInfoRepository readersinfoRepository; // Inject the ReaderInfoRepository
    private BookRepository bookRepository; // Inject the BookRepository
    private ReviewBookRepository reviewBookRepository; // Inject the ReviewBookRepository
    private static final Logger logger = LoggerFactory.getLogger(ReadersinfoServiceImpl.class); // Create a logger

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // Implement the method to add a reader
    @Override
    public ReadersinfoDto addReaderInfo(ReadersinfoDto readerInfoDto) {
        ReadersInfo readerInfo = ReadersinfoMapper.maptoReadersinfo(readerInfoDto);
        ReadersInfo savedReaderInfo = readersinfoRepository.save(readerInfo);

        List<Book> books = bookRepository.findAll();
//        for (Book book : books){
//            ReviewbookId reviewbookId = new ReviewbookId();
//            reviewbookId.setBookID(book.getId());
//            reviewbookId.setReaderID(savedReaderInfo.getId());
//
//            ReviewBook reviewbook = new ReviewBook();
//            reviewbook.setId(reviewbookId);
//            reviewbook.setBookID(book);
//            reviewbook.setReaderID(savedReaderInfo);
//            reviewBookRepository.save(reviewbook);
//        }
        books.parallelStream().forEach(book -> {
            ReviewbookId reviewbookId = new ReviewbookId();
            reviewbookId.setBookID(book.getId());
            reviewbookId.setReaderID(savedReaderInfo.getId());

            Reviewbook reviewbook = new Reviewbook();
            reviewbook.setId(reviewbookId);
            reviewbook.setBookID(book);
            reviewbook.setReaderID(savedReaderInfo);
            reviewBookRepository.save(reviewbook);
        });
        return null;
    }

    // Implement the method to find a reader by email
    @Override
    public ReadersinfoDto findByEmail(String email) {
        ReadersInfo reader = readersinfoRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Reader not found with email: " + email));

        return ReadersinfoMapper.maptoReadersinfoDto(reader);
    }

    // Implement the method to get all readers
    @Override
    public List<ReadersinfoDto> getAllUsers() {
        List<ReadersInfo> readers = readersinfoRepository.findAll();
        if (logger.isDebugEnabled())
        {
            readers.forEach(reader -> logger.debug("Users: {}", reader));
        }
        return readers.stream() // Creates a Stream of elements from the students List
                .map((reader) -> ReadersinfoMapper.maptoReadersinfoDto(reader))  // Transforms each Student object to a StudentDto object using StudentMapper
                .collect(Collectors.toList()); // Collects the transformed StudentDto objects into a List
    }

    // Implement the method to authenticate a reader
    @Override
    public ReadersinfoDto authenticate(String email, String password) throws AuthenticationException {
        ReadersInfo reader = readersinfoRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("User not found with email: " + email));

        if (!password.equals(reader.getPassword())) {
            throw new AuthenticationException("Invalid password");
        }

        // security
        reader.setPassword(passwordEncoder.encode(password));
        return ReadersinfoMapper.maptoReadersinfoDto(reader);
    }

    // Implement the method to save an image
    @Override
    public ReadersinfoDto saveImage(int readerID, byte[] imageData) {
        Optional<ReadersInfo> readerOptional = readersinfoRepository.findById(readerID);
        if (readerOptional.isPresent()) {
            ReadersInfo reader = readerOptional.get();
            reader.setImage(imageData);
            readersinfoRepository.save(reader);

            return ReadersinfoMapper.maptoReadersinfoDto(reader);
        } else {
            throw new RuntimeException("Reader not found with ID: " + readerID);
        }
    }

    // Implement the method to update a reader
    @Override
    public ReadersinfoDto updateReadersinfo(Integer readerID, ReadersInfo readersinfo) {
        ReadersInfo reader = readersinfoRepository.findById(readerID).orElseThrow(() -> new RuntimeException("Book is not found with id: " + readerID));

        reader.setAddress(readersinfo.getAddress());
        reader.setEmail(readersinfo.getEmail());
        reader.setFirstName(readersinfo.getFirstName());
        reader.setLastName(readersinfo.getLastName());
        reader.setPhoneNumber(readersinfo.getPhoneNumber());
        reader.setPassword(readersinfo.getPassword());
        reader.setUsername(readersinfo.getUsername());
        reader.setGender(readersinfo.getGender());
        reader.setImage(readersinfo.getImage());

        return ReadersinfoMapper.maptoReadersinfoDto(readersinfoRepository.save(reader));
    }

    // Implement the method to get the top 5 most borrowed users
    @Override
    public List<ReadersinfoDto> getTop5MostBorrowUser() {
        List<ReadersInfo> readersInfos = readersinfoRepository.findTop5MostBorrowUser().
                orElseThrow(() -> new ResourceNotFoundException("There is no one borrow books at the moment"));
        return  ReadersinfoMapper.mapToListReaderInfoDto(readersInfos);
    }

    @Override
    public Integer findNumberOfTimeUserBorrowById(Integer id) {
        Integer numberOfTimeUserBorrow = readersinfoRepository.findNumberOfTimeUserBorrowById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id = " + id + "has not borrowed any book yet"));
        return numberOfTimeUserBorrow;
    }

    // Implement the method to confirm an email
    @Override
    public boolean confirmEmail(String email) {
        ReadersinfoDto readerDto = findByEmail(email);
        return readerDto != null;
    }

    // Implement the method to update a password
    @Override
    public void updatePassword(String email, String newPassword) {
        ReadersinfoDto reader = findByEmail(email);
        reader.setPassword(newPassword);
        readersinfoRepository.save(ReadersinfoMapper.maptoReadersinfo(reader));
    }
}
