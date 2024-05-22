package com.example.spring_actuator_2;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// для вызова: http://localhost:8080/actuator/metrics/custom.metric.name
@Component
public class CustomMetric implements MeterBinder {
    private final AtomicInteger myMetric = new AtomicInteger(0);

    @Override
    public void bindTo(MeterRegistry registry) {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            char[] keyStorePassword = "changeit".toCharArray();
            try(InputStream keyStoreData = new FileInputStream("C:\\Java\\jdk-17.0.10\\lib\\security\\cacerts")){
//            try(InputStream keyStoreData = new FileInputStream("$JAVA_HOME\\lib\\security\\cacerts")){
                keyStore.load(keyStoreData, keyStorePassword);
            }
                Enumeration<String> stringEnumeration = keyStore.aliases();
                List<String> strTags = new ArrayList<>();
                while (stringEnumeration.asIterator().hasNext()){
                    String alias = stringEnumeration.asIterator().next();
                    strTags.add(alias);
                    Certificate certificate = keyStore.getCertificate(alias);

                    X509Certificate myCert = (X509Certificate) CertificateFactory
                            .getInstance("X509")
                            .generateCertificate(
                                    new ByteArrayInputStream(certificate.getEncoded())
                            );
                    // Продолжительность действия сертификата оставшаяся в днях.
                    Long daysDuration = Duration.between(Instant.now(), myCert.getNotAfter().toInstant()).toDays();
                    // Для продолжительности сертификата с момента начала действия.
                    //Long daysDuration = Duration.between(myCert.getNotBefore().toInstant(), myCert.getNotAfter().toInstant()).toDays();
                    strTags.add(daysDuration.toString());
                }
                Gauge.builder("custom.metric.name", myMetric, AtomicInteger::get)
                        .description("List of certificates")
                        .tags(strTags.toArray(new String[0]))
                        .register(registry);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void increment() {
        myMetric.incrementAndGet();
    }
}
