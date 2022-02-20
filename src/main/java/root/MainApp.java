package root;

import java.util.List;
import java.util.Scanner;

import exception.NewsAPIException;
import model.EverythingRequest;
import model.NewsInfo;
import model.TopHeadlinesRequest;
import services.NewsAPIService;

public class MainApp {

	static Scanner scanner = new Scanner(System.in);

	static NewsAPIService service = new NewsAPIService("https://newsapi.org/", "1a6714409f4e416eb1ba446037c34f42");

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//������� ��� ������� ��� �� ������ � ������� ���� ������� ���� ���������� ���� �������� ���
		while (true) {
			System.out.println("1. �������� � ����������� ��������� ����� ��� top ��������!");
			System.out.println("2. ��������� �� ����������� ��� ���� ��� ��������!");
			System.out.println("3. ������!");
			String userChoise = scanner.nextLine();
			if (userChoise.equals("1")) {
				System.out.println("�������� 1, �������� � ����������� ��������� ����� ��� top ��������!");
				topHeadlines();
			} else if (userChoise.equals("2")) {
				System.out.println(
						"�������� 2, �� ������ ����������� ��� �� ����������� ��������� ������ ���� ��� ��������!");
				everythingNews();
			} else if (userChoise.equals("3")) {
				System.out.println("�������� 3, ��� �����!");
				break;
			} else
				System.out.println("����� �������, ���������� ��� ������ �����!");

		}
	}

	// ������������ top-headlines
	private static void topHeadlines() {
		System.out.println("1. �������� ������� ����� ��� top ��������!");
		System.out.println("2. ����������� ������� ����� ��� top ��������!");
		String userChoise = scanner.nextLine();
		if (userChoise.equals("1")) {
			System.out.println("� �������� ��������� ����� ��� top �������� ������");
			try {
				List<NewsInfo> news = service.getTopHeadlinesNews(
						new TopHeadlinesRequest(service.getUserLocation().getCountryCode().toLowerCase(), null));
				System.out.println("������� ������: (" + news.size() + "):");
				for (NewsInfo info : news) {
					System.out.println(info.getTitle());
					System.out.println(info.getDescription());
					System.out.println(info.getUrl());
					System.out.println(info.getPublish_date());
				}

			} catch (NewsAPIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (userChoise.equals("2")) {
			System.out.println(
					"�� ����� ����������� ��������� ����� � ���������� ��� top ��������, �� ���������� �� ������������ ����������� ��� �� �����!");
			System.out.println("���� ����! (�� GR)");
			String countryChoise = scanner.nextLine().toLowerCase();
			System.out.println(
					"���� ��������� (�� business, entertainment, general, health, science, sports, technology");
			String categoryChoise = scanner.nextLine().toLowerCase();
			List<NewsInfo> news;
			try {
				news = service.getTopHeadlinesNews(new TopHeadlinesRequest(countryChoise, categoryChoise));
				System.out.println("������� ������: (" + news.size() + "):");
				for (NewsInfo info : news) {
					System.out.println(info.getTitle());
					System.out.println(info.getDescription());
					System.out.println(info.getUrl());
					System.out.println(info.getPublish_date());
				}
			} catch (NewsAPIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else
			System.out.println("����� �������, ���������� ��� ������ �����!");

	}

	// ������������ everything-news
	private static void everythingNews() {
		System.out.println(
				"�� ����� ����������� ��������� �� ����������� ������ ���� ��� ��������, �� ���������� �� ������������ ����������� ��� �� �����!");
		System.out.println("���� q! (��� �� keyword ��� ����������)");
		String keywordChoise = scanner.nextLine().toLowerCase();
		System.out.println("���� searchIN! (��� �� ��� title, sescription, content)");
		String searchINChoise = scanner.nextLine().toLowerCase();
		System.out.println("���� sources!");
		String sourcesChoise = scanner.nextLine().toLowerCase();
		System.out.println("���� language! (�� GR)");
		String languageChoise = scanner.nextLine().toLowerCase();
		System.out.println("���� from! (����� YYYY-MM-DD)");
		String fromChoise = scanner.nextLine().toLowerCase();
		System.out.println("���� to! (����� YYYY-MM-DD)");
		String toChoise = scanner.nextLine().toLowerCase();
		List<NewsInfo> News;
		try {
			News = service.getEverythingNews(new EverythingRequest(keywordChoise, searchINChoise, sourcesChoise,
					languageChoise, fromChoise, toChoise));
			System.out.println("������� ������: (" + News.size() + "):");
			for (NewsInfo info : News) {
				System.out.println(info.getTitle());
				System.out.println(info.getDescription());
				System.out.println(info.getUrl());
				System.out.println(info.getPublish_date());
			}

		} catch (NewsAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
