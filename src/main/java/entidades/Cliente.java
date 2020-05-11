/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author seryu
 */
public class Cliente {
    private Integer id;
    private String codigo;
    private String empresa;
    private String contacto;
    private String cargoContacto;
    private String direccion;
    private String ciudad;
    private String region;
    private String cp;
    private String pais;
    private String telefono;
    private String fax;

    public Cliente(Integer id, String codigo, String empresa, String contacto, String cargoContacto, String direccion, String ciudad, String region, String cp, String pais, String telefono, String fax) {
        this.id = id;
        this.codigo = codigo;
        this.empresa = empresa;
        this.contacto = contacto;
        this.cargoContacto = cargoContacto;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.region = region;
        this.cp = cp;
        this.pais = pais;
        this.telefono = telefono;
        this.fax = fax;
    }

    public Cliente() {
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCargoContacto() {
        return cargoContacto;
    }

    public void setCargoContacto(String cargoContacto) {
        this.cargoContacto = cargoContacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String toString() {
        String codigo1 = codigo;
        String empresa1 = empresa;
        String contacto1 = contacto;
        String cargoContacto1 = cargoContacto;
        String direccion1 = direccion;
        String ciudad1 = ciudad;
        String region1 = region;
        String cp1 = cp;
        String pais1 = pais;
        String telefono1 = telefono;
        String fax1 = fax;
        
        codigo1 = String.format("%" + 5 + "s", codigo1);
        empresa1 = String.format("%" + 18 + "s", empresa1);
        contacto1 = String.format("%" + 18 + "s", contacto1);
        cargoContacto1 = String.format("%" + 20 + "s", cargoContacto1);
        direccion1 = String.format("%" + 10 + "s", direccion1);
        ciudad1 = String.format("%" + 8 + "s", ciudad1);
        region1 = String.format("%" + 6 + "s", region1);
        cp1 = String.format("%" + 10 + "s", cp1);
        pais1 = String.format("%" + 10 + "s", pais1);
        telefono1 = String.format("%" + 10 + "s", telefono1);
        fax1 = String.format("%" + 10 + "s", fax1);
        
        
        String cadena = "|  %3d  |  %.5s  |  %.18s  |  %.18s  |  %.20s  |  %.10s  |  %.8s  |  %.6s  |  %.10s  |  %.10s  |  %.10s  |  %.10s  |";
        String format = String.format(cadena, id, codigo1, empresa1, contacto1, cargoContacto1, direccion1, ciudad1, region1, cp1, pais1, telefono1, fax1);
        return format;
    }
    
    
}
