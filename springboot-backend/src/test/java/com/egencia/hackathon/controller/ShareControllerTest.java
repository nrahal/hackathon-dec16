package com.egencia.hackathon.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShareControllerTest {

    private ShareController shareController = new ShareController();

    private MockMvc mockMvc;

    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(shareController).build();
    }

    @Test
    public void should_create_facebook_publication_model() throws Exception {
        mockMvc.perform(get("/share/facebook/ew0KCSJkYXRlIjogIjIwMTYtMTEtMjFUMTQ6MjU6MDArMDEwMCIsDQoJImRlc3RpbmF0aW9uIjogIlBhcmlzIg0KfQ=="))
                // Then
                .andExpect(status().isOk());
    }

}