package pl.kolodzanka.meteo;

import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeteoTest {

    String rawResponse;
    Meteo meteo = new Meteo();
    DANE dane;
    @Mock
    Source source;


    @BeforeEach
    public void readResponseFromFile() {

        String fileName = "/Users/ania/ConfigHelperText.txt";
        Path paths = Paths.get(fileName);
        try {
            rawResponse = Files.lines(paths).collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void shouldReturnStringWithTemperature() {
        when(source.getResponse()).thenReturn(rawResponse);
        String result = Meteo.getDane(DANE.TEMPERATURA, source.getResponse());
        Assertions.assertEquals("13.4 ", result);
    }

    @Test
    void shouldReturnStringWithHumidity() {
        when(source.getResponse()).thenReturn(rawResponse);
        String result = Meteo.getDane(DANE.WILGOTNOŚĆ, source.getResponse());
        Assertions.assertEquals("39.0", result);
    }

    @Test
    void shoouldThrowExceptionWhenSourceIsNull() {
        when(source.getResponse()).thenReturn(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Meteo.getDane(DANE.TEMPERATURA, source.getResponse()));
    }

    @Test
    void shoouldThrowArgumentIllegalExceptionWhenDaneIsNull() {
        try {
            when(source.getResponse()).thenReturn(rawResponse);
            Meteo.getDane(null, source.getResponse());
            fail("Should Throw Exception");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Illegal data!", e.getMessage());
        }

    }


    @Test
    void shoouldThrowArgumentIllegalExceptionWhenInputIsEmpty() {
            when(source.getResponse()).thenReturn("");
           Assertions.assertThrows(IllegalArgumentException.class,
                   () -> Meteo.getDane(DANE.TEMPERATURA, source.getResponse()));
    }

    @Test
    public void shouldReturnAverageData () {
        List<String> list = Arrays.asList("16.1", "15.6","16.2", "15.6", "16.4", "15.6", "15.2", "15.5", "15.3");
        assertEquals((Double)15.722222222222221, meteo.averageData(list));

    }

    @Test
    public void shouldReturnAverageDatawithNull () {
        List<String> list = Arrays.asList("16.1", "15.6","16.2", "null", "16.4", "15.6", "15.2", "15.5", "15.3");
        assertEquals((Double)15.722222222222221, meteo.averageData(list));

    }
}