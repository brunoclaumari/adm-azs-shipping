package com.admazsshipping.fretes.entities.enums;

public enum EnumTipoEndereco {
	
	ORIGEM("origem"),
    DESTINO("destino");
    

    private String descricao;

    EnumTipoEndereco(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
