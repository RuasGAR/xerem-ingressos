package org.agilekip.tutorials.travel.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.agilekip.tutorials.travel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EstadioTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Estadio.class);
        Estadio estadio1 = new Estadio();
        estadio1.setId(1L);
        Estadio estadio2 = new Estadio();
        estadio2.setId(estadio1.getId());
        assertThat(estadio1).isEqualTo(estadio2);
        estadio2.setId(2L);
        assertThat(estadio1).isNotEqualTo(estadio2);
        estadio1.setId(null);
        assertThat(estadio1).isNotEqualTo(estadio2);
    }
}
