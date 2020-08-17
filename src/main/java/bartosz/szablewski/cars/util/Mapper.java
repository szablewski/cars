package bartosz.szablewski.cars.util;

import bartosz.szablewski.cars.model.Car;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Mapper {
    private static final String ID = "Id";
    private static final String NAZWA = "Nazwa";
    private static final String DATA_ZAKUPU = "Data zakupu";
    private static final String KOLOR = "Kolor";

    public static List<Car> carsMapper(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Car> cars = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Car car = new Car(
                        Long.parseLong(csvRecord.get(ID)),
                        csvRecord.get(NAZWA),
                        Date.from(Instant.parse(csvRecord.get(DATA_ZAKUPU))),
                        csvRecord.get(KOLOR)
                );
                if (!car.getColor().equals(""))
                    cars.add(car);
            }

            return cars;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }
}
