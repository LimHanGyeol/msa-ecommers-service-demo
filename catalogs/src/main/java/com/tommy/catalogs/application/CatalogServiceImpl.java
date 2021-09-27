package com.tommy.catalogs.application;

import com.tommy.catalogs.domain.Catalog;
import com.tommy.catalogs.domain.CatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    @Override
    public List<Catalog> finaAllCatalogs() {
        return catalogRepository.findAll();
    }
}
