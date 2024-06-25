package com.example.projeto2;

public class Nascentes {
    private String Aspecto;
    private String Vegetacao;
    private String AddressText;

    public Nascentes() {
    }

    public Nascentes(String aspecto, String vegetacao, String addressText) {
        this.Aspecto = aspecto;
        this.Vegetacao = vegetacao;
        this.AddressText = addressText;
    }

    public String getAspecto() {
        return Aspecto;
    }

    public String getVegetacao() {
        return Vegetacao;
    }

    public String getAddressText() {
        return AddressText;
    }
}
