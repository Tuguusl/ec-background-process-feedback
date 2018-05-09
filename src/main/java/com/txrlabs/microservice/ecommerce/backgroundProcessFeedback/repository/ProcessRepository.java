package com.txrlabs.microservice.ecommerce.backgroundProcessFeedback.repository;

import com.txrlabs.microservice.ecommerce.backgroundProcessFeedback.enumeration.ProcessStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@PreAuthorize("hasRole('ROLE_MICROSERVICE')")
public interface ProcessRepository extends CrudRepository<Process, Long> {

    @Override
    @PostAuthorize("hasRole('ROLE_MICROSERVICE') OR (hasRole('ROLE_PROCESS') AND (hasRole('ROLE_CROSS_CLIENT') OR returnObject.client == authentication.name))")
    Process findOne(Long id);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MICROSERVICE', 'ROLE_PROCESS')")
    boolean exists(Long id);

    @Override
    @PostFilter("hasRole('ROLE_MICROSERVICE') OR (hasRole('ROLE_PROCESS') AND (hasRole('ROLE_CROSS_CLIENT') OR filterObject.client == authentication.name))")
    Iterable<Process> findAll();

    @Override
    @PostFilter("hasRole('ROLE_MICROSERVICE') OR (hasRole('ROLE_PROCESS') AND (hasRole('ROLE_CROSS_CLIENT') OR filterObject.client == authentication.name))")
    Iterable<Process> findAll(Iterable<Long> ids);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MICROSERVICE', 'ROLE_PROCESS')")
    long count();

    @PostFilter("hasRole('ROLE_MICROSERVICE') OR (hasRole('ROLE_PROCESS') AND (hasRole('ROLE_CROSS_CLIENT') OR filterObject.client == authentication.name))")
    List<Process> findByApp(@Param("app") String app);

    @PostFilter("hasRole('ROLE_MICROSERVICE') OR (hasRole('ROLE_PROCESS') AND (hasRole('ROLE_CROSS_CLIENT') OR filterObject.client == authentication.name))")
    List<Process> findByAppAndStatus(@Param("app") String app, @Param("status") ProcessStatus status);

}
