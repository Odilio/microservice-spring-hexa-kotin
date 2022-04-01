package com.arquitetura.hexagonal.adapters.inbound.controller

import com.arquitetura.hexagonal.adapters.dto.BoilerplateDTO
import com.arquitetura.hexagonal.application.service.BoilerplateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
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
    private lateinit var service: BoilerplateService

    @GetMapping(value = ["/v1/"])
    fun listarBoilerplate(@RequestParam search: String?, page: Pageable?): Page<BoilerplateDTO?>? {
        return if (search?.isNotBlank() == true)
            service.listarFiltrandoTodosCampos(search, page)
        else
            service.listarTodos(null, page)
    }

    @GetMapping(value = ["/v1/{codigo}"])
    fun buscarBoilerplate(@PathVariable codigo: Long): ResponseEntity<BoilerplateDTO> {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorCodigo(codigo))
    }

    @PostMapping("/v1/")
    fun salvarBoilerplate(@RequestBody boilerplateDTO: BoilerplateDTO): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarBoilerplate(boilerplateDTO))
    }

    @DeleteMapping("/v1/{codigo}")
    @ResponseStatus(value = HttpStatus.OK)
    fun deletarBoilerplate(@PathVariable codigo: Long) {
        service.removerBoilerplate(codigo)
    }
}
