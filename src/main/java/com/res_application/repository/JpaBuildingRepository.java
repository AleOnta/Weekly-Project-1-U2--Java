package com.res_application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.res_application.model.Building;

@Repository
public interface JpaBuildingRepository extends CrudRepository<Building, Long> {

}
