package cade.repository;

import cade.entities.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ClasseRepository extends JpaRepository<Classe, Long> {

}
