package com.arquitetura.hexagonal.ports.`in`

import com.arquitetura.hexagonal.adapters.dto.BoilerplateDTO
import com.arquitetura.hexagonal.application.model.BoilerplateEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface BoilerplateServicePort {

    fun buscarPorCodigo(codigo: Long): BoilerplateDTO?

    fun listarTodos(spec: Specification<BoilerplateEntity>?, page: Pageable?): Page<BoilerplateDTO>

    fun listarFiltrandoTodosCampos(search: String?, page: Pageable?): Page<BoilerplateDTO>

    fun salvarBoilerplate(boilerplateDTO: BoilerplateDTO): BoilerplateDTO

    fun removerBoilerplate(id: Long)
}

