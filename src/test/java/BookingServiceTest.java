import com.max.BookingService;
import com.max.CantBookException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class BookingServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceTest.class);
    static BookingService mockBookingService;

    @BeforeAll
    static void init(){
        mockBookingService = mock(BookingService.class);
        logger.info("Мок-объект сервиса бронирования создан");
    }

    @Test
    void testCreateBookingWithMockTrue() throws CantBookException {
        logger.info("Запуск теста CreateBookingWithMockTrue");

        when(mockBookingService.book(anyString(), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(true);
        logger.debug("Создание брони успешно");

        Assertions.assertTrue(mockBookingService.book("Новая бронь", LocalDateTime.now(), LocalDateTime.now()));
        logger.info("Тест CreateBookingWithMockTrue завершен");
    }

    @Test
    void testCreateBookingWithMockThrowException() throws CantBookException {
        logger.info("Запуск теста CreateBookingWithMockThrowException");

        when(mockBookingService.book(anyString(), any(LocalDateTime.class), any(LocalDateTime.class))).thenThrow(CantBookException.class);
        logger.debug("Выброс исключения CantBookException");

        Assertions.assertThrows(CantBookException.class,
                () -> mockBookingService.book("Новая бронь", LocalDateTime.now(), LocalDateTime.now()));
        logger.info("Тест CreateBookingWithMockThrowException завершен");
    }

}
