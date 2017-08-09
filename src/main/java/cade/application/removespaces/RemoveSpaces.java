package cade.application.removespaces;

import cade.api.DicionarioAbertoAPI;
import cade.entities.Token;
import cade.repository.ItemRepository;
import cade.repository.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cade on 03/08/17.
 */
public class RemoveSpaces {

    private ItemRepository itemRepository;
    private TokenRepository tokenRepo;
    private static final Logger log = LoggerFactory.getLogger(RemoveSpaces.class);

    public RemoveSpaces (ItemRepository itemRepository, TokenRepository tokenRepo) {
        this.itemRepository = itemRepository;
        this.tokenRepo = tokenRepo;
    }

    private static CachedDictionary cachedDictionary = new CachedDictionary();
    private static DicionarioAbertoAPI daApi = new DicionarioAbertoAPI();

    public void removeFromAllItems() {
        List<Integer> allItemIds = itemRepository.findAllIds();

        allItemIds.forEach( itemId -> {
            removeFromItem(itemId);
        });
    }

    public void removeFromItem(int itemId) {
        List<Token> allTokens = this.tokenRepo.getTokenByItemId(itemId);
        List<Token> correctTokens = new ArrayList<>();

        allTokens.forEach((Token token) -> {
            boolean tokenIsNoun = token.getTag().startsWith("N");
            if(tokenIsNoun){
                String word = token.getLemma().trim();
                if(!word.isEmpty() && !cachedDictionary.contains(word)){
                    Entry wordData = daApi.getWord(word);
                    if(wordData == null) {
                        log.error("Data not found to word: "+ word);
                    }

                }
            }

            correctTokens.add(token);
        });

        log.info("Success: "+ DicionarioAbertoAPI.successsQte + ", Error: "+ DicionarioAbertoAPI.errorsQte);

    }

}
