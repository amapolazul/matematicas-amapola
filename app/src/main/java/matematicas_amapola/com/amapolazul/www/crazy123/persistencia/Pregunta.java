package matematicas_amapola.com.amapolazul.www.crazy123.persistencia;

/**
 * Created by jsmartinez on 21/08/2015.
 */
public class Pregunta {

    private Long id;
    private String respuestaCorrecta;
    private int imagen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }


}
