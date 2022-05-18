package com.lc.apilc.configuration;

import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
@Getter
public class InternationalizationConfig {

//    @Value("{campo.cliente.nome.obrigatorio}")
//    private String clienteNomeObrigatorio;

//    @Value("{campo.cliente.endereco.obrigatorio}")
//    private String clienteEnderecoObrigatorio;
//
//    @Value("{campo.cliente.telefone.obrigatorio}")
//    private String clienteTelefoneObrigatorio;
//
//    @Value("{campo.cliente.documento.obrigatorio}")
//    private String clienteDocumentoObrigatorio;
//
//    @Value("{campo.cliente.email.obrigatorio}")
//    private String clienteEmailObrigatorio;
//
//    @Value("{campo.departamento.nome.obrigatorio}")
//    private String clienteDepartamentoObrigatorio;
//
//    @Value("{campo.login.login.obrigatorio}")
//    private String loginObrigatorio;
//
//    @Value("{campo.login.senha.obrigatorio}")
//    private String senhaObrigatoria;
//
//    @Value("{campo.fornecedor.nome.obrigatorio}")
//    private String fornecedorNomeObrigatorio;
//
//    @Value("{campo.fornecedor.endereco.obrigatorio}")
//    private String fornecedorEnderecoObrigatorio;
//
//    @Value("{campo.fornecedor.telefone.obrigatorio}")
//    private String fornecedorTelefoneObrigatorio;
//
//    @Value("{campo.fornecedor.documento.obrigatorio}")
//    private String fornecedorDocuementoObrigatorio;
//
//    @Value("{campo.fornecedor.email.obrigatorio}")
//    private String fornecedorEmailObrigatorio;
//
//    @Value("{campo.usuario.nome.obrigatorio}")
//    private String usuarioNomeObrigatorio;
//
//    @Value("{campo.usuario.login.obrigatorio}")
//    private String usuarioLoginObrigatorio;
//
//    @Value("{campo.usuario.senha.obrigatorio}")
//    private String usuarioSenhaObrigatorio;
//
//    @Value("{campo.usuario.isAdm.obrigatorio}")
//    private String usuarioIsAdminObrigatorio;
//
//    @Value("{campo.usuario.departamento.obrigatorio}")
//    private String usuarioDepartamentoObrigatorio;
//
//    @Value("{campo.os.cliente.obrigatorio}")
//    private String osClienteObrigatorio;
//
//    @Value("{campo.os.usuario.obrigatorio}")
//    private String osUsuarioObrigatorio;
//
//    @Value("{campo.statusos.status.obrigatorio}")
//    private String statusObrigatorio;
//
//    @Value("{mensagem.upload.post}")
//    private String arquivoEnviado;
//
//    @Value("{mensagem.upload.delete}")
//    private String arquivoDeletado;
//
//    @Value("{mensagem.upload.acl.invalido}")
//    private String aclInvalido;
//
//    @Value("{mensagem.usuario.nao.encontrado}")
//    private String usuarioNaoEncontrado;
//
//    @Value("{mensagem.login.ja.existe}")
//    private String loginJaExiste;
//
//    @Value("{mensagem.os.nao.encontrada}")
//    private String osNaoEncontrada;

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:pt-br");
        messageSource.setDefaultLocale(Locale.getDefault());
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
