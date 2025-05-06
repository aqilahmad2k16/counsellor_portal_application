package org.learnify.com.counsellor_portal_app.repositories;

import org.learnify.com.counsellor_portal_app.dtos.requestDtos.EnquiryDto;
import org.learnify.com.counsellor_portal_app.models.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {

    List<Enquiry> findAllByCounsellorId(Long counsellorId);
}
