/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author juanm
 */
public class ingreData {
    
    private Integer ingredienteId;
    private String nombreIngrediente;
    private Integer stockIngredinte;
    

    public ingreData(Integer ingredienteId,
            String nombreIngrediente,
            Integer stockIngredinte) {

        this.ingredienteId = ingredienteId;
        this.nombreIngrediente = nombreIngrediente;
        this.stockIngredinte = stockIngredinte;
        

    }

    public Integer getIngredienteId() {
        return ingredienteId;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public Integer getStockIngredinte() {
        return stockIngredinte;
    }

    
}
