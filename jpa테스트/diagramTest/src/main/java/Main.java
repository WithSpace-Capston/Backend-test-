import domain.Member;
import domain.MemberTeam;
import domain.Team;
import domain.space.MemberSpace;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.awt.print.Book;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

//            Member member = new Member();
//            member.setMember_name("test");
//            member.setTeamRelations();
//            em.persist(member);

            System.out.println("test==========================1");

            // 팀 생성
            Team team = new Team();
            team.setTeamName("Team A");

            // 멤버생성
            Member member = new Member();
            member.setMemberName("John");
            member.setEmail("john@example.com");
            member.setPassword("password");

            //팀없는 멤버 테스트
            Member member2 = new Member();
            member2.setMemberName("한슬이");
            member2.setEmail("hanseul@example.com");
            member2.setPassword("password2");
            MemberSpace memberSpace = new MemberSpace(member2);
            MemberSpace memberSpace2 = new MemberSpace(member2);
            member.setMemberSpace(memberSpace);
            member.setMemberSpace(memberSpace2);

            // 멤버-팀 관계 생성
            MemberTeam memberTeam = new MemberTeam(member, team);

            // 멤버 - 멤버팀 - 팀 이어주기 - 관련 메소드 필요
            member.getMemberTeams().add(memberTeam);
            team.getMemberTeams().add(memberTeam);


            em.persist(team);
            em.persist(member);
            em.persist(memberTeam);
            em.persist(member2);
            em.persist(memberSpace); //얘는 member이랑 생명주기? 맞추는걸로 바꿔야됨
            em.persist(memberSpace2);



            System.out.println("test==========================");

            tx.commit();


        } catch (Exception e) {
            System.out.println("Exception-----");
            System.out.println(e);
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}
