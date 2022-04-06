package com.arquitetura.hexagonal.adapters.mapper

import org.modelmapper.ModelMapper
import reactor.core.publisher.Flux
import java.util.stream.Collectors

class Converter {

    companion object {


        private val modelMapper = ModelMapper()

        fun <D> toMonoModel(user: Any?, outClass: Class<D>?): D? {
            return modelMapper.map(user, outClass)
        }

        fun <D> toFluxCollection(users: Flux<*>, outClass: Class<D>?): Flux<D>? {
            return users.flatMap { user: Any? -> users }
                .map { user: Any? -> toMonoModel(user, outClass) }
        }

        fun <D> toModel(user: Any, outClass: Class<D>?): D {
            return modelMapper.map(user, outClass)
        }

        fun <D> toCollection(users: List<*>, outClass: Class<D>?): MutableList<D> {
            return users.stream().map { user ->
                user?.let {
                    toModel(it, outClass)
                }
            }.collect(Collectors.toList())
        }
    }
}