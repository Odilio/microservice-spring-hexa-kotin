package com.arquitetura.hexagonal.application.service

import com.arquitetura.hexagonal.adapters.dto.BoilerplateDTO
import com.arquitetura.hexagonal.application.model.BoilerplateEntity
import com.arquitetura.hexagonal.ports.`in`.BoilerplateServicePort
import com.arquitetura.hexagonal.ports.out.BoilerplateRepositoryPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class BoilerplateService: BoilerplateServicePort{

    @Autowired
    private lateinit var boilerplateRepository: BoilerplateRepositoryPort

    override fun buscarPorCodigo(codigo: Long): BoilerplateDTO? {
        return boilerplateRepository.findByIdOrNull(codigo)?.let { BoilerplateDTO(it) }
    }

    override fun listarTodos(spec: Specification<BoilerplateEntity?>?, page: Pageable?): Page<BoilerplateDTO?>? {
        return boilerplateRepository.findAll(spec, page)?.map { BoilerplateDTO(it) }
    }

    override fun listarFiltrandoTodosCampos(search: String?, page: Pageable?): Page<BoilerplateDTO?>? {
        return boilerplateRepository.findAllFields(search, page)?.map { BoilerplateDTO(it!!) }
    }

    override fun salvarBoilerplate(boilerplateDTO: BoilerplateDTO): BoilerplateDTO {
        return BoilerplateDTO(boilerplateRepository.save(boilerplateDTO.toEntity()))
    }

    override fun removerBoilerplate(id: Long) {
        boilerplateRepository.deleteById(id)
    }
}
