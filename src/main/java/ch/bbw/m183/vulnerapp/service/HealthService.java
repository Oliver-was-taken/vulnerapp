package ch.bbw.m183.vulnerapp.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.Scanner;

@Service
@Transactional
@RequiredArgsConstructor
public class HealthService {

    @PreAuthorize("hasRole('ADMIN')")
    @SneakyThrows
    public String health() {
        // lookup the local actuator health endpoint, and login as admin, to get extra details
        var url = new URL("http://localhost:8080/actuator/health");
        var urlConnection = url.openConnection();
        var s = new Scanner(urlConnection.getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
