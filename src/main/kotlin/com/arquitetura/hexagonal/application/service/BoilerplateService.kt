package com.arquitetura.hexagonal.application.service

import com.arquitetura.hexagonal.adapters.dto.BoilerplateMapper
import com.arquitetura.hexagonal.adapters.mapper.Converter
import com.arquitetura.hexagonal.adapters.model.BoilerplateModel
import com.arquitetura.hexagonal.ports.`in`.BoilerplateServicePort
import com.arquitetura.hexagonal.ports.out.BoilerplatePersistencePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BoilerplateService: BoilerplateServicePort {

    @Autowired
    private lateinit var boilerplateAdapter: BoilerplatePersistencePort

    override fun buscarPorCodigo(codigo: Long): BoilerplateMapper? {
        return boilerplateAdapter.findByIdOrNull(codigo)?.let {
            Converter.toModel(it, BoilerplateMapper::class.java)
        }
    }

    override fun listarTodos( page: Pageable): Page<BoilerplateMapper> {
        val boilerplates = Converter.toCollection(boilerplateAdapter.findAll(page).content, BoilerplateMapper::class.java)

        return PageImpl(boilerplates)
    }

    override fun listarFiltrandoTodosCampos(search: String?, page: Pageable): Page<BoilerplateMapper> {
        val boilerplates = Converter.toCollection(boilerplateAdapter.findAllFields(search, page).content, BoilerplateMapper::class.java)

        return PageImpl(boilerplates)
    }

    override fun salvarBoilerplate(boilerplate: BoilerplateMapper): BoilerplateMapper {
        val boilerplateSalvo = boilerplateAdapter.save(boilerplate)
        return Converter.toModel(boilerplateSalvo, BoilerplateMapper::class.java)
    }

    override fun removerBoilerplate(id: Long) {
        boilerplateAdapter.deleteById(id)
    }
}