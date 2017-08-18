package cade.application.removespaces;

import cade.repository.ItemRepository;
import cade.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import sentilex.SentilexRepository;

@SpringBootApplication
@ComponentScan({"cade", "sentilex"})
public class RemoveSpacesSpringBootApplication {

	private static ItemRepository itemRepository;
	private static TokenRepository tokenRepo;
	private static SentilexRepository sentilexRepository;
	private static CachePalavrasRepository dbCachedPalavras;

	@Autowired
	public RemoveSpacesSpringBootApplication(ItemRepository itemRepository, TokenRepository tokenRepo,
											 SentilexRepository sentilexRepository, CachePalavrasRepository dbCachedPalavras) {
		this.itemRepository = itemRepository;
		this.tokenRepo = tokenRepo;
		this.sentilexRepository = sentilexRepository;
		this.dbCachedPalavras = dbCachedPalavras;
	}

	public static void main(String[] args) {

		SpringApplication.run(RemoveSpacesSpringBootApplication.class, args);
		RemoveSpaces manager = new RemoveSpaces(itemRepository, tokenRepo, sentilexRepository, dbCachedPalavras);
		manager.removeFromItem(6);

		System.out.println("-- FIM --");
	}



}
