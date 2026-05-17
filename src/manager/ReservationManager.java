package manager;

import model.Member;
import model.Movie;
import model.Reservation;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 영화 예매, 회원별 예매 내역, 영화별 통계 및 매출을 관리하는 클래스
 */
public class ReservationManager {
    // 회원 ID별로 여러 개의 예매 정보를 묶어서 관리하는 구조 (1대다 관계)
    private HashMap<String, ArrayList<Reservation>> reservationMap = new HashMap<>();
    // 영화 제목별 누적 예매 인원 수를 저장하는 Map
    private HashMap<String, Integer> movieCountMap = new HashMap<>();
    // 프로그램 전체 누적 매출액
    private int totalRevenue = 0;

    /**
     * 1. 영화 예매 처리 메서드
     */
    public void reserveMovie(Member member, Movie movie, int people) {
        // 총 결제 금액 계산
        int totalPrice = movie.getPrice() * people;
        // 예매 객체 생성
        Reservation reservation = new Reservation(movie, people, totalPrice);

        // 해당 회원의 첫 예매라면 리스트를 새로 생성해주고, 기존 리스트가 있다면 가져와서 예매 정보 추가
        reservationMap.putIfAbsent(member.getId(), new ArrayList<>());
        reservationMap.get(member.getId()).add(reservation);

        // 영화별 누적 예매 인원 갱신 (기존 수치가 없으면 0에서 시작하여 인원 추가)
        movieCountMap.put(movie.getTitle(), movieCountMap.getOrDefault(movie.getTitle(), 0) + people);

        // 전체 총 매출 누적
        totalRevenue += totalPrice;

        System.out.println("\n예매 완료!");
        System.out.println(member.getName() + "님이 " + movie.getTitle() + " " + people + "명 예매했습니다.");
    }

    /**
     * 2. 회원별 예매 내역 조회 메서드
     */
    public void displayMemberReservations(Member member) {
        System.out.println("\n[" + member.getName() + "님의 예매 내역]");
        // 회원의 ID로 예매 리스트를 찾음
        ArrayList<Reservation> list = reservationMap.get(member.getId());

        if (list == null || list.isEmpty()) {
            System.out.println("예매 내역이 없습니다.");
            return;
        }

        // 해당 회원이 예매한 모든 내역 출력
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getReservationInfo());
        }
    }

    /**
     * 3. 영화별 예매 인원 통계 조회 메서드
     */
    public void displayMovieReservationCounts() {
        System.out.println("\n[영화별 예매 인원]");
        if (movieCountMap.isEmpty()) {
            System.out.println("예매된 영화가 없습니다.");
            return;
        }
        for (String title : movieCountMap.keySet()) {
            System.out.println(title + ": " + movieCountMap.get(title) + "명");
        }
    }

    /**
     * 4. 총 매출 조회 메서드
     */
    public void displayTotalRevenue() {
        System.out.println("\n[총 매출 내역]");
        System.out.println("현재 총 매출: " + totalRevenue + "원");
    }
}