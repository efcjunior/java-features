package service.optional;

import model.Member;

import java.util.Optional;
import java.util.Set;

public class MemberService {

    private final Set<Member> members;

    public MemberService(Set<Member> members) {
        this.members = members;
    }

    public Optional<Member> findByName(String name) {
        return members.stream().filter(member -> member.getName().equalsIgnoreCase(name)).findFirst();
    }

}
