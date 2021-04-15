package com.mrl;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@SpringBootApplication
@Slf4j
public class CosmosDbApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(CosmosDbApplication.class);

	@Autowired
	private UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CosmosDbApplication.class, args);
	}

	public void run(String... var1) {
		this.repository.deleteAll();
		// LOGGER.info("Deleted all data in container.");

		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setFirstName(RandomStringUtils.randomAlphabetic(6));
		user.setLastName(RandomStringUtils.randomAlphabetic(10));
		user.setAddress(RandomStringUtils.randomAlphanumeric(20));

		// Save the User class to Azure Cosmos DB database.
		final Mono<User> savedUserMono = repository.save(user);
		log.info("Saved user with UUID {}", user.getId());

		final User savedUser = savedUserMono.block();
		log.info("Saved user: {}", savedUser);

		// final Flux<User> firstNameUserFlux =
		// repository.findByFirstName("testFirstName");

		// Nothing happens until we subscribe to these Monos.
		// findById will not return the user as user is not present.
		// final Mono<User> findByIdMono = repository.findById(testUser.getId());
		// final User findByIdUser = findByIdMono.block();
		// Assert.isNull(findByIdUser, "User must be null");

		// final User savedUser = saveUserMono.block();
		// Assert.state(savedUser != null, "Saved user must not be null");
		// Assert.state(savedUser.getFirstName().equals(testUser.getFirstName()), "Saved
		// user first name doesn't match");

		// firstNameUserFlux.collectList().block();

		// final Optional<User> optionalUserResult =
		// repository.findById(testUser.getId()).blockOptional();
		// Assert.isTrue(optionalUserResult.isPresent(), "Cannot find user.");

		// final User result = optionalUserResult.get();
		// Assert.state(result.getFirstName().equals(testUser.getFirstName()), "query
		// result firstName doesn't match!");
		// Assert.state(result.getLastName().equals(testUser.getLastName()), "query
		// result lastName doesn't match!");

		// LOGGER.info("findOne in User collection get result: {}", result.toString());
	}
}