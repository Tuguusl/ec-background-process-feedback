package com.txrlabs.microservice.ecommerce.backgroundProcessFeedback.repository;

import com.txrlabs.microservice.ecommerce.backgroundProcessFeedback.domain.BackgroundProcessFeedback;
import com.txrlabs.microservice.ecommerce.backgroundProcessFeedback.enumeration.ProcessStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@PreAuthorize("hasRole('ROLE_MICROSERVICE')")
public interface BackgroundProcessFeedbackRepository extends CrudRepository<BackgroundProcessFeedback, Long> {

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MICROSERVICE', 'ROLE_PROCESS')")
    BackgroundProcessFeedback findOne(Long id);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MICROSERVICE', 'ROLE_PROCESS')")
    boolean exists(Long id);

    @Override
    @PostFilter("hasRole('ROLE_MICROSERVICE') OR (hasRole('ROLE_PROCESS') AND (hasRole('ROLE_CROSS_CLIENT') OR filterObject.client == authentication.name))")
    Iterable<BackgroundProcessFeedback> findAll();

    @Override
    @PostFilter("hasRole('ROLE_MICROSERVICE') OR (hasRole('ROLE_PROCESS') AND (hasRole('ROLE_CROSS_CLIENT') OR filterObject.client == authentication.name))")
    Iterable<BackgroundProcessFeedback> findAll(Iterable<Long> ids);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MICROSERVICE', 'ROLE_PROCESS')")
    long count();

    @PostFilter("hasRole('ROLE_MICROSERVICE') OR (hasRole('ROLE_PROCESS') AND (hasRole('ROLE_CROSS_CLIENT') OR filterObject.client == authentication.name))")
    List<BackgroundProcessFeedback> findByApp(@Param("app") String app);

    @PostFilter("hasRole('ROLE_MICROSERVICE') OR (hasRole('ROLE_PROCESS') AND (hasRole('ROLE_CROSS_CLIENT') OR filterObject.client == authentication.name))")
    List<BackgroundProcessFeedback> findByAppAndStatus(@Param("app") String app, @Param("status") ProcessStatus status);

}
