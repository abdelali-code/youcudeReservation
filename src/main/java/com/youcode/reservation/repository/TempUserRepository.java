package com.youcode.reservation.repository;

import com.youcode.reservation.model.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TempUserRepository extends JpaRepository<TempUser, Long> {

    List<TempUser> findAllByIsEvaluated(boolean val);
    @Transactional
    void deleteAllByIdIn(List<Long> ids);

    @Transactional
    List<TempUser> getAllByIdIn(List<Long> ids);
}
