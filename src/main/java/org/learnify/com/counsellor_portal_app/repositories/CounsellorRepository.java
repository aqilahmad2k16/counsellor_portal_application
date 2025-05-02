package org.learnify.com.counsellor_portal_app.repositories;

import org.learnify.com.counsellor_portal_app.models.Counsellor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounsellorRepository extends JpaRepository<Counsellor, Long> {
}
