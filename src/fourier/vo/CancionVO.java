package fourier.vo;

public class CancionVO {
	private Integer idCancion;
	private String nombre;
	private String url;

	 public Integer getIdCancion() {
	  return idCancion;
	 }

	 public void setIdCancion(Integer idCancion) {
	  this.idCancion = idCancion;
	 }
	 
	 public String getNombre() {
	  return nombre;
	 }

	 public void setNombre(String nombre) {
	  this.nombre = nombre;
	 }
	 
	 public String getUrl() {
		 return url;
	 }

	 public void setUrl(String url) {
		 this.url = url;
	 }
	
	 public String toString(){
		 String s;
		 s = "ID: " + Integer.toString( idCancion ) + " - nombre: " + nombre + " - url: " +url;
		 return s;
	 }
}
