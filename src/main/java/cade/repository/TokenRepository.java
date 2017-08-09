package cade.repository;

import cade.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = "select distinct * from token where id in (select( select t.id from token t where (t.tag like 'N%' or t.tag like 'V%' or t.tag like 'A%') and LENGTH(t.lemma) > 1 and t.item_id = i.id LIMIT ?1) from item i) order by item_id", nativeQuery = true)
    List<Token> getTokenNounVerbAdjective(int limit);

    @Query(value = "select * from token where item_id = ?1", nativeQuery = true)
    List<Token> getTokenByItemId(int itemId);
}
