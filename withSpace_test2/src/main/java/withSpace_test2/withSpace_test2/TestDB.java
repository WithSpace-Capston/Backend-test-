package withSpace_test2.withSpace_test2;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import withSpace_test2.withSpace_test2.domain.Member;
import withSpace_test2.withSpace_test2.domain.friend.FriendShip;
import withSpace_test2.withSpace_test2.domain.space.MemberSpace;
import withSpace_test2.withSpace_test2.domain.space.Space;
import withSpace_test2.withSpace_test2.domain.space.schedule.Category;
import withSpace_test2.withSpace_test2.domain.space.schedule.Schedule;
import withSpace_test2.withSpace_test2.domain.space.schedule.ToDo;
import withSpace_test2.withSpace_test2.service.*;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestDB {
    //scheduleInit()에 사용

    private final CategoryService categoryService;

    private final ScheduleService scheduleService;

    private final ToDoService toDoService;

    //friendInit()에 사용

    private final FriendShipService friendShipService;

    private final MemberService memberService;


    @PostConstruct
    public void postConstruct() {
        scheduleInit();
        friendInit();
    }

    @Transactional
    public void friendInit() {
        Member memberA = new Member();
        memberA.setMemberName("memberA");
        memberA.setEmail("aaa@naver.com");
        memberA.setStatus(true);

        Member memberB = new Member();
        memberB.setMemberName("memberB");
        memberB.setEmail("bbb@naver.com");
        memberB.setStatus(true);

        Member memberC = new Member();
        memberC.setMemberName("memberC");
        memberC.setEmail("ccc@naver.com");
        memberC.setStatus(true);

        memberService.join(memberA);
        memberService.join(memberB);
        memberService.join(memberC);

        //A와 B가 친구
        FriendShip friendShip1 = new FriendShip(memberA, memberB);
        FriendShip friendShip2 = new FriendShip(memberB, memberA);

        //A와 C가 친구
        FriendShip friendShip3 = new FriendShip(memberA, memberC);
        FriendShip friendShip4 = new FriendShip(memberC, memberA);


        friendShipService.addFriend(friendShip1);
        friendShipService.addFriend(friendShip2);

        friendShipService.addFriend(friendShip3);
        friendShipService.addFriend(friendShip4);
    }

    @Transactional
    public void scheduleInit() {
        Member member = new Member();
        member.setMemberName("memberA");
        Space memberASpace = new MemberSpace(member);
        Schedule schedule = new Schedule(memberASpace);

        scheduleService.makeSchedule(schedule);

        Category studyCategory = new Category(schedule, "공부");
        Category workOutCategory = new Category(schedule, "운동");

        categoryService.makeCategory(studyCategory);
        categoryService.makeCategory(workOutCategory);

        ToDo springToDo = new ToDo(studyCategory, "스프링 공부", false, LocalDateTime.now());
        ToDo codingToDo = new ToDo(studyCategory, "코딩 공부", false, LocalDateTime.now());

        toDoService.makeTodo(springToDo);
        toDoService.makeTodo(codingToDo);

        ToDo healthToDo = new ToDo(workOutCategory, "헬스", false, LocalDateTime.now());
        toDoService.makeTodo(healthToDo);
    }
}
