package org.agilekip.tutorials.travel.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.agilekip.tutorials.travel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TimeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TimeDTO.class);
        TimeDTO timeDTO1 = new TimeDTO();
        timeDTO1.setId(1L);
        TimeDTO timeDTO2 = new TimeDTO();
        assertThat(timeDTO1).isNotEqualTo(timeDTO2);
        timeDTO2.setId(timeDTO1.getId());
        assertThat(timeDTO1).isEqualTo(timeDTO2);
        timeDTO2.setId(2L);
        assertThat(timeDTO1).isNotEqualTo(timeDTO2);
        timeDTO1.setId(null);
        assertThat(timeDTO1).isNotEqualTo(timeDTO2);
    }
}
