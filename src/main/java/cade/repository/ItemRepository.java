package cade.repository;

import cade.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("Select i.id from Item i ")
    List<Integer> findAllIds();
}
