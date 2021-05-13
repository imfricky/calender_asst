package com.example.demo.Repository;

import com.example.demo.model.Calender;
import com.example.demo.model.TimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface CalenderRepository extends JpaRepository<Calender,Long> {

    @Query(value = "select * from calender c where c.cid=?2 and date=?1",nativeQuery = true)
    public List<Calender> getEmployeeCalender(String date,Long id);

    @Query(value="select distinct(c.cid) from calender c where date=?1 and (c.start_time between ?2 and ?3 or c.end_time between ?2 and ?3 or( c.start_time<=?2 and c.end_time>=?3))",nativeQuery = true)
    public List<Long> getConflictCalender(String date, Date startTime, Date endTime);


}
