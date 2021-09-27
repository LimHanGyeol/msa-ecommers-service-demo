package com.tommy.catalogs.presentation;

import com.tommy.catalogs.application.CatalogService;
import com.tommy.catalogs.domain.Catalog;
import com.tommy.catalogs.vo.CatalogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping(value = "/catalogs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CatalogResponse>> getCatalogs() {
        List<Catalog> accounts = catalogService.finaAllCatalogs();

        List<CatalogResponse> accountResponses = accounts.stream()
                .map(CatalogResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .body(accountResponses);
    }
}
