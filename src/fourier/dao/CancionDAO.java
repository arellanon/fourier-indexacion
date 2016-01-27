package fourier.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import fourier.bd.DbConnection;
import fourier.vo.CancionVO;


public class CancionDAO {

	public CancionVO agregarCancion(CancionVO cancion) {
	  DbConnection conn= new DbConnection();
	  String sql;
	  int nuevoIdCancion = 0;
	  try {
		  sql = "insert into canciones (nombre, url) values ( ?, ?)";
		  PreparedStatement query = (PreparedStatement) conn.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		  query.setString(1, cancion.getNombre() );
		  query.setString(2, cancion.getUrl() );
		  query.execute();
		  ResultSet keyResultSet = query.getGeneratedKeys();
		  if (keyResultSet.next()) {
			  nuevoIdCancion = (int) keyResultSet.getInt(1);
			  //System.out.println(nuevoIdCancion);
		  }
		  query.close();
		  conn.desconectar();
	  } catch (SQLException e) {
	            System.out.println(e.getMessage());
	  }
	  return this.consultarCancion(nuevoIdCancion);
	}
	
	public CancionVO consultarCancion(int idCancion) {
		CancionVO cancion= new CancionVO();
		DbConnection conn= new DbConnection();
		try {
			PreparedStatement query = (PreparedStatement) conn.getConnection().prepareStatement("select * from canciones where idCancion = ?");
			query.setInt(1, idCancion);
			ResultSet res = query.executeQuery();   
			if(res.next()){
				cancion.setIdCancion(Integer.parseInt(res.getString("idCancion")));
				cancion.setNombre(res.getString("nombre"));
				cancion.setUrl(res.getString("url"));
			}
			res.close();
			query.close();
			conn.desconectar();
		} catch (Exception e) {
			System.out.println("No se encontro el id");
		}
		return cancion; 
	}
	
	public ArrayList<CancionVO> ListarCanciones() {
		ArrayList<CancionVO> canciones= new ArrayList<CancionVO>();
		DbConnection conn= new DbConnection();
		try {
			PreparedStatement query = (PreparedStatement) conn.getConnection().prepareStatement("select * from canciones");
			ResultSet res = query.executeQuery();
			while(res.next()){
				CancionVO cancion= new CancionVO();
				cancion.setIdCancion(Integer.parseInt(res.getString("idCancion")));
				cancion.setNombre(res.getString("nombre"));
				cancion.setUrl(res.getString("url"));
				canciones.add(cancion);
			}
			res.close();
			query.close();
			conn.desconectar();
		} catch (Exception e) {
			System.out.println("No se encontro el id");
		}
		return canciones; 
	}
	
}
