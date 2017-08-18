package cade.application.removespaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CachePalavrasRepository extends JpaRepository<CachePalavras, Long> {

    CachePalavras findByForm(String form);
}