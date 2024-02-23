package com.example.booking.date.services;

import java.util.List;

public interface DaysService {

  List<String> getFullTravelDates(String startDate, String endDate);

}
