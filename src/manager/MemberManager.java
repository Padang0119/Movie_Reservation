package manager;

import model.Member;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 회원 데이터의 등록 및 조회를 관리하는 클래스
 */
public class MemberManager {
    // 회원 전체 목록 조회를 위한 ArrayList
    private ArrayList<Member> memberList = new ArrayList<>();
    // 회원 ID로 빠른 검색을 수행하기 위한 HashMap
    private HashMap<String, Member> memberMap = new HashMap<>();

    /**
     * 1. 회원 등록 메서드
     */
    public void registerMember(String id, String name, String phone) {
        // 중복 검사: 동일한 회원 ID가 이미 존재하는지 확인
        if (memberMap.containsKey(id)) {
            System.out.println("이미 존재하는 ID입니다.");
            return;
        }
        // 회원 객체를 생성하여 리스트와 맵에 저장
        Member member = new Member(id, name, phone);
        memberList.add(member);
        memberMap.put(id, member);
        System.out.println("회원 등록이 완료되었습니다.");
    }

    /**
     * 2. 회원 전체 조회 메서드
     */
    public void displayAllMembers() {
        System.out.println("\n[회원 목록]");
        if (memberList.isEmpty()) {
            System.out.println("등록된 회원이 없습니다.");
            return;
        }
        // 향상된 for문을 사용하여 리스트 내 모든 회원 정보 출력
        for (Member member : memberList) {
            System.out.println(member.toString());
        }
    }

    /**
     * 예매 시스템 등 외부 클래스에서 회원 객체를 참조할 수 있도록 제공하는 메서드
     */
    public Member getMember(String id) {
        return memberMap.get(id);
    }
}