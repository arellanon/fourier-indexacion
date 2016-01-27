package fourier.bd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	   /**Parametros de conexion*/
	   static String bd = "fourier";
	   static String login = "root";
	   static String password = "1234";
	   static String url = "jdbc:mysql://localhost/"+bd;
	 
	   Connection connection = null;
	 
	   /** Constructor de DbConnection */
	   public DbConnection() {
	      try{
	         //obtenemos el driver de para mysql
	         Class.forName("com.mysql.jdbc.Driver");
	         //obtenemos la conexión
	         connection = DriverManager.getConnection(url,login,password);
	 
	         if (connection!=null){
	            //System.out.println("Conexión a base de datos "+bd+" OK\n");
	         }
	      }
	      catch(SQLException e){
	         System.out.println(e);
	      }catch(ClassNotFoundException e){
	         System.out.println(e);
	      }catch(Exception e){
	         System.out.println(e);
	      }
	   }
	   /**Permite retornar la conexión*/
	   public Connection getConnection(){
	      return connection;
	   }
	 
	   public void desconectar() {
		   try {
			   connection.close();
		   } catch (SQLException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
	   }

	}