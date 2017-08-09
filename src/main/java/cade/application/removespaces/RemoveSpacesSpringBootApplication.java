package cade.application.removespaces;

import cade.repository.ItemRepository;
import cade.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cade")
public class RemoveSpacesSpringBootApplication {

	private static ItemRepository itemRepository;
	private static TokenRepository tokenRepo;

	@Autowired
	public RemoveSpacesSpringBootApplication(ItemRepository itemRepository, TokenRepository tokenRepo) {
		this.itemRepository = itemRepository;
		this.tokenRepo = tokenRepo;
	}

	public static void main(String[] args) {

		SpringApplication.run(RemoveSpacesSpringBootApplication.class, args);
		RemoveSpaces manager = new RemoveSpaces(itemRepository, tokenRepo);
		manager.removeFromItem(5);

		System.out.println("-- FIM --");
	}



}
