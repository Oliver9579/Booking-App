package com.example.booking.date.services;

import com.example.booking.date.models.Days;
import com.example.booking.date.repositories.DaysRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class DaysServiceImpl implements DaysService {

  private DaysRepository daysRepository;

  @Override
  public List<String> getFullTravelDates(String startDate, String endDate) {
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    try {
      LocalDate firstDate = LocalDate.parse(startDate.substring(0, 10), dateFormat);
      LocalDate lastDate = LocalDate.parse(endDate.substring(0, 10), dateFormat);
      long travelLength = ChronoUnit.DAYS.between(firstDate, lastDate);
      List<String> dates = new ArrayList<>();
      for (int i = 0; i <= travelLength; i++) {
        dates.add(firstDate.plusDays(i).format(dateFormat));
      }
      return dates;
    }catch (DateTimeParseException e){
    }
    return new ArrayList<>();
  }

  @Override
  public List<String> getFullTravelDates(Date startDate, Date endDate) {
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate firstDate = new java.sql.Date(startDate.getTime()).toLocalDate();
    LocalDate lastDate = new java.sql.Date(endDate.getTime()).toLocalDate();
    long travelLength = ChronoUnit.DAYS.between(firstDate, lastDate);
    List<String> dates = new ArrayList<>();
    for (int i = 0; i <= travelLength; i++) {
      dates.add(firstDate.plusDays(i).format(dateFormat));
    }
    return dates;
  }

  @Override
  public Days getByDate(String date) {
    return daysRepository.findByDate(date);
  }

  @Override
  public Days save(Days days) {
    return daysRepository.save(days);
  }

}
