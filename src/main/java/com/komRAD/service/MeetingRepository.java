package com.komRAD.service;

import com.komRAD.model.Meeting;
import org.springframework.data.repository.CrudRepository;

public interface MeetingRepository extends CrudRepository<Meeting,Long> {
}
