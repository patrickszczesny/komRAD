package com.komRAD.service;

import com.komRAD.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<Game,Long> {
}
