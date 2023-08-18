package be.civaform.demorabbit.dto;

public class DemoDTO {

    private int code;
    private String libelle;

    public DemoDTO() {
    }

    public DemoDTO(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "DemoDTO{" +
                "code=" + code +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
