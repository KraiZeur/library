package application.businessLayer;

/**
 * 
 * @author Jeremy
 *
 */
public class TestEngine {

		public static void main(String[] args) {
			//DataInit.createTables();
		/*	Session session = DBConnection.getSession();
			session.beginTransaction();
			
			//create author
			Author author1 = new Author("biography","12-12-1992",new Name("Agatha","Christie"));
			session.save(author1);
			Author author2 = new Author("biography","25-11-1984",new Name("Emile","Zola"));
			session.save(author2);
			
			//create editor
			Editor editor1=new Editor();
			editor1.setName("1er editeur");
			session.save(editor1);
			
			//create collection
			BookSeries bookSeries1 =new BookSeries();
			bookSeries1.setName("1ère collection");
			session.save(bookSeries1);
			
			//create theme
			Theme theme1=new Theme();
			theme1.setName("Dessins animés");
			session.save(theme1);
			
			//create book
			Book book1= new Book (BookType.comics,"cover1","Roi lion","simba et pumba",1987,true);
			Book book2= new Book (BookType.novel,"cover2","Code Lyoko","on ira ,on saura, sauver notre existence",1992,false);
			Book book3= new Book (BookType.comics,"cover3","Batman","Batman_description",1974,true);
			Book book4= new Book (BookType.comics,"cover4","Pokemon","PIKACHUUU",1992,false);
			session.save(book1);
			session.save(book2);
			session.save(book3);
			session.save(book4);
			
			//create customer
			Customer customer1 = new Customer(new Name("Jeremy","Carayon"), new CustomerAdress("33","rue Lebon","Sartrouville",78500),"10-02-2014");
			session.save(customer1);
			
			//create Borrowing
			Borrowing borrowing1=new Borrowing(9.99,"10-02-2014","11-02-2014");
			session.save(borrowing1);
			
			//create Administrator
			Administrator administrator1 = new Administrator(new Login("Prénom_Nom","password"));
			session.save(administrator1);
			
			
			//operations allow to link table "Book" and others table
			book1.setAuthor(author1);
			book1.setEditor(editor1);
			book1.setBookSeries(bookSeries1);
			book1.setTheme(theme1);
			book2.setAuthor(author1);
			book2.setTheme(theme1);
			book3.setEditor(editor1);
			book3.setAuthor(author2);
			book4.setAuthor(author2);
			book4.setBookSeries(bookSeries1);
			book4.setTheme(theme1);
			
			//operations allow to link table "Borrowing" with others
			borrowing1.setBook(book1);
			borrowing1.setCustomer(customer1);
			
			session.getTransaction().commit();
			
			BookService bs = new BookService();
			//System.out.println("nombre de livre : " +bs.getDaoBook().count());
			

			
			session.close();
			*/
			// TEST 2 ( for class  DAOBook)
			 
				
				/*  Session session = DBConnection.getSession();
					session.beginTransaction();
					BookService bs = new BookService();
				List<Book> liste = bs.getDaoBook().getBooksByYear(1987);
					
					for(Book bk : liste) {
						System.out.println(bk.getName());
					}
					
					liste = bs.getDaoBook().getBooksByName("o");
					
					for(Book bk : liste) {
						System.out.println(bk.getName());
					}
					
					List<Book> liste1=bs.getDaoBook().getBooksByAuthor(2);
					for (Book br : liste1 ){
						System.out.println(br.getName());
					}
					
					List<Book> liste2=bs.getDaoBook().getBooksByParameters("Roi lion",1987,"comics","cover1","simba et pumba",true );
					
					for(Book br : liste2)
						System.out.println(br.getName());
					session.close();
					
			*/
			
					// TEST 3 ( for class DAOAuthor)	
					
					/*DAOAuthor daoAuthor = new DAOAuthor();
					Author author = new Author("biography","12-12-1992",new Name("Albert","Camus"));
					daoAuthor.create(author);*/
				/*	Session session = DBConnection.getSession();
					session.beginTransaction();
					AuthorService as = new AuthorService();
					List<Author> liste1 = as.getDaoAuthor().getAuthorByName("Agatha", "Christie");
					
					for(Author ar : liste1) {
						System.out.println(ar.getName());
					}
					
					List<Author> list=as.getDaoAuthor().getAuthorByParameters("Agatha","Christie","12-12-1992","biography");
					
					for(Author ar : list){
						System.out.println(ar.getName());
					}
					
					session.close();*/
					
					// TEST 4( for class BookSeries)
					
				/*	DAOBookSeries daoBookSeries = new DAOBookSeries();
					BookSeries bookSeries = new BookSeries ("2ème collection DAO");
					daoBookSeries.create(bookSeries); */
					
				/*	Session session = DBConnection.getSession();
					session.beginTransaction();
					BookSeriesService bss = new BookSeriesService();
					List<BookSeries> liste = bss.getDaoBookSeries().getBookSeriesByName("2");
					
					for(BookSeries bs : liste) {
						System.out.println(bs.getName());
					}
					
					session.close();
					*/
					
					// TEST 5 ( for class Editor)
					
					/*DAOEditor daoEditor = new DAOEditor();
					Editor editor = new Editor ("2ème editeur");
					daoEditor.create(editor);
					*/
					/*Session session = DBConnection.getSession();
					session.beginTransaction();
					EditorService es = new EditorService();
					List<Editor> liste = es.getDaoEditor().getEditorByName("1");
					
					for(Editor er : liste) {
						System.out.println(er.getName());
					}
					
					session.close();
					*/
					// TEST 6 ( for class Customer)
					
					/*DAOCustomer daoCustomer = new DAOCustomer();
					Customer customer = new Customer(new Name("Thomas","Ritaly"), new CustomerAdress("37","rue du MiniMarket","Sannois",78500),"17-08-2013");
					daoCustomer.create(customer);
					*/
				/*    Session session = DBConnection.getSession();
					session.beginTransaction();
					CustomerService cs = new CustomerService();
					List<Customer> liste = cs.getDaoCustomer().getCustomerByName("Jeremy","Carayon");
					
					for(Customer cr : liste) {
						System.out.println(cr.getName());
					}
					
					List<Customer> liste2= cs.getDaoCustomer().getCustomerByParameters("Jeremy","Carayon","33","rue Lebon","Sartrouville",78500,"10-02-2014");
					for(Customer cr:liste2){
						System.out.println(cr.getName());
					}
						*/		
					
					
					// TEST 7 ( for class Theme)
					
				/*	DAOTheme daoTheme = new DAOTheme();
					Theme theme = new Theme ("football");
					daoTheme.create(theme);*/
					
			/*		ThemeService ts = new ThemeService();
					List<Theme> list = ts.getDaoTheme().getThemeByName("foot");
					
					for(Theme te: list){
						System.out.println(te.getName());
					}
					*/
		}
	
}