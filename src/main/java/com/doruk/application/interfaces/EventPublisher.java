package com.doruk.application.interfaces;

public interface EventPublisher {
    enum EventSubject {
        LICENSE_WEBHOOK("license.webhook");

        private final String subject;

        EventSubject(String subject) {
            this.subject = subject;
        }

        public String getSubject() {
            return subject;
        }

        @Override
        public String toString() {
            return subject;
        }
    }

    void publish(EventDto eventDto);
    void publish(EventSubject subject, String payload);
}
