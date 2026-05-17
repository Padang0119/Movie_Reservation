package manager;

import model.Movie;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 영화 데이터의 등록, 전체 조회, 검색을 관리하는 클래스
 */
public class MovieManager {
    // 순차적인 전체 조회를 위한 ArrayList
    private ArrayList<Movie> movieList = new ArrayList<>();
    // 영화 제목으로 O(1)의 빠른 검색을 수행하기 위한 HashMap
    private HashMap<String, Movie> movieMap = new HashMap<>();

    /**
     * 1. 영화 등록 메서드
     */
    public void registerMovie(String title, String genre, String time, int price) {
        // 중복 검사: 이미 동일한 제목의 영화가 Map에 존재하는지 확인
        if (movieMap.containsKey(title)) {
            System.out.println("이미 등록된 영화 제목입니다.");
            return;
        }
        // 새로운 영화 객체 생성 후 리스트와 맵에 각각 저장
        Movie movie = new Movie(title, genre, time, price);
        movieList.add(movie);
        movieMap.put(title, movie);
        System.out.println("영화 등록이 완료되었습니다.");
    }

    /**
     * 2. 영화 전체 조회 메서드
     */
    public void displayAllMovies() {
        System.out.println("\n[영화 목록]");
        if (movieList.isEmpty()) {
            System.out.println("등록된 영화가 없습니다.");
            return;
        }
        // ArrayList의 인덱스를 활용하여 순서대로 출력
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println((i + 1) + ". " + movieList.get(i).toString());
        }
    }

    /**
     * 3. 영화 단건 검색 메서드
     */
    public void searchMovie(String title) {
        System.out.println("\n[검색 결과]");
        // HashMap에서 제목을 key로 사용해 객체를 빠르게 탐색
        Movie movie = movieMap.get(title);
        if (movie != null) {
            System.out.println("제목: " + movie.getTitle());
            System.out.println("장르: " + movie.getGenre());
            System.out.println("상영 시간: " + movie.getTime());
            System.out.println("가격: " + movie.getPrice() + "원");
        } else {
            System.out.println("해당 영화를 찾을 수 없습니다.");
        }
    }

    /**
     * 예매 시스템 등 외부 클래스에서 영화 객체를 참조할 수 있도록 제공하는 메서드
     */
    public Movie getMovie(String title) {
        return movieMap.get(title);
    }
}