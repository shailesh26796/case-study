package com.ntt.player.controller;

import com.ntt.player.entity.Player;
import com.ntt.player.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    @WithMockUser
    public void testGetPlayersByName() throws Exception {
        List<Player> playerList = new ArrayList<>();
        LocalDate date = LocalDate.of(1970, 9, 16);
        String playerName = "Sachin";
        Player player = new Player(1L,"India",date,"Batter","Sachin","Tendulkar",50000L);
        Player player1 = new Player(2L,"India",date,"Batter","Sachin","Dravid",5000L);
        playerList.add(player);
        playerList.add(player1);
        Mockito.when(playerService.getPlayersByName(playerName)).thenReturn(playerList);

        mockMvc.perform(MockMvcRequestBuilders.get("/player/name/{name}", playerName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{" +
                        "\"id\":1," +
                        "\"country\":\"India\"," +
                        "\"dateOfBirth\":\"1970-09-16\"," +
                        "\"position\":\"Batter\"," +
                        "\"firstName\":\"Sachin\"," +
                        "\"lastName\":\"Tendulkar\"," +
                        "\"runs\":50000" +
                        "},{\"id\":2," +
                        "\"country\":\"India\","+
                        "\"dateOfBirth\":\"1970-09-16\"," +
                        "\"position\":\"Batter\"," +
                        "\"firstName\":\"Sachin\"," +
                        "\"lastName\":\"Dravid\","+
                        "\"runs\":5000}]"));
    }
}