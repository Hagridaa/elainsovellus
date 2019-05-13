package elainkauppa.test;

public class ElainDAOTest {

	public static void main(String[] args) {
		ElainDAO elainDao = new ElainDAO();
		
		System.out.println(elainDao.findAll());
		// int id, String laji, String nimi, String kuvaus, double hinta
		Elain uusiElain = new Elain(6,"sammakko", "Jeppe","Kurainen ja limainen",100);
		
//		try {
//			elainDao.addNew(uusiElain);
//		} catch (SQLException e) {	
//			throw new RuntimeException(e);
//		}
		
		try {
			System.out.println(elainDao.find(6));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
//		try {
//			elainDao.removeElain(1);
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
	}

}
