# Ethereum Transaction Sender

This application provides a simple way to send Ethereum transactions using Spring Boot and Web3j.

## Features

- **Send Transaction Endpoint**: Exposes an endpoint to send Ethereum transactions with specified recipient address and amount.
- **Web3j Integration**: Utilizes Web3j library for interaction with the Ethereum blockchain.
- **Credentials Handling**: Safely handles Ethereum account's private key using Spring configuration.

## Prerequisites

- Java 17
- Maven
- Ethereum node or a service provider to connect to Ethereum blockchain

## Getting Started

1. Clone this repository:

    ```bash
    git clone https://github.com/1mbanamid/web3.git
    ```


2. Update application.properties with your Ethereum node URL and private key:

    ```properties
    web3j.client-address=http://your-ethereum-node-url
    web3j.credentials.private-key=your-ethereum-private-key
    ```

3. Build the project:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    java -jar target/ethereum-transaction-sender.jar
    ```

5. The application will start on port 8080 by default. You can access the send transaction endpoint at `http://localhost:8080/send`.

## Usage

Send a transaction by making a GET request to `/send` endpoint with parameters `to` (recipient's address) and `amount` (amount of Ether to send).

Example:

```bash
curl -X GET "http://localhost:8080/send?to=0xRecipientAddress&amount=0.01"
```
## Testing

Unit tests are included to ensure the correctness of transaction sending functionality. You can run the tests using Maven:

```bash

mvn test
```
##Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or create a pull request.
## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

