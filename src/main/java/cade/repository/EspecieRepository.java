package cade.repository;

import cade.entities.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EspecieRepository extends JpaRepository<Especie, Long> {

    @Query("Select e from Especie e where nameEspecie like ?1%")
    List<Especie> getEspeciesByNameEspecieStartingWith(String start);

}
