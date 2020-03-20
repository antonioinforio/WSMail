

import java.sql.SQLException;
import java.util.List;

import br.com.antonio.dao.PaisDao;
import br.com.antonio.modelo.Pais;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Pais> paises;
		try {
			paises = new PaisDao().FindAll();
			for (Pais p:paises) {
				
				System.out.println(p.getPais() + " " + p.getMoeda());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block!!!
			e.printStackTrace();
		}
		


	}

}
