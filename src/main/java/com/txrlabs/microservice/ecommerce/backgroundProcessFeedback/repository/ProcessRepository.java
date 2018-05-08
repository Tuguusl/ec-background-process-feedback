package com.txrlabs.microservice.ecommerce.backgroundProcessFeedback.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@PreAuthorize("hasRole('ROLE_MICROSERVICE')")
public interface ProcessRepository extends CrudRepository<Process, Long> {

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MICROSERVICE', 'ROLE_PROCESS')")
    Optional<Process> findById(Long id);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MICROSERVICE', 'ROLE_PROCESS')")
    boolean existsById(Long id);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MICROSERVICE', 'ROLE_PROCESS')")
    Iterable<Process> findAll();

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MICROSERVICE', 'ROLE_PROCESS')")
    Iterable<Process> findAllById(Iterable<Long> ids);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MICROSERVICE', 'ROLE_PROCESS')")
    long count();

}
