package com.arquitetura.hexagonal.adapters.outbound.integration

import com.arquitetura.hexagonal.application.model.BoilerplateModel
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.toEntityList
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

@Service
class BaseServiceIntegration(val webClient: WebClient) {

    @Value("\${url.ms-base}")
    private val url: String? = null

    fun findByIdOrNull(codigo: Long): BoilerplateModel? = webClient
            .get()
            .uri(UriComponentsBuilder
                .fromHttpUrl(url!!)
                .path("/base/v1/")
                    .queryParam("codigo", codigo)
                    .build()
                    .toUri())
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
            .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
            .toEntity(BoilerplateModel::class.java)
            .block()?.body

    fun deleteById(codigo: Long): BoilerplateModel? = webClient
            .get()
            .uri(UriComponentsBuilder
                .fromHttpUrl(url!!)
                .path("/base/v1/")
                    .queryParam("codigo", codigo)
                    .build()
                    .toUri())
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
            .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
            .toEntity(BoilerplateModel::class.java)
            .block()?.body

    fun findAll(page: Pageable): ResponseEntity<List<BoilerplateModel>>? = webClient
        .get()
        .uri(UriComponentsBuilder
            .fromHttpUrl(url!!)
            .path("base/v1/buscar")
               .queryParam("sort", page.sort)
               .queryParam("pageSize", page.pageSize)
               .queryParam("pageNumber", page.pageNumber)
            .build()
            .toUri())
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
        .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
        .toEntityList<BoilerplateModel>()
        .block()

    fun findAllFields(search: String?, page: Pageable): ResponseEntity<List<BoilerplateModel>>? = webClient
        .get()
        .uri(UriComponentsBuilder
            .fromHttpUrl(url!!)
            .path("feriados/v1/nacionais/buscar")
                .queryParam("search", search)
                .queryParam("sort", page.sort)
                .queryParam("pageSize", page.pageSize)
                .queryParam("pageNumber", page.pageNumber)
            .build()
            .toUri())
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
        .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
        .toEntityList<BoilerplateModel>()
        .block()


    fun save(boilerplate: BoilerplateModel): ResponseEntity<BoilerplateModel> = webClient
        .get()
        .uri(UriComponentsBuilder
            .fromHttpUrl(url!!)
            .path("base/v1/")
            .build()
            .toUri())
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
        .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
        .toEntity(BoilerplateModel::class.java)
        .block()!!
}