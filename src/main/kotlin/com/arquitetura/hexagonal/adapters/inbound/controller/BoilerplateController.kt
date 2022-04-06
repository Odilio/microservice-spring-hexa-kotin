package com.arquitetura.hexagonal.adapters.inbound.controller

import com.arquitetura.hexagonal.adapters.dto.BoilerplateDTO
import com.arquitetura.hexagonal.adapters.dto.BoilerplateMapper
import com.arquitetura.hexagonal.adapters.mapper.Converter
import com.arquitetura.hexagonal.ports.`in`.BoilerplateServicePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/boilerplate")
class BoilerplateController {

    @Autowired
    private lateinit var service: BoilerplateServicePort

    @GetMapping(value = ["/v1/"])
    fun listarBoilerplate(@RequestParam search: String?, page: Pageable): Page<BoilerplateDTO> {
        val boilerplates = if (search?.isNotBlank() == true)
            service.listarFiltrandoTodosCampos(search, page).content
        else
            service.listarTodos(page).content

        return PageImpl(Converter.toCollection(boilerplates, BoilerplateDTO::class.java))
    }

    @GetMapping(value = ["/v1/{codigo}"])
    fun buscarBoilerplate(@PathVariable codigo: Long): ResponseEntity<BoilerplateDTO?> {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorCodigo(codigo)?.let {
            Converter.toModel(it, BoilerplateDTO::class.java)
        })
    }

    @PostMapping("/v1/")
    fun salvarBoilerplate(@RequestBody boilerplateDTO: BoilerplateDTO): ResponseEntity<BoilerplateDTO> {
        val boilerplate = Converter.toModel(boilerplateDTO, BoilerplateMapper::class.java)
        return ResponseEntity.status(HttpStatus.CREATED).body(Converter.toModel(service.salvarBoilerplate(boilerplate),
            BoilerplateDTO::class.java))
    }

    @DeleteMapping("/v1/{codigo}")
    @ResponseStatus(value = HttpStatus.OK)
    fun deletarBoilerplate(@PathVariable codigo: Long) {
        service.removerBoilerplate(codigo)
    }
}
