package org.example.cities.repositories;

import org.example.cities.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource(collectionResourceRel = "cities", path = "cities")
@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Long> {
    //@RestResource(path = "name", rel = "name")
    Page<City> findByNameIgnoreCase(@Param("q") String name, Pageable pageable);
    //@RestResource(path = "nameContains", rel = "nameContains")
    Page<City> findByNameContainsIgnoreCase(@Param("q") String name, Pageable pageable);

    //@RestResource(path = "state", rel = "state")
    Page<City> findByStateCodeIgnoreCase(@Param("q") String stateCode, Pageable pageable);

    //@RestResource(path = "postalCode", rel = "postalCode")
    Page<City> findByPostalCode(@Param("q") String postalCode, Pageable pageable);
}
