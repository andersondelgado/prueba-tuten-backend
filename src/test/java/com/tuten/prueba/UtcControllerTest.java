package com.tuten.prueba;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuten.prueba.utc.entity.UtcRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = PruebaApplication.class)
@AutoConfigureMockMvc
public class UtcControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postUtcTest() throws Exception {
        try {
            String url = "/utc";
            UtcRequest request = new UtcRequest();
            request.setTime("13:35:01");
            request.setTimeZone("-1");

            String payload = objectMapper.writeValueAsString(request);
            System.out.println("request: "+payload);
            ResultActions result = this.mockMvc.perform(
                    post(url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(payload)

            ).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
            System.out.println("response: "+result.andReturn().getResponse().getContentAsString());
            Assertions.assertNotNull(result.andReturn().getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
