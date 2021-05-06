package com.tuten.prueba.utc.service;

import com.tuten.prueba.utc.entity.UtcRequest;
import com.tuten.prueba.utc.entity.UtcResponse;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.TimeZone;

@Service
public class UtcService {

    public UtcResponse setUTC(UtcRequest request) {
        try {
            String dateFormat = "HH:mm:ss";
            SimpleDateFormat format = new SimpleDateFormat(dateFormat);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date dateTime = format.parse(request.getTime());

            Date now = dateTime;
            Instant current = now.toInstant();
            ZoneOffset offset = ZoneOffset.ofHours(Integer.parseInt(request.getTimeZone()));
            LocalDateTime ldt = LocalDateTime.ofInstant(current, ZoneId.ofOffset("UTC", offset));
            OffsetDateTime odt = OffsetDateTime.of(ldt, offset);
            String oTime = odt.toLocalTime().toString();

            UtcResponse response = new UtcResponse();
            response.setTime(oTime);
            response.setTimeZone(odt.getOffset().getId());
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
