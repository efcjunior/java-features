package service.optional;

import model.Member;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MemberService {

    private final Set<Member> members;

    public MemberService(Set<Member> members) {
        this.members = members;
    }

    public Member findByName(String name) {
        return members
                .stream()
                .filter(member -> member.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException());
    }

    public Optional<Member> update(Member memberChanged) {
        Optional<Member> foundMember =
                members
                        .stream()
                        .filter(member -> member.getId() == memberChanged.getId())
                        .findFirst();

        foundMember.ifPresent(member -> member = memberChanged);

        return foundMember;
    }

    public boolean isMemberNewer(Member member) {
        return !members.stream()
                .filter(m -> m.getId() != member.getId())
                .filter(m -> m.getAge() <= member.getAge())
                .sorted(Comparator.comparing(Member::getAge))
                .findFirst()
                .isPresent();
    }

    public Member chooseMemberByAgeGreaterThan(int age) {
        return members
                .stream()
                .filter(m -> m.getAge() >= age)
                .findAny()
                .orElse(Member.empty());
    }

}
