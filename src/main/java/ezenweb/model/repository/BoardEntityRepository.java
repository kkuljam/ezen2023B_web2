package ezenweb.model.repository;

import ezenweb.model.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 매핑된 테이블의 엔티티/레코드 들을 조작/관리 하는 리모콘/인터페이스 역할
public interface BoardEntityRepository extends JpaRepository<BoardEntity,Integer> {
    @Query(value = "select * from board where bno=:bno and mno_fk=:mno_fk",nativeQuery = true)
    List<Object> findByBnoAndMno(int bno , int mno_fk);
}
