package model;

/**
 * 영화 정보를 담는 데이터 클래스 (Value Object)
 */
public class Movie {
    private String title;  // 영화 제목
    private String genre;  // 장르
    private String time;   // 상영 시간
    private int price;  //영화 표 가격

    // 생성자: 영화 객체를 생성할 때 필드 값을 초기화
    public Movie(String title, String genre, String time, int price) {
        this.title = title;
        this.genre = genre;
        this.time = time;
        this.price = price;
    }

    // Getter 메서드들: 외부에서 필드 값을 읽을 수 있도록 제공
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getTime() { return time; }
    public int getPrice() { return price; }

    // toString 재정의: 객체 자체를 출력할 때 보기 좋은 문자열 포맷으로 변환
    @Override
    public String toString() {
        return title + " / " + genre + " / " + time + " / " + price + "원";
    }
}