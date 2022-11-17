package org.agilekip.tutorials.travel.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.agilekip.tutorials.travel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class IngressoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IngressoDTO.class);
        IngressoDTO ingressoDTO1 = new IngressoDTO();
        ingressoDTO1.setId(1L);
        IngressoDTO ingressoDTO2 = new IngressoDTO();
        assertThat(ingressoDTO1).isNotEqualTo(ingressoDTO2);
        ingressoDTO2.setId(ingressoDTO1.getId());
        assertThat(ingressoDTO1).isEqualTo(ingressoDTO2);
        ingressoDTO2.setId(2L);
        assertThat(ingressoDTO1).isNotEqualTo(ingressoDTO2);
        ingressoDTO1.setId(null);
        assertThat(ingressoDTO1).isNotEqualTo(ingressoDTO2);
    }
}
