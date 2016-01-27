package fourier.vo;

public class HuellaDigitalVO {
	private long hash;
	private int idCancion;
	private int tiempo;

	/*
	public HuellaDigitalVO(long hash, int idCancion, int tiempo) {
		this.hash = hash;
		this.idCancion = idCancion;
		this.tiempo = tiempo;
	}
	*/
		
	public long getHash() {
		return hash;
	}
	
	public void setHash(long hash) {
		this.hash = hash;
	}
	 
	public Integer getIdCancion() {
		return idCancion;
	}
	
	public void setIdCancion(Integer idCancion) {
		this.idCancion = idCancion;
	}
	 
	public Integer getTiempo() {
		return tiempo;
	}
	
	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}
	
	public String toString() {
		String s;
		s = "hash: " + Long.toString(hash) + " - IdCancion: " + Integer.toString(idCancion) + " - tiempo: " + Integer.toString(tiempo);
		return s;
	}
	 
}
