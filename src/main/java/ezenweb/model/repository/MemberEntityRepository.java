package ezenweb.model.repository;

import ezenweb.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity,Integer> {

    //1. 특정 필드의 조건으로 레코드/엔티티 검색
    MemberEntity findByMemail(String memail);

    //2. 특정 필드의 조건으로 존재여부 검색
    boolean existsByMemail(String memail);

    //3. 직접 native SQL 지원
    // select * from member where memail =?;
    // @Query(value = "SQL 작성", nativeQuery = true)
        // SQL 매개변수 대입
            // : 매개변수명
    @Query(value = "select * from member where memail = :memail",nativeQuery = true)
    MemberEntity findByMemailSQL(String memail);

    //=============로그인============
    boolean existsByMemaiAndMpassword(String memail,String mpassword);
}
/*
    추상메소드
        1. 특정 필드를 찾는 추상메소드 정의
            MemberEntity findBy필드명(조건매개변수);
        2. 특정 필드의 조건으로 존재여부 검색
            boolean existsBy필드명(조건매개변수);
        - 반환 타입
 */

/*
    -리포지토리 만드는 방법
    1.@Repository
    2.extends JpaRepository<조작할엔티티,PK의 필드타입>

     -리포지토리 이용한 CRUD 메소드
     1. .save(엔티티) : 해당 엔티티객체를 테이블에 삽입 insert
 */
