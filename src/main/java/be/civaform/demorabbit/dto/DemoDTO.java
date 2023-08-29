package be.civaform.demorabbit.dto;

public class DemoDTO {

    private int code;
    private String libelle;

    private boolean erreur = false;
    private int essais = 0;

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

    public boolean isErreur() {
        return erreur;
    }

    public void setErreur(boolean erreur) {
        this.erreur = erreur;
    }

    public int getEssais() {
        return essais;
    }

    public void setEssais(int essais) {
        this.essais = essais;
    }

    @Override
    public String toString() {
        return "DemoDTO{" +
                "code=" + code +
                ", libelle='" + libelle + '\'' +
                ", erreur=" + erreur +
                ", essais=" + essais +
                '}';
    }
}
