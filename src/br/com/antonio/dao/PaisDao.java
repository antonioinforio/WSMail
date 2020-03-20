package br.com.antonio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.antonio.conexao.Conexao;
import br.com.antonio.modelo.Pais;

public class PaisDao {
	
	private Connection con;
	
	public PaisDao() throws ClassNotFoundException, SQLException{
		this.con = Conexao.conectar();
	}
	
	public List<Pais> FindAll() throws SQLException {
		List<Pais> paises = new ArrayList<Pais>();
		
		PreparedStatement statement = this.con.prepareStatement("select * from COUNTRY");
		
		ResultSet rs = statement.executeQuery();
		
		
		while (rs.next()) {
			
			Pais pais = new Pais();
			
			pais.setPais(rs.getString("COUNTRY"));
			pais.setMoeda(rs.getString("CURRENCY"));
			
			paises.add(pais);
			
			
		} 
		
		return paises;
		
	}

}
