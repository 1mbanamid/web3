package by.imbanamid.web3.controller;

import by.imbanamid.web3.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Endpoint to send an Ethereum transaction.
     *
     * @param to the recipient's Ethereum address.
     * @param amount the amount of Ether to send.
     * @return a message containing the transaction hash or an error message.
     */
    @GetMapping("/send")
    public String sendTransaction(@RequestParam String to, @RequestParam BigDecimal amount) {
        try {
            String transactionHash = transactionService.sendTransaction(to, amount);
            return "Transaction hash: " + transactionHash;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
