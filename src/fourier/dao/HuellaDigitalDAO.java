package fourier.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import fourier.bd.DbConnection;
import fourier.vo.HuellaDigitalVO;


public class HuellaDigitalDAO {

	public void agregarHuellaDigital(HuellaDigitalVO huellaDigital) {
	  DbConnection conn= new DbConnection();
	  String sql;
	  try {
		  sql = "insert into huellasDigitales (hash, idCancion, tiempo) values ( ?, ?, ?)";
		  PreparedStatement query = (PreparedStatement) conn.getConnection().prepareStatement(sql);
		  query.setLong(1, huellaDigital.getHash() );
		  query.setInt(2, huellaDigital.getIdCancion() );
		  query.setInt(3, huellaDigital.getTiempo() );
		  query.execute();
		  query.close();
		  conn.desconectar();
	  } catch (SQLException e) {
		  System.out.println(e.getMessage());
	  } finally {
		  if(conn!=null) {
			  conn.desconectar();			  
		  }
	  }
	}
	
	public ArrayList<HuellaDigitalVO> consultarHuellaDigital(long hash) {
		ArrayList<HuellaDigitalVO> huellasDigitales = new ArrayList<HuellaDigitalVO>();
		DbConnection conn= new DbConnection();
		try {
			PreparedStatement query = (PreparedStatement) conn.getConnection().prepareStatement("select * from huellasDigitales where hash = ?");
			query.setLong(1, hash);
			ResultSet res = query.executeQuery();   
			while(res.next()){
				HuellaDigitalVO huellaDigital = new HuellaDigitalVO();
				huellaDigital.setHash(Long.parseLong( res.getString("hash") ));
				huellaDigital.setIdCancion(Integer.parseInt(res.getString("idCancion")));
				huellaDigital.setTiempo(Integer.parseInt(res.getString("tiempo")));
				huellasDigitales.add(huellaDigital);
			}
			res.close();
			query.close();
			conn.desconectar();
		} catch (Exception e) {
			System.out.println("No se encontro el id");
		}
		return huellasDigitales;
	}

}
