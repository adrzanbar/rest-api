package com.uncode.stop.rest_api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uncode.stop.rest_api.error.NotFoundException;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.mapper.DtoMapper;
import com.uncode.stop.rest_api.service.CrudService;
import com.uncode.stop.rest_api.service.Identifiable;

import jakarta.validation.ConstraintViolationException;

public abstract class CrudController<E extends Identifiable<ID>, ID, DTO> {

    protected abstract CrudService<E, ID> getService();

    protected abstract DtoMapper<E, DTO> getMapper();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DTO create(@RequestBody DTO dto) {
        return getMapper().toDto(getService().create(getMapper().toEntity(dto)));
    }

    @GetMapping("/{id}")
    public DTO read(@PathVariable ID id) {
        return getMapper().toDto(getService().readOne(id));
    }

    @GetMapping
    public Page<DTO> read(Pageable pageable) {
        return getService().readMany(pageable).map(getMapper()::toDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DTO update(@PathVariable ID id, @RequestBody DTO dto) {
        E entity = getMapper().toEntity(dto);
        entity.setId(id);
        return getMapper().toDto(getService().update(entity));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable ID id) {
        getService().delete(id);
        return "Deleted";
    }

    @ExceptionHandler(TransactionSystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleTransactionSystemException(TransactionSystemException e) {
        var response = new HashMap<String, Object>();

        // Primera cause es RollbackException
        // Segunda cause es TransactionSystemException
        Throwable cause = e.getCause();
        if (cause != null) {
            cause = cause.getCause();
        }
        if (cause instanceof ConstraintViolationException) {
            var ex = (ConstraintViolationException) cause;

            // Construir un mapa de errores de validación
            // {
            // "fieldErrors": {
            // "correo": "Correo requerido",
            // "apellido": "Apellido requerido",
            // "telefono": "Teléfono requerido",
            // "nombre": "Nombre requerido"
            // },
            // "error": "Validation Error"
            // }
            response.put("error", "Validation Error");
            var errors = new HashMap<String, String>();
            ex.getConstraintViolations().forEach(violation -> {
                String field = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                errors.put(field, message);
            });
            response.put("fieldErrors", errors);
        } else {
            response.put("error", "Bad Request");
            response.put("message", "Invalid request body");
        }
        return response;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFoundException(NotFoundException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Not Found");
        response.put("message", "The requested resource was not found.");
        return response;
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleServiceException(ServiceException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Error de servicio");
        response.put("message", e.getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        // return e.getMessage();
        return "Error interno del servidor";
    }

}
