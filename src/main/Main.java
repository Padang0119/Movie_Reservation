package main;

import manager.MemberManager;
import manager.MovieManager;
import manager.ReservationManager;
import model.Member;
import model.Movie;
import java.util.Scanner;

/**
 * 프로그램의 흐름을 제어하고 사용자 조작 메뉴 인터페이스를 제공하는 실행 클래스
 */
public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);

        // 각 담당 기능을 수행할 매니저 인스턴스 생성
        MovieManager movieMgr = new MovieManager();
        MemberManager memberMgr = new MemberManager();
        ReservationManager resMgr = new ReservationManager();

        // 프로그램 메인 루프 실행 (0을 누르기 전까지 무한 반복)
        while (true) {
            System.out.println("\n===== 영화 예매 관리 시스템 =====");
            System.out.println("1. 영화 등록");
            System.out.println("2. 영화 전체 조회");
            System.out.println("3. 영화 검색");
            System.out.println("4. 회원 등록");
            System.out.println("5. 회원 전체 조회");
            System.out.println("6. 영화 예매");
            System.out.println("7. 회원별 예매 내역 조회");
            System.out.println("8. 영화별 예매 인원 조회");
            System.out.println("9. 총 매출 조회");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택: ");

            String choice = sc.nextLine(); // 메뉴 번호 입력받기

            switch (choice) {
                case "1": // 영화 등록 기능
                    System.out.println("\n[영화 등록을 시작합니다]");
                    System.out.print("영화 제목 입력: ");
                    String title = sc.nextLine();
                    System.out.print("장르 입력: ");
                    String genre = sc.nextLine();
                    System.out.print("상영 시간 입력 (예: 19:00): ");
                    String time = sc.nextLine();

                    int price;
                    // 티켓 가격 입력 예외 처리 루프
                    while (true) {
                        try {
                            System.out.print("티켓 가격 입력 (숫자만): ");
                            price = Integer.parseInt(sc.nextLine());
                            break; // 정수로 잘 입력되었다면 입력 루프 탈출
                        } catch (NumberFormatException e) {
                            System.out.println("잘못된 입력입니다. 가격은 숫자만 입력해주세요.");
                        }
                    }
                    // 무비 매니저를 통해 영화 등록 수행
                    movieMgr.registerMovie(title, genre, time, price);
                    break;

                case "2": // 영화 전체 조회 기능
                    movieMgr.displayAllMovies();
                    break;

                case "3": // 영화 개별 검색 기능
                    System.out.print("검색할 영화 제목을 입력하세요: ");
                    movieMgr.searchMovie(sc.nextLine());
                    break;

                case "4": // 회원 등록 기능
                    System.out.println("\n[회원 등록을 시작합니다]");
                    System.out.print("회원 ID 입력: ");
                    String id = sc.nextLine();
                    System.out.print("이름 입력: ");
                    String name = sc.nextLine();
                    System.out.print("전화번호 입력 (예: 010-1234-5678): ");
                    String phone = sc.nextLine();

                    // 멤버 매니저를 통해 회원 등록 수행
                    memberMgr.registerMember(id, name, phone);
                    break;

                case "5": // 회원 전체 조회 기능
                    memberMgr.displayAllMembers();
                    break;

                case "6": // 영화 예매 기능
                    System.out.println("\n[영화 예매를 시작합니다]");
                    System.out.print("회원 ID를 입력하세요: ");
                    String resId = sc.nextLine();
                    // 회원 ID 검증 및 객체 확보
                    Member member = memberMgr.getMember(resId);
                    if (member == null) {
                        System.out.println("존재하지 않는 회원입니다. 회원 등록을 먼저 해주세요.");
                        break;
                    }

                    System.out.print("예매할 영화 제목을 입력하세요: ");
                    String resTitle = sc.nextLine();
                    // 영화 제목 검증 및 객체 확보
                    Movie movie = movieMgr.getMovie(resTitle);
                    if (movie == null) {
                        System.out.println("존재하지 않는 영화입니다. 영화 등록 여부를 확인해주세요.");
                        break;
                    }

                    int people;
                    // 예매 인원수 입력 예외 처리 루프
                    while (true) {
                        try {
                            System.out.print("예매 인원 입력 (숫자만): ");
                            people = Integer.parseInt(sc.nextLine());
                            if (people <= 0) {
                                System.out.println("인원은 1명 이상이어야 합니다.");
                                continue;
                            }
                            break; // 검증이 통과되면 입력 루프 탈출
                        } catch (NumberFormatException e) {
                            System.out.println("잘못된 입력입니다. 인원은 숫자만 입력해주세요.");
                        }
                    }
                    // 확인된 회원 정보, 영화 정보, 인원을 기반으로 예매 진행
                    resMgr.reserveMovie(member, movie, people);
                    break;

                case "7": // 회원별 예매 내역 조회 기능
                    System.out.print("조회할 회원 ID를 입력하세요: ");
                    Member searchMember = memberMgr.getMember(sc.nextLine());
                    if (searchMember != null) {
                        resMgr.displayMemberReservations(searchMember);
                    } else {
                        System.out.println("존재하지 않는 회원입니다.");
                    }
                    break;

                case "8": // 영화별 누적 예매 인원 조회 기능
                    resMgr.displayMovieReservationCounts();
                    break;

                case "9": // 총 매출액 조회 기능
                    resMgr.displayTotalRevenue();
                    break;

                case "0": // 시스템 종료
                    System.out.println("프로그램을 종료합니다.");
                    sc.close(); // 스캐너 인스턴스 반환
                    return;     // 메인 메서드를 종료함으로써 무한 루프 탈출

                default:
                    System.out.println("잘못된 입력입니다. 메뉴 번호(0~9)를 다시 선택해주세요.");
            }
        }
    }
}