package com.arquitetura.hexagonal.ports.`in`

import com.arquitetura.hexagonal.adapters.dto.BoilerplateMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BoilerplateServicePort {

    fun buscarPorCodigo(codigo: Long): BoilerplateMapper?

    fun listarTodos(page: Pageable): Page<BoilerplateMapper>

    fun listarFiltrandoTodosCampos(search: String?, page: Pageable): Page<BoilerplateMapper>

    fun salvarBoilerplate(boilerplate: BoilerplateMapper): BoilerplateMapper

    fun removerBoilerplate(id: Long)
}

