package App;

import java.sql.Date;

/**
 * Created by Shadow on 3/31/2017.
 */
public class Acceso {
    private String id;
    private java.sql.Date fecha;
    private boolean dentro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isDentro() {
        return dentro;
    }

    public void setDentro(boolean dentro) {
        this.dentro = dentro;
    }
}
