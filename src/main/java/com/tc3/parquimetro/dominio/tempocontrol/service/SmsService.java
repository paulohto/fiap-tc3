package com.tc3.parquimetro.dominio.tempocontrol.service;

import com.tc3.parquimetro.dominio.tempocontrol.entidade.Tempo;
import com.tc3.parquimetro.dominio.tempocontrol.repositorio.ITempoRepositorio;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*
 Classe responsavel pela regra de negocio referente ao sistema de alerta.
 ToDo - Recurso de alerta que notifica o condutor.
 ToDo - Quando o tempo de estacionamento está prestes a expirar para horário fixo.
 ToDo - Para períodos variáveis emite um alerta informando que o sistema estenderá automaticamente o estacionamento por mais uma hora.
 ToDo - O sistema monitora o tempo com precisão para garantir a cobrança correta, a menos que o condutor desligue o registro.
 */
@Service
public class SmsService {

    @Autowired
    private ITempoRepositorio repoTempo;

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    public void sendSms(Long tempoId){

        Tempo tempo = repoTempo.findById(tempoId).get();
        String msg = "O tempo de " + tempo.getTempoContratado() + " está preste a vencer "
                + " por favor, renove ou pare o tempo agora!";

        Twilio.init(twilioSid, twilioKey);

        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

        Message message = Message.creator(to, from, msg).create();
        System.out.println(message.getSid());
    }
}
