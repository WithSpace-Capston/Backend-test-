import domain.Member;
import domain.MemberTeam;
import domain.Team;
import domain.friend.FriendShip;
import domain.space.MemberSpace;
import domain.space.Page;
import domain.space.TeamSpace;

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

            //개인에게 스페이스 주기
            MemberSpace memberSpace = new MemberSpace(member2);
            member.setMemberSpace(memberSpace);
            em.persist(memberSpace);

            //팀에게 스페이스 주기
            TeamSpace teamSpace = new TeamSpace(team);
            team.setTeamSpace(teamSpace);
            em.persist(teamSpace);

            // 멤버-팀 관계 생성
            MemberTeam memberTeam = new MemberTeam(member, team);

            // 멤버 - 멤버팀 - 팀 이어주기 -
            member.getMemberTeams().add(memberTeam);
            team.getMemberTeams().add(memberTeam);

            //팀 스페이스에 페이지를 넣기
            Page teamPage = new Page();
            teamSpace.getPageList().add(teamPage);
            teamPage.setSpace(teamSpace);

            //개인 스페이스에 페이지 넣기
            Page memberPage = new Page();
            memberSpace.getPageList().add(memberPage);
            memberPage.setSpace(memberSpace);

            //페이지 밑에 페이지 넣기
            Page childPage = new Page();
            memberPage.addchildPage(childPage);

            //친구관계 만들기
            FriendShip friendship = new FriendShip();
            friendship.setMember(member);
            friendship.setFriend(member2);
            friendship.setStatus(FriendShip.Status.PENDING);
            em.persist(friendship);




            //애들 생명주기 맞춰야됨..
            em.persist(team);
            em.persist(member);
            em.persist(memberTeam);


            em.persist(member2);

            em.persist(memberSpace);
            em.persist(memberPage);
            em.persist(teamPage);


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
