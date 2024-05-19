package by.imbanamid.web3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class TransactionService {

    @Autowired
    private Web3j web3j;

    @Autowired
    private Credentials credentials;

    /**
     * Sends a transaction to transfer a specified amount of Ether to a given address.
     *
     * @param toAddress the recipient's Ethereum address.
     * @param amountInEther the amount of Ether to send.
     * @return the transaction hash of the sent transaction.
     * @throws Exception if an error occurs while sending the transaction.
     */
    public String sendTransaction(String toAddress, BigDecimal amountInEther) throws Exception {
        BigInteger nonce = getNonce(credentials.getAddress());
        BigInteger gasLimit = BigInteger.valueOf(21000);
        BigInteger gasPrice = Convert.toWei("20", Convert.Unit.GWEI).toBigInteger();
        BigInteger value = Convert.toWei(amountInEther, Convert.Unit.ETHER).toBigInteger();

        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
                nonce, gasPrice, gasLimit, toAddress, value);

        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);

        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();

        return ethSendTransaction.getTransactionHash();
    }

    /**
     * Retrieves the nonce for the given Ethereum address.
     *
     * @param address the Ethereum address to retrieve the nonce for.
     * @return the nonce for the specified address.
     * @throws Exception if an error occurs while retrieving the nonce.
     */
    private BigInteger getNonce(String address) throws Exception {
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                address, org.web3j.protocol.core.DefaultBlockParameterName.LATEST).send();
        return ethGetTransactionCount.getTransactionCount();
    }
}
