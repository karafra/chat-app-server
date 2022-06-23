package karafra.chatapp.utils;

import org.springframework.stereotype.Component;

import karafra.chatapp.domain.chat.Member;

@Component
public class MemberComparatorImpl implements MemberComparator {

    @Override
    public int compare(Member o1, Member o2) {
        return o1.getUsername().compareTo(o2.getUsername());
    }

}
