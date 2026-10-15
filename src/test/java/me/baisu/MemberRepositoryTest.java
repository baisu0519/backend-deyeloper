package me.baisu;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Sql("/Insert-members.sql")
    @DisplayName("MemberRepository 를 동해 member 테이블의 모든 레코드(3개) 가져오기")
    @Test
    public void getAllMembers(){
        List<Member> members = memberRepository.findAll();//select * from member;


        assertThat(members.size()).isEqualTo(3);
    }
    @Sql("/Insert-members.sql")
    @DisplayName("MemberRepository를 통해 이름이 'C'인 레코드 가져오기")
    @Test
    public void getMemberByName(){
        //given

        //when
        Optional<Member> optMember = memberRepository.findByName("C");
        Member member = optMember.get();

        //then
        assertThat(member.getId()).isEqualTo(3);
    }

    @Sql("/Insert-members.sql")
    @DisplayName("MemberRepository를 통해 Id가 2인 레코드 가져오기")
    @Test
    public void getMemberById(){
        //given
        //when
        Member member = memberRepository.findById(2L).get();

        //then

        assertThat(member.getName()).isEqualTo("B");
    }
    @DisplayName("MemberRepository를 통해 이름이 '양쩡후이'인 레코드 추가하기")
    @Test
    public void saveMemberByName(){
        //given
       // Member member = new Member(1L,"양쩡후이");
        Member member = new Member("양쩡후이");


        //when
        memberRepository.save(member);

        //then
        //member 테이블에서 모든 레코드를 select 했을 때 레코드의 수가 1이고
        //그 레코드의 name 칼럼 값이 '양쩡후이'인지 확인
        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("양쩡후이");
    }

    @Sql("/Insert-members.sql")
    @DisplayName("테이블 member 에서 id가 3인 레코드 삭제하기")
    @Test
    public void deleteById3(){
        //given

        //when
        memberRepository.deleteById(3L);
        //then
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(2);
    }

    @Sql("/Insert-members.sql")
    @DisplayName("테이블 member 의 모든 레코드 삭제하기")
    @Test
    public void deleteAll() {
        // given
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(3);

        // when
        memberRepository.deleteAll();

        // then
        members = memberRepository.findAll();
        assertThat(members.size()).isZero();
    }

    @DisplayName("테이블 member name이 'A', 'B'인 레코드를 한 번에 삽입하기")
    @Test
    public void saveMembers(){
        //given
        List<Member> members = List.of(new Member("A"), new Member("B"));

        //when
        memberRepository.saveAll(members);

        //then
        assertThat(memberRepository.findAll().size()).isEqualTo(2);

    }
    @Sql("/Insert-members.sql")
    @DisplayName("변경감지 기능을 통해 Id가 2인 레코드의 이름을 'B', 'BB'로 변경하기")
    //@Transactional
    @Test
    public void update(){
        //given
        Member member = memberRepository.findById(2L).get();

        //when
        member.setName("BB");
        //then
        Member updatedMember = memberRepository.findById(2L).get();
        assertThat(updatedMember.getName()).isEqualTo("BB");

    }



}