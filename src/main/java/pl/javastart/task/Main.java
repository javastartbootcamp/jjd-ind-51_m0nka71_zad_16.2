package pl.javastart.task;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run(new Scanner(System.in));
    }

    public void run(Scanner scanner) {
        // uzupełnij rozwiązanie. Korzystaj z przekazanego w parametrze scannera
        System.out.println("Podaj datę:");
        String userDate = scanner.nextLine();

        List<String> patterns = Arrays.asList("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy.MM.dd HH:mm:ss");

        boolean formattedDate = false;
        for (String pattern : patterns) {
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(userDate, DateTimeFormatter.ofPattern(pattern));
                LocalTime localTime = localDateTime.toLocalTime();
                System.out.println("Czas lokalny: " + localTime);

                //UTC
                Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
                System.out.println("UTC: " + instant);

                //Londyn
                ZoneId londonZoneId = ZoneId.of("Europe/London");
                ZonedDateTime londonZonedDateTime = localDateTime.atZone(londonZoneId);
                System.out.println("Londyn : " + londonZonedDateTime);

                //Los Angeles:
                ZoneId losAngelesZoneId = ZoneId.of("America/Los_Angeles");
                ZonedDateTime losAngelesDateTime = localDateTime.atZone(losAngelesZoneId);
                System.out.println("Los Angeles: " + losAngelesDateTime);

                //Sydney
                ZoneId sydneyZoneId = ZoneId.of("Australia/Sydney");
                ZonedDateTime sydneyDateTime = localDateTime.atZone(sydneyZoneId);
                System.out.println("Sydney: " + sydneyDateTime);

                formattedDate = true;
                break;
            } catch (DateTimeParseException ignored) {
                //ignored
            }
        }
    }
}
