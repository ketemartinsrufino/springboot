package cade.application;

import cade.entities.Especie;
import cade.entities.EspecieToken;
import cade.entities.Token;
import cade.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CountEspeciesSpringBootApplication {

	private  ClasseRepository classeRepo;
	private static EspecieRepository especieRepo;
	private static ItemRepository itemRepository;
	private  SubclasseRepository subclasseRepo;
	private static TokenRepository tokenRepo;
	private static EspecieTokenRepository especieTokenRepository;

	@Autowired
	public CountEspeciesSpringBootApplication(ItemRepository itemRepository, ClasseRepository classeRepository,
                                              TokenRepository tokenRepo, EspecieRepository especieRepo,
                                              EspecieTokenRepository especieTokenRepository) {
		this.itemRepository = itemRepository;
		this.tokenRepo = tokenRepo;
		this.classeRepo = classeRepository;
		this.especieRepo = especieRepo;
		this.especieTokenRepository = especieTokenRepository;
	}


	public static void main(String[] args) {

		SpringApplication.run(CountEspeciesSpringBootApplication.class, args);
		List<Integer> allItemIds = itemRepository.findAllIds();
		List<Token> allTokens = tokenRepo.getTokenNounVerbAdjective(1);

		System.out.println("ALL TOKENS: "+ allTokens.size());

		especieTokenRepository.deleteAll();
		for(Token t: allTokens) {
			List<Especie> especies = especieRepo.getEspeciesByNameEspecieStartingWith(t.getLemma());
			for(Especie e: especies) {
				EspecieToken especieToken = new EspecieToken();
				especieToken.setEspecieId(e.getIdEspecie());
				especieToken.setTokenId(t.getId());
				especieToken.setLemma(t.getLemma());
				especieTokenRepository.save(especieToken);
			}

			System.out.println("ALL especies start with {"+ t.getLemma()+ "} : "+ especies.size());
		}
		System.out.println("-- FIM --");
	}
}
