package by.imbanamid.web3.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private Web3j web3j;

    @MockBean
    private Credentials credentials;

    /**
     * Test the sendTransaction method in TransactionService.
     *
     * This test mocks the dependencies and verifies that the transaction is created
     * and sent correctly.
     */
    @Test
    public void testSendTransaction() throws Exception {
        String fromAddress = "0xYourAddress";
        String toAddress = "0xRecipientAddress";
        BigDecimal amount = BigDecimal.ONE;

        // Mock the credentials to return a specific address
        when(credentials.getAddress()).thenReturn(fromAddress);

        // Mock the nonce (transaction count) response from the Ethereum node
        EthGetTransactionCount ethGetTransactionCount = Mockito.mock(EthGetTransactionCount.class);
        when(ethGetTransactionCount.getTransactionCount()).thenReturn(BigInteger.ONE);
        when(web3j.ethGetTransactionCount(fromAddress, org.web3j.protocol.core.DefaultBlockParameterName.LATEST).send())
                .thenReturn(ethGetTransactionCount);

        // Mock the transaction hash response from the Ethereum node
        EthSendTransaction ethSendTransaction = Mockito.mock(EthSendTransaction.class);
        when(ethSendTransaction.getTransactionHash()).thenReturn("0xTransactionHash");
        when(web3j.ethSendRawTransaction(any(String.class)).send()).thenReturn(ethSendTransaction);

        // Perform the transaction and get the transaction hash
        String transactionHash = transactionService.sendTransaction(toAddress, amount);

        // Validate that the transaction hash is as expected
        assertEquals("0xTransactionHash", transactionHash);
    }
}
