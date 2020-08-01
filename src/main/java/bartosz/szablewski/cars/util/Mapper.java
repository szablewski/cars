package bartosz.szablewski.cars.util;

import bartosz.szablewski.cars.model.Car;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Mapper {


    public static String TYPE = "text/csv";
    static String[] HEADERs = {"Id", "Nazwa", "Data zakupu", "Kolor"};
    private static Object Date;

    public static List<Car> carsMapper(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Car> cars = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Car car = new Car(
                        Long.parseLong(csvRecord.get("Id")),
                        csvRecord.get("Nazwa"),
                        (java.util.Date) (Date = new SimpleDateFormat("dd.MM.yyyy").parse(csvRecord.get("Data zakupu"))),
                        csvRecord.get("Kolor")
                );

                cars.add(car);
            }

            return cars;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }
}
