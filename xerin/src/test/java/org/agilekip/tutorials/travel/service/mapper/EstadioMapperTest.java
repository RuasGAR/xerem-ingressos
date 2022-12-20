package org.agilekip.tutorials.travel.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EstadioMapperTest {

    private EstadioMapper estadioMapper;

    @BeforeEach
    public void setUp() {
        estadioMapper = new EstadioMapperImpl();
    }
}
