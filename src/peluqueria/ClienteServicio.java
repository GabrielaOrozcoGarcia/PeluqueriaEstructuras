package peluqueria;

/**
 * Entidad principal Peluqueria
 * Representa un cliente con turno asignado para un servicio.
 * @author Gabriela Orozco Garcia
 */
public class ClienteServicio {
    private String numeroTurno;   // identificador principal
    private String nombreCliente;
    private String tipoServicio;
    private String estado;        // PENDIENTE, ATENDIDO, CANCELADO

    public ClienteServicio(String numeroTurno, String nombreCliente, String tipoServicio) {
        this.numeroTurno = numeroTurno;
        this.nombreCliente = nombreCliente;
        this.tipoServicio = tipoServicio;
        this.estado = "PENDIENTE";
    }

    // Getters
    public String getNumeroTurno() { return numeroTurno; }
    public String getNombreCliente() { return nombreCliente; }
    public String getTipoServicio() { return tipoServicio; }
    public String getEstado() { return estado; }

    // Setters
    public void setNumeroTurno(String numeroTurno) { this.numeroTurno = numeroTurno; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Turno: " + numeroTurno
                + " | Cliente: " + nombreCliente
                + " | Servicio: " + tipoServicio
                + " | Estado: " + estado;
    }

    // Compara por numero de turno (identificador principal)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ClienteServicio otro = (ClienteServicio) obj;
        return this.numeroTurno.equalsIgnoreCase(otro.numeroTurno);
    }
}
