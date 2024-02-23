package com.example.booking.date.services;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DaysServiceImpl implements DaysService {

  @Override
  public List<String> getFullTravelDates(String startDate, String endDate) {
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate firstDate = LocalDate.parse(startDate, dateFormat);
    LocalDate lastDate = LocalDate.parse(endDate, dateFormat);
    long travelLength = ChronoUnit.DAYS.between(firstDate, lastDate);
    List<String> dates = new ArrayList<>();
    for (int i = 0; i <= travelLength; i++) {
      dates.add(firstDate.plusDays(i).format(dateFormat));
    }
    return dates;
  }
}
