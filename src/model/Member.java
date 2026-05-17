package model;

/**
 * 회원 정보를 담는 데이터 클래스 (Value Object)
 */
public class Member {
    private String id;     // 회원 ID (고유 식별자)
    private String name;   // 회원 이름
    private String phone;  // 전화번호

    // 생성자: 회원 객체를 생성할 때 필드 값을 초기화
    public Member(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    // Getter 메서드들: 외부에서 필드 값을 읽을 수 있도록 제공
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }

    // toString 재정의: 회원 정보 출력 포맷 설정
    @Override
    public String toString() {
        return "ID: " + id + " | 이름: " + name + " | 전화번호: " + phone;
    }
}