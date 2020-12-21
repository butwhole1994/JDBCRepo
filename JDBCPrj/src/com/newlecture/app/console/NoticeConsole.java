package com.newlecture.app.console;

import java.util.List;
import java.util.Scanner;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService;

public class NoticeConsole {

	private NoticeService service;
	private int page;
	private String searchWord;
	private String searchField;
	
	public NoticeConsole() {
		service = new NoticeService();
		page = 1;
		searchWord = "";
		searchField = "";
	}
	
	public void printNoticeList() throws Exception {
		List<Notice> list = service.getList(page, searchField, searchWord);
		int count = service.getCount();
		int lastPage = count/10;
		lastPage = count%10 > 0?lastPage+1:lastPage;
		System.out.println("────────────────────────────────────────");
		System.out.printf("<공지사항> 총 %d게시글\n", count);
		System.out.println("────────────────────────────────────────");
		for(Notice n : list) {
			System.out.printf("%d. %s / %s / %s\n",
					n.getId(),
					n.getTitle(),
					n.getWriterId(),
					n.getRegDate());
		}
		System.out.println("────────────────────────────────────────");
		System.out.printf("              %d / %d pages\n", page, lastPage);
	}          

	public int inputNoticeMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.printf("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.검색/ 6.종료 >");
		String menu_ = sc.nextLine();
		int menu = Integer.parseInt(menu_);
		return menu;
	}

	public void movePrevList() {
		if(page == 1) {
			System.out.println("===================");
			System.out.println("[이전 페이지가 없습니다.]");
			System.out.println("===================");
			return;
		}
		page--;
	}

	public void moveNextList() throws Exception {
		int count = service.getCount();
		int lastPage = count/10;
		lastPage = count%10>0?lastPage+1:lastPage;
		if(page == lastPage) {
			System.out.println("===================");
			System.out.println("[다음 페이지가 없습니다.]");
			System.out.println("===================");
			return;
		}
		page++;
		
	}

	public void inputSearchWord() {
		Scanner sc = new Scanner(System.in);
		System.out.println("검색 범주(title/content/writerId)중에 하나를 입력하세요.");
		System.out.println(" > ");
		searchField = sc.nextLine();
		
		System.out.println("검색어 > ");
		searchWord = sc.nextLine();
	}

}
