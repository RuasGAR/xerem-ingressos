package org.agilekip.tutorials.travel.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.agilekip.tutorials.travel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TimeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Time.class);
        Time time1 = new Time();
        time1.setId(1L);
        Time time2 = new Time();
        time2.setId(time1.getId());
        assertThat(time1).isEqualTo(time2);
        time2.setId(2L);
        assertThat(time1).isNotEqualTo(time2);
        time1.setId(null);
        assertThat(time1).isNotEqualTo(time2);
    }
}
