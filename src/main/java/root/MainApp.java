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

		//φτιάχνω την πρόσοψη που θα βλέπει ο χρήστης στην κονσόλα πριν προχωρήσει στις επιλογές του
		while (true) {
			System.out.println("1. Αυτόματη ή χειροκίνητη αναζήτηση χώρας για top ειδήσεις!");
			System.out.println("2. Αναζήτηση με παραμέτρους για όλες τις ειδήσεις!");
			System.out.println("3. Έξοδος!");
			String userChoise = scanner.nextLine();
			if (userChoise.equals("1")) {
				System.out.println("Επέλεξες 1, αυτόματη ή χειροκίνητη αναζήτηση χώρας για top ειδήσεις!");
				topHeadlines();
			} else if (userChoise.equals("2")) {
				System.out.println(
						"Επέλεξες 2, θα δώσεις παραμέτρους για τη χειροκίνητη αναζήτηση μεταξύ όλων των ειδήσεων!");
				everythingNews();
			} else if (userChoise.equals("3")) {
				System.out.println("Επέλεξες 3, για έξοδο!");
				break;
			} else
				System.out.println("Λάθος επιλογή, ξαναγυρνάς στο αρχικό μενού!");

		}
	}

	// αποτελέσματα top-headlines
	private static void topHeadlines() {
		System.out.println("1. Αυτόματη επιλογή χώρας για top ειδήσεις!");
		System.out.println("2. Χειροκίνητη επιλογή χώρας για top ειδήσεις!");
		String userChoise = scanner.nextLine();
		if (userChoise.equals("1")) {
			System.out.println("Η αυτόματη αναζήτηση χώρας για top ειδήσεις φέρνει");
			try {
				List<NewsInfo> news = service.getTopHeadlinesNews(
						new TopHeadlinesRequest(service.getUserLocation().getCountryCode().toLowerCase(), null));
				System.out.println("Αριθμός άρθρων: (" + news.size() + "):");
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
					"Θα γίνει χειροκίνητη αναζήτηση χώρας ή κατηγορίας για top ειδήσεις, δε χρειάζεται να συμπληρωθούν απαραιτήτως όλα τα πεδία!");
			System.out.println("Δώσε χώρα! (Πχ GR)");
			String countryChoise = scanner.nextLine().toLowerCase();
			System.out.println(
					"Δώσε κατηγορία (Πχ business, entertainment, general, health, science, sports, technology");
			String categoryChoise = scanner.nextLine().toLowerCase();
			List<NewsInfo> news;
			try {
				news = service.getTopHeadlinesNews(new TopHeadlinesRequest(countryChoise, categoryChoise));
				System.out.println("Αριθμός άρθρων: (" + news.size() + "):");
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
			System.out.println("Λάθος επιλογή, ξαναγυρνάς στο αρχικό μενού!");

	}

	// αποτελέσματα everything-news
	private static void everythingNews() {
		System.out.println(
				"Θα γίνει χειροκίνητη αναζήτηση με παραμέτρους μεταξύ όλων των ειδήσεων, δε χρειάζεται να συμπληρωθούν απαραιτήτως όλα τα πεδία!");
		System.out.println("Δώσε q! (Δηλ το keyword της αναζήτησης)");
		String keywordChoise = scanner.nextLine().toLowerCase();
		System.out.println("Δώσε searchIN! (Ενα εκ των title, sescription, content)");
		String searchINChoise = scanner.nextLine().toLowerCase();
		System.out.println("Δώσε sources!");
		String sourcesChoise = scanner.nextLine().toLowerCase();
		System.out.println("Δώσε language! (Πχ GR)");
		String languageChoise = scanner.nextLine().toLowerCase();
		System.out.println("Δώσε from! (Μορφή YYYY-MM-DD)");
		String fromChoise = scanner.nextLine().toLowerCase();
		System.out.println("Δώσε to! (Μορφή YYYY-MM-DD)");
		String toChoise = scanner.nextLine().toLowerCase();
		List<NewsInfo> News;
		try {
			News = service.getEverythingNews(new EverythingRequest(keywordChoise, searchINChoise, sourcesChoise,
					languageChoise, fromChoise, toChoise));
			System.out.println("Αριθμός άρθρων: (" + News.size() + "):");
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
