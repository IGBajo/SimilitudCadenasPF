package util;
import java.io.Serializable;

public class PersonaFisica implements Serializable {

	private static final long serialVersionUID = 2L;

	protected int id;
	protected String apellido1;
	protected String apellido2;
	protected String nombre;
	protected String fec_nac;
	
	protected String dni;
	protected String civitas;
	protected String direccion;
	protected int provincia;
	protected int municipio;
	
	
	protected String ccaa;
	protected String provincia_den;
	protected String municipio_den;
	protected int calle;
	protected int numero;
	protected String piso_letra;
	protected int cp;
	protected String telefono;
	protected String fax;
	protected String correo_e;
	protected String web;
	protected String sexo;
	protected int nacionalidad;
	protected String nacionalidad_den;
	protected String baja_fecha;
	protected String baja_causa;
	protected String provincia_municipio;
	protected String tipo_via;
	protected String tipo_via_denominacion;
	protected String nombre_via;
	protected String numeroc;
	protected String via_id;
	protected String bis;
	protected String portal;
	protected String escalera;
	protected String piso;
	protected String puerta;    
    
    public PersonaFisica() {
        super();
    }

    /**
     * @return El valor del campo 'id'
     */
    public int getId() {
        return id;
    }

    /**
     * @param _id Valor a asignar al campo 'id'
     */
    public void setId(int _id) {
        id = _id;
    }

    /**
     * @return El valor del campo 'dni'
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param _dni Valor a asignar al campo 'dni'
     */
    public void setDni(String _dni) {
        dni = _dni;
    }

    
    /**
     * @return El valor del campo 'nombre'
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param _nombre Valor a asignar al campo 'nombre'
     */
    public void setNombre(String _nombre) {
        nombre = _nombre;
    }

    /**
     * @return El valor del campo 'apellido1'
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param _apellido1 Valor a asignar al campo 'apellido1'
     */
    public void setApellido1(String _apellido1) {
        apellido1 = _apellido1;
    }

    /**
     * @return El valor del campo 'apellido2'
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param _apellido2 Valor a asignar al campo 'apellido2'
     */
    public void setApellido2(String _apellido2) {
    	if (_apellido2==null) {
    		apellido2="";
    	}
        apellido2 = _apellido2;
    }

    /**
     * @return El valor del campo 'dni'
     */
    public String getFecNac() {
        return fec_nac;
    }

    /**
     * @param _dni Valor a asignar al campo 'dni'
     */
    public void setFecNac(String _fec_nac) {
    	if (_fec_nac==null) {
    		fec_nac="";
    	}
    	fec_nac = _fec_nac;
    }
    
    /**
     * @return El valor del campo 'ccaa'
     */
    public String getCcaa() {
        return ccaa;
    }

    /**
     * @param _ccaa Valor a asignar al campo 'ccaa'
     */
    public void setCcaa(String _ccaa) {
        ccaa = _ccaa;
    }

    /**
     * @return El valor del campo 'provincia'
     */
    public int getProvincia() {
        return provincia;
    }

    /**
     * @param _provincia Valor a asignar al campo 'provincia'
     */
    public void setProvincia(int _provincia) {
        provincia = _provincia;
    }

    /**
     * @return El valor del campo 'provincia_den'
     */
    public String getProvincia_den() {
        return provincia_den;
    }

    /**
     * @param _provincia_den Valor a asignar al campo 'provincia_den'
     */
    public void setProvincia_den(String _provincia_den) {
        provincia_den = _provincia_den;
    }

    /**
     * @return El valor del campo 'municipio'
     */
    public int getMunicipio() {
        return municipio;
    }

    /**
     * @param _municipio Valor a asignar al campo 'municipio'
     */
    public void setMunicipio(int _municipio) {
        municipio = _municipio;
    }

    /**
     * @return El valor del campo 'municipio_den'
     */
    public String getMunicipio_den() {
        return municipio_den;
    }

    /**
     * @param _municipio_den Valor a asignar al campo 'municipio_den'
     */
    public void setMunicipio_den(String _municipio_den) {
        municipio_den = _municipio_den;
    }

    /**
     * @return El valor del campo 'direccion'
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param _direccion Valor a asignar al campo 'direccion'
     */
    public void setDireccion(String _direccion) {
        direccion = _direccion;
    }

    /**
     * @return El valor del campo 'calle'
     */
    public int getCalle() {
        return calle;
    }

    /**
     * @param _calle Valor a asignar al campo 'calle'
     */
    public void setCalle(int _calle) {
        calle = _calle;
    }

    /**
     * @return El valor del campo 'numero'
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param _numero Valor a asignar al campo 'numero'
     */
    public void setNumero(int _numero) {
        numero = _numero;
    }

    /**
     * @return El valor del campo 'piso_letra'
     */
    public String getPiso_letra() {
        return piso_letra;
    }

    /**
     * @param _piso_letra Valor a asignar al campo 'piso_letra'
     */
    public void setPiso_letra(String _piso_letra) {
        piso_letra = _piso_letra;
    }

    /**
     * @return El valor del campo 'cp'
     */
    public int getCp() {
        return cp;
    }

    /**
     * @param _cp Valor a asignar al campo 'cp'
     */
    public void setCp(int _cp) {
        cp = _cp;
    }

    /**
     * @return El valor del campo 'telefono'
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param _telefono Valor a asignar al campo 'telefono'
     */
    public void setTelefono(String _telefono) {
        telefono = _telefono;
    }

    /**
     * @return El valor del campo 'fax'
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param _fax Valor a asignar al campo 'fax'
     */
    public void setFax(String _fax) {
        fax = _fax;
    }

    /**
     * @return El valor del campo 'correo_e'
     */
    public String getCorreo_e() {
        return correo_e;
    }

    /**
     * @param _correo_e Valor a asignar al campo 'correo_e'
     */
    public void setCorreo_e(String _correo_e) {
        correo_e = _correo_e;
    }

    /**
     * @return El valor del campo 'web'
     */
    public String getWeb() {
        return web;
    }

    /**
     * @param _web Valor a asignar al campo 'web'
     */
    public void setWeb(String _web) {
        web = _web;
    }

    /**
     * @return El valor del campo 'sexo'
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param _sexo Valor a asignar al campo 'sexo'
     */
    public void setSexo(String _sexo) {
        sexo = _sexo;
    }

    /**
     * @return El valor del campo 'nacionalidad'
     */
    public int getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param _nacionalidad Valor a asignar al campo 'nacionalidad'
     */
    public void setNacionalidad(int _nacionalidad) {
        nacionalidad = _nacionalidad;
    }

    /**
     * @return El valor del campo 'nacionalidad_den'
     */
    public String getNacionalidad_den() {
        return nacionalidad_den;
    }

    /**
     * @param _nacionalidad_den Valor a asignar al campo 'nacionalidad_den'
     */
    public void setNacionalidad_den(String _nacionalidad_den) {
        nacionalidad_den = _nacionalidad_den;
    }

    /**
     * @return El valor del campo 'cip_civitas'
     */
    public String getCip_civitas() {
        return civitas;
    }

    /**
     * @param _cip_civitas Valor a asignar al campo 'cip_civitas'
     */
    public void setCip_civitas(String _civitas) {
        civitas = _civitas;
    }

    /**
     * @return El valor del campo 'baja_fecha'
     */
    public String getBaja_fecha() {
        return baja_fecha;
    }

    /**
     * @param _baja_fecha Valor a asignar al campo 'baja_fecha'
     */
    public void setBaja_fecha(String _baja_fecha) {
        baja_fecha = _baja_fecha;
    }

    /**
     * @return El valor del campo 'baja_causa'
     */
    public String getBaja_causa() {
        return baja_causa;
    }

    /**
     * @param _baja_causa Valor a asignar al campo 'baja_causa'
     */
    public void setBaja_causa(String _baja_causa) {
        baja_causa = _baja_causa;
    }

	/**
	 * @return the provincia_municipio
	 */
	public String getProvincia_municipio() {
		return provincia_municipio;
	}

	/**
	 * @param _provincia_municipio the provincia_municipio to set
	 */
	public void setProvincia_municipio(String _provincia_municipio) {
		provincia_municipio = _provincia_municipio;
	}

	/**
	 * @return the tipo_via
	 */
	public String getTipo_via() {
		return tipo_via;
	}

	/**
	 * @param _tipo_via the tipo_via to set
	 */
	public void setTipo_via(String _tipo_via) {
		tipo_via = _tipo_via;
	}

	/**
	 * @return the tipo_via_denominacion
	 */
	public String getTipo_via_denominacion() {
		return tipo_via_denominacion;
	}

	/**
	 * @param _tipo_via_denominacion the tipo_via_denominacion to set
	 */
	public void setTipo_via_denominacion(String _tipo_via_denominacion) {
		tipo_via_denominacion = _tipo_via_denominacion;
	}

	/**
	 * @return the nombre_via
	 */
	public String getNombre_via() {
		return nombre_via;
	}

	/**
	 * @param _nombre_via the nombre_via to set
	 */
	public void setNombre_via(String _nombre_via) {
		nombre_via = _nombre_via;
	}

	/**
	 * @return the numeroc
	 */
	public String getNumeroc() {
		return numeroc;
	}

	/**
	 * @param _numeroc the numeroc to set
	 */
	public void setNumeroc(String _numeroc) {
		numeroc = _numeroc;
	}

	/**
	 * @return the via_id
	 */
	public String getVia_id() {
		return via_id;
	}

	/**
	 * @param _via_id the via_id to set
	 */
	public void setVia_id(String _via_id) {
		via_id = _via_id;
	}

	/**
	 * @return the bis
	 */
	public String getBis() {
		return bis;
	}

	/**
	 * @param _bis the bis to set
	 */
	public void setBis(String _bis) {
		bis = _bis;
	}

	/**
	 * @return the portal
	 */
	public String getPortal() {
		return portal;
	}

	/**
	 * @param _portal the portal to set
	 */
	public void setPortal(String _portal) {
		portal = _portal;
	}

	/**
	 * @return the escalera
	 */
	public String getEscalera() {
		return escalera;
	}

	/**
	 * @param _escalera the escalera to set
	 */
	public void setEscalera(String _escalera) {
		escalera = _escalera;
	}

	/**
	 * @return the piso
	 */
	public String getPiso() {
		return piso;
	}

	/**
	 * @param _piso the piso to set
	 */
	public void setPiso(String _piso) {
		piso = _piso;
	}

	/**
	 * @return the puerta
	 */
	public String getPuerta() {
		return puerta;
	}

	/**
	 * @param _puerta the puerta to set
	 */
	public void setPuerta(String _puerta) {
		puerta = _puerta;
	}

    
}

