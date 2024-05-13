package com.example.booking.date.services;

import com.example.booking.date.models.Days;

import java.util.Date;
import java.util.List;

public interface DaysService {

  List<String> getFullTravelDates(String startDate, String endDate);

  List<String> getFullTravelDates(Date startDate, Date endDate);

  Days getByDate(String date);

}
