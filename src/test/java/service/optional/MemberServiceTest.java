package service.optional;
import org.junit.Before;
import org.junit.Test;
import model.Member;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.RandomStringUtils.*;

public class MemberServiceTest {

    private final Member member1 = new Member(
                Long.valueOf(randomNumeric(1,3)), randomAlphabetic(8),
            50);


    private final Member member2 = new Member(
            Long.valueOf(randomNumeric(1,3)), randomAlphabetic(8),
            Integer.valueOf(randomNumeric(1,2)));


    private final Member member3 = new Member(
            Long.valueOf(randomNumeric(1,3)), randomAlphabetic(8),
            Integer.valueOf(randomNumeric(1,2)));


    private final Member member4 = new Member(
            Long.valueOf(randomNumeric(1,3)), randomAlphabetic(8),
            Integer.valueOf(randomNumeric(1,2)));


    private final Member member5     = new Member(
            Long.valueOf(randomNumeric(1,3)), randomAlphabetic(8),
            Integer.valueOf(randomNumeric(1,2)));

    private final Member member6     = new Member(
            Long.valueOf(randomNumeric(1,3)), "Ramom",
            Integer.valueOf(randomNumeric(1,2)));

    private MemberService memberService;


    @Before
    public void setUp(){
        memberService = new MemberService(newHashSet(member1, member2, member3, member4, member5, member6));
    }

    @Test
    public void findByName() {
        assertThat(memberService.findByName("Andre")).isEqualTo(Optional.empty());
        assertThat(memberService.findByName("Ramom").orElseThrow()).isEqualTo(member6);
    }

    @Test
    public void update() {
        member1.setAge(35);
        assertThat(memberService.update(member1).orElseThrow().getAge()).isEqualTo(35);
    }
}
