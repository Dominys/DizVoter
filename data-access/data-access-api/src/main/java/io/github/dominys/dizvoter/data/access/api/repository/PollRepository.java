package io.github.dominys.dizvoter.data.access.api.repository;

import io.github.dominys.dizvoter.data.access.models.PollEntity;

import java.util.List;
import java.util.Optional;

public interface PollRepository {

  PollEntity createPoll(PollEntity poll);

  Optional<PollEntity> findById(long id);

  List<PollEntity> readPage(long page, long pageSize);

  long countTotal();
}
