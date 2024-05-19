package by.imbanamid.web3.controller;

import by.imbanamid.web3.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    /**
     * Test the /send endpoint in TransactionController.
     *
     * This test mocks the TransactionService and verifies that the endpoint
     * returns the correct response.
     */
    @Test
    public void testSendTransaction() throws Exception {
        String toAddress = "0xRecipientAddress";
        BigDecimal amount = BigDecimal.valueOf(0.01);
        String transactionHash = "0xTransactionHash";

        // Mock the transactionService to return a specific transaction hash
        when(transactionService.sendTransaction(toAddress, amount)).thenReturn(transactionHash);

        // Perform the GET request to /send and validate the response
        MvcResult result = mockMvc.perform(get("/send")
                        .param("to", toAddress)
                        .param("amount", amount.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Transaction hash: " + transactionHash))
                .andReturn();
    }
}
