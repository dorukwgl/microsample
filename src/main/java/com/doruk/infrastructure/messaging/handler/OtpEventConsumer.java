package com.doruk.infrastructure.messaging.handler;

import com.doruk.application.events.OtpDeliveryEvent;
import com.doruk.application.interfaces.MailService;
import com.doruk.application.interfaces.SmsService;
import com.doruk.infrastructure.config.AppExecutors;
import io.micronaut.context.annotation.Context;
import io.micronaut.json.JsonMapper;
import io.nats.client.Connection;

@Context
public class OtpEventConsumer extends EventConsumer<OtpDeliveryEvent> {
    private final SmsService smsService;
    private final MailService mailService;

    public OtpEventConsumer(AppExecutors executors, Connection natsConnection, JsonMapper jsonMapper, SmsService smsService, MailService mailService) {
        super(executors, natsConnection, jsonMapper);
        this.smsService = smsService;
        this.mailService = mailService;
    }

    @Override
    protected String subject() {
        return "event.otp.delivery";
    }

    @Override
    protected String durable() {
        return "otp-delivery-consumer";
    }

    @Override
    protected String fetchErrorMsg() {
        return "Fetch Error: Failed to fetch message durable: otp-delivery-consumer";
    }

    @Override
    protected String deserializationErrorMsg() {
        return "Deserialization Error: Failed to deserialize OTP delivery event, terminating";
    }

    @Override
    protected String processFailureMsg() {
        return "Failed to process OTP delivery event";
    }

    @Override
    protected Class<OtpDeliveryEvent> getConsumedEventTypeClass() {
        return OtpDeliveryEvent.class;
    }

    @Override
    protected AckAction processEvent(OtpDeliveryEvent event) {
        switch (event.channel()) {
            case EMAIL -> mailService.sendMail(
                    new MailService.MailParams("", event.to(), event.magicLink(), event.otp()),
                    event.contentTemplate());
            case PHONE -> smsService.sendOtp(event.to(), event.otp(), event.contentTemplate());
        }

        return AckAction.ACK;
    }
}
