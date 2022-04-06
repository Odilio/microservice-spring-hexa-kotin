package com.arquitetura.hexagonal.adapters.dto

import com.arquitetura.hexagonal.application.model.BoilerplateModel
import com.arquitetura.hexagonal.application.extensions.format
import java.util.*

data class BoilerplateDTO(

    var id: Long? = null,
    var nome: String? = null,
    var dataUltimaAtualizacao: String? = null,
) {
    constructor(boilerplate: BoilerplateModel) : this() {
        this.id = boilerplate.id
        this.nome = boilerplate.nome
        this.dataUltimaAtualizacao = boilerplate.dataUltimaAtualizacao?.format("yyyy-MM-dd HH:mm:ss")
    }

    fun toEntity(): BoilerplateModel {
        return BoilerplateModel(
            this.id,
            this.nome,
            Date()
        )
    }
}
