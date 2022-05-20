package com.algaworks.algalog.api.exceptionhander;

import lombok.AllArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//Essa anotação diz que a classe é um componente do spring, porém com um proposito, que é tratar exceções de forma...
//global, ou seja, pra todos os controlladores da application.
@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    //Interface para resolver mensagens
    private MessageSource messageSource;

    //Metodo da ResponseEntityExceptionHandler
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<Problema.Campo> listCampos = new ArrayList<>();
        //Pegando todos os erros que foram atribuidos a essa requisição. getAllError retorna um list
        for (ObjectError error : ex.getBindingResult().getAllErrors()){

            //Fazendo cast do error. Só foi possivel porque o ObjectError é domesmo tipo que o error.
            String nome = ((FieldError)error).getField();

            //LocaleContextHolder.getLocale() pega o locale associoado a esse processamento
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            listCampos.add(new Problema.Campo(nome, mensagem));
        }

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(LocalDateTime.now());
        problema.setTitulo("Um ou mais campo estão inválido. Faça o preenchimento correto e tente novamente!");
        problema.setCampos(listCampos);
        //Metodo da ResponseEntityExceptionHandler
        return handleExceptionInternal(ex, problema, headers, status, request);
    }
}