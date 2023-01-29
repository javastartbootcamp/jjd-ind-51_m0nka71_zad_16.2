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

        List<String> patternsWithTime = Arrays.asList("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
                "yyyy.MM.dd HH:mm:ss", "dd.MM.yyyy HH:mm:ss");
        List<String> patternWithDate = List.of("yyyy-MM-dd");

        LocalDateTime localDateTime = loadDateTimeFromUser(userDate, patternsWithTime, patternWithDate);
        if (localDateTime != null) {
            display(localDateTime);
        }
    }

    private static LocalDateTime loadDateTimeFromUser(String userDate, List<String> patternsWithTime, List<String> patternWithDate) {
        LocalDateTime localDateTime = null;
        for (String pattern : patternsWithTime) {
            try {
                localDateTime = LocalDateTime.parse(userDate, DateTimeFormatter.ofPattern(pattern));
                return localDateTime;
            } catch (DateTimeParseException ignored) {
                //ignored
            }
        }
        for (String patt : patternWithDate) {
            LocalDate localDate = LocalDate.parse(userDate, DateTimeFormatter.ofPattern(patt));
            localDateTime = LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
        }
        return localDateTime;
    }

    private static void display(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        //Local
        String formattedLocalTime = dateTimeFormatter.format(localDateTime);
        System.out.println("Czas lokalny: " + formattedLocalTime);

        //UTC
        ZonedDateTime utcZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        String utcDateTime = dateTimeFormatter.format(utcZonedDateTime);
        System.out.println("UTC: " + utcDateTime);

        //Londyn
        ZonedDateTime londonZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Europe/London"));
        String londonDateTime = dateTimeFormatter.format(londonZonedDateTime);
        System.out.println("Londyn: " + londonDateTime);

        //Los Angeles:
        ZonedDateTime losAngelesZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
        String losAngelesDateTime = dateTimeFormatter.format(losAngelesZonedDateTime);
        System.out.println("Los Angeles: " + losAngelesDateTime);

        //Sydney
        ZonedDateTime sydneyZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Australia/Sydney"));
        String sydneyDateTime = dateTimeFormatter.format(sydneyZonedDateTime);
        System.out.println("Sydney: " + sydneyDateTime);
    }
}
