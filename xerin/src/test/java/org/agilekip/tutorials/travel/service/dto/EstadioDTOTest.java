package org.agilekip.tutorials.travel.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.agilekip.tutorials.travel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EstadioDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadioDTO.class);
        EstadioDTO estadioDTO1 = new EstadioDTO();
        estadioDTO1.setId(1L);
        EstadioDTO estadioDTO2 = new EstadioDTO();
        assertThat(estadioDTO1).isNotEqualTo(estadioDTO2);
        estadioDTO2.setId(estadioDTO1.getId());
        assertThat(estadioDTO1).isEqualTo(estadioDTO2);
        estadioDTO2.setId(2L);
        assertThat(estadioDTO1).isNotEqualTo(estadioDTO2);
        estadioDTO1.setId(null);
        assertThat(estadioDTO1).isNotEqualTo(estadioDTO2);
    }
}
