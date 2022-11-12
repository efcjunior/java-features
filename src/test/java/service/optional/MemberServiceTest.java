package service.optional;
import org.junit.Before;
import org.junit.Test;
import model.Member;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.RandomStringUtils.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MemberServiceTest {

    private final Member member1 = new Member(
                Long.valueOf(randomNumeric(1,3)), randomAlphabetic(8),
            50);


    private final Member member2 = new Member(
            Long.valueOf(randomNumeric(1,3)), randomAlphabetic(8),
            35);


    private final Member member3 = new Member(
            Long.valueOf(randomNumeric(1,3)), randomAlphabetic(8),
            22);


    private final Member member4 = new Member(
            Long.valueOf(randomNumeric(1,3)), "Julia",
            23);


    private final Member member5     = new Member(
            Long.valueOf(randomNumeric(1,3)), "Andre",
            36);

    private final Member member6     = new Member(
            Long.valueOf(randomNumeric(1,3)), "Ramom",
            35);

    private MemberService memberService;


    @Before
    public void setUp(){
        memberService = new MemberService(newHashSet(member1, member2, member3, member4, member5, member6));
    }

    @Test
    public void findByName() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> memberService.findByName("Andre"));
        assertThat(memberService.findByName("Ramom")).isEqualTo(member6);
    }

    @Test
    public void update() {
        member1.setAge(35);
        assertThat(memberService.update(member1).orElseThrow().getAge()).isEqualTo(35);
    }

    @Test
    public void isMemberNewer() {
        assertThat(memberService.isMemberNewer(member2)).isEqualTo(false);
        assertThat(memberService.isMemberNewer(member1)).isEqualTo(false);
        assertThat(memberService.isMemberNewer(member3)).isEqualTo(true);
    }

    @Test
    public void chooseMemberByAgeGreaterThan() {
        assertThat(memberService.chooseMemberByAgeGreaterThan(100)).isEqualTo(Member.empty());
    }

    @Test
    public void givenNames_whenFindByName_thenReturnMembers() {
        Set<Member> actualMembers =  memberService.findByName(newHashSet("Ramom", "Andre", "Julia", "Filipe"));
        Set<Member> expectedMembers = newHashSet(member4, member5, member6);
        assertThat(actualMembers).isEqualTo(expectedMembers);
    }

    @Test
    public void givenNames_whenFindByName_thenReturnEmpty() {
        Set<Member> actualMembers =  memberService.findByName(newHashSet("Ramo1", "Andr2", "Juli3", "Filipe"));
        Set<Member> expectedMembers = newHashSet();
        assertThat(actualMembers).isEqualTo(expectedMembers);
    }
}
