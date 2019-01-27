package com.komRAD.service;

import com.komRAD.model.Venue;
import org.springframework.data.repository.CrudRepository;

public interface VenueRepository extends CrudRepository<Venue,Long> {
}
