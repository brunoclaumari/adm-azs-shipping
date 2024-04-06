package com.admazsshipping.fretes.entities.enums;

public enum EnumTipoCarga {
	
	POR_CUBAGEM("CUBAGEM"),
    POR_PESO("PESO");
    

    private String descricao;

    EnumTipoCarga(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
