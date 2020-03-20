package br.com.antonio.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.inforio.util.Criptografia;

public class Conexao {
	/* Formato da String de conexão */
	private static final String FORMATO_URL_INTERBASE = "%s://%s/%s";
	private static final String FORMATO_URL_FIREBIRD  = "%s:%s/%s:%s"; 
	
	/* Dados da String de conexão */
	private static final String PROTOCOLO = PropertiesLoaderImpl.getValor("protocolo");
	private static final String IP = PropertiesLoaderImpl.getValor("ip");
	private static final String PORTA = PropertiesLoaderImpl.getValor("porta");
	private static final String PATH = PropertiesLoaderImpl.getValor("path");
	private static final String DRIVER_CLASS = PropertiesLoaderImpl.getValor("driverClass");
	
	/* Autenticação */
	private static final String USUARIO = "SYSDBA";
	private static final byte[] SENHA = Criptografia.StrToByte(PropertiesLoaderImpl.getValor("key"));

	public static Connection conectar() throws ClassNotFoundException, SQLException{ 
		
		try {
			final String urlConexao;
			
			switch(PROTOCOLO) {
				case "jdbc:interbase":
					urlConexao = String.format(FORMATO_URL_INTERBASE, PROTOCOLO, IP, PATH);
					break;
					
				case "jdbc:firebirdsql":
					urlConexao = String.format(FORMATO_URL_FIREBIRD, PROTOCOLO, IP, PORTA, PATH);
					break;
					
				default:
					urlConexao = String.format(FORMATO_URL_INTERBASE, "jdbc:interbase", IP, PATH);
					break;
			}
			
			Class.forName(DRIVER_CLASS);
			
			Connection conexao = DriverManager.getConnection(urlConexao, USUARIO, Criptografia.descriptografar(SENHA)); 
			conexao.setAutoCommit(false);
			
			return conexao;
			
		} catch (Exception e){ 
			System.out.println("Erro ao conectar: " + e.toString());
			return null; 
		} 
	}
	
	
	public static void main(String[] args) {
		try {
			Conexao.conectar();
			System.out.println(">>> pronto!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
