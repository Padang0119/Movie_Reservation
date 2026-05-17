package model;

/**
 * 개별 예매 정보를 담는 데이터 클래스
 */
public class Reservation {
    private Movie movie;     // 예매한 영화 객체 (영화 정보 포함)
    private int people;      // 예매 인원 수
    private int totalPrice;  // 총 결제 금액 (영화 가격 * 인원 수)

    // 생성자: 예매 정보를 입력받아 저장
    public Reservation(Movie movie, int people, int totalPrice) {
        this.movie = movie;
        this.people = people;
        this.totalPrice = totalPrice;
    }

    // Getter 메서드들
    public Movie getMovie() { return movie; }
    public int getPeople() { return people; }
    public int getTotalPrice() { return totalPrice; }

    // 회원별 예매 내역을 출력할 때 사용할 커스텀 문자열 포맷
    public String getReservationInfo() {
        return movie.getTitle() + " / " + movie.getTime() + " / " + people + "명 / " + totalPrice + "원";
    }
}