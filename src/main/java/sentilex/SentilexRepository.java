package sentilex;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SentilexRepository extends JpaRepository<SentilexData, Long> {

    SentilexData findByForm(String form);
}