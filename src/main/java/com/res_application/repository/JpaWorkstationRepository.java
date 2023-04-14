package com.res_application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.res_application.model.Workstation;

@Repository
public interface JpaWorkstationRepository extends CrudRepository<Workstation, Long> {

}
