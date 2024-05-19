package by.imbanamid.web3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {

    @Value("${web3j.client-address}")
    private String clientAddress;

    @Value("${web3j.credentials.private-key}")
    private String privateKey;

    /**
     * Creates a Web3j bean to interact with the Ethereum blockchain.
     *
     * @return an instance of Web3j connected to the specified client address.
     */
    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(clientAddress));
    }

    /**
     * Creates a Credentials bean to handle the Ethereum account's private key.
     *
     * @return an instance of Credentials created from the specified private key.
     */
    @Bean
    public Credentials credentials() {
        return Credentials.create(privateKey);
    }
}
