package cade.application.removespaces;

import cade.api.DicionarioAbertoAPI;
import cade.application.generateprepositions.PrepositionProcessor;
import cade.entities.Item;
import cade.entities.Token;
import cade.repository.ItemRepository;
import cade.repository.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sentilex.SentilexData;
import sentilex.SentilexRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cade on 03/08/17.
 */
public class RemoveSpaces {

    private ItemRepository itemRepository;
    private TokenRepository tokenRepo;
    private SentilexRepository sentilexRepo;
    private CachePalavrasRepository dbCachedPalavras;

    private static final Logger log = LoggerFactory.getLogger(RemoveSpaces.class);

    public RemoveSpaces (ItemRepository itemRepository, TokenRepository tokenRepo,
                         SentilexRepository sentilexRepo, CachePalavrasRepository cachedRepo) {
        this.itemRepository = itemRepository;
        this.tokenRepo = tokenRepo;
        this.sentilexRepo = sentilexRepo;
        this.dbCachedPalavras =  cachedRepo;
    }

    private static CachedDictionary localCachedDictionary = new CachedDictionary();
    private static DicionarioAbertoAPI dicionarioAbertoApi = new DicionarioAbertoAPI();

    public void removeFromAllItems() {
        List<Integer> allItemIds = itemRepository.findAllIds();

        allItemIds.forEach( itemId -> {
            removeFromItem(itemId);
        });
    }

    public void removeFromItem(int itemId) {
        List<Token> allTokens = this.tokenRepo.getTokenByItemId(itemId);
        String corrected = getCorrectedWord(allTokens);
        saveCorrectedDescription(itemId, corrected);
    }

    public String getCorrectedWord(List<Token> allTokens) {
        List<Token> correctTokens = new ArrayList<>();
        int indexLastWord = 0;
        String wordToJoin = "";
        int MAX_JOIN = 3;
        int joinCount = 0;

        List<String> foundOnDI = new ArrayList<>();
        List<String> foundOnSX = new ArrayList<>();
        List<String> foundOnCache = new ArrayList<>();
        List<String> notFound = new ArrayList<>();
        Token token = null;

        for (int i=0; i < allTokens.size(); i++) {
            token = allTokens.get(i);
            String word = token.getForm().trim();

            String tag = token.getTag();
            boolean tokenIsNoun = tag.startsWith("N");
            if (tokenIsNoun) {
                word = wordToJoin + word;
                log.info("[Start searching by word: " + word+ "]");

                if (!word.isEmpty() && !localCachedDictionary.contains(word) && this.dbCachedPalavras.findByForm(word) == null) {

                    //eh substantivo e soh tem uma letra, "c have"
                    if(word.length() == 1) {
                        log.warn("Time to join with the next... ");
                        wordToJoin = word;
                        joinCount++;
                        continue;
                    }

                    Entry wordData = dicionarioAbertoApi.getWord(word);
                    if (wordData == null) {
                        log.warn("Word not found on dicionario aberto: " + word);

                        SentilexData sentilexData = sentilexRepo.findByForm(word);

                        if (sentilexData == null) {
                            if(joinCount <= MAX_JOIN) {
                                log.warn("Time to join with the next... ");
                                wordToJoin = word;
                                joinCount++;
                                continue;
                            } else {
                                notFound.add(word);
                                wordToJoin ="";
                                i = indexLastWord+2;
                                //incluir metaphone3
                            }

                        } else {
                            foundOnSX.add(word);
                        }
                    } else {
                        foundOnDI.add(word);
                        this.dbCachedPalavras.save(new CachePalavras(word));
                    }
                } else {
                    foundOnCache.add(word);
                }

            } else if(!wordToJoin.isEmpty()) {
                //palavras que iam ser juntas com a proxima, mas a proxima nao era substantivo
                for(; joinCount > 0; joinCount--) {
                    Token previousToken = allTokens.get(i - joinCount);
                    correctTokens.add(previousToken);
                    notFound.add(previousToken.getForm());
                }
            }
            if(tag.startsWith("PRP") && allTokens.get(i+1).getTag().startsWith("DET")){
                i++;
                word = PrepositionProcessor.join(token.getForm(), allTokens.get(i).getForm());

            }
            token.setForm(word);
            wordToJoin ="";
            joinCount = 0;
            indexLastWord = i;
            correctTokens.add(token);
        }

        if(!wordToJoin.isEmpty()) {
            //palavras que iam ser juntas com a proxima, mas a proxima nao era substantivo
            for(; joinCount > 0; joinCount--) {
                Token previousToken = allTokens.get(allTokens.size() - joinCount);
                correctTokens.add(previousToken);
                notFound.add(previousToken.getForm());
            }
        }

        String corrected = this.joinCorrected(correctTokens);
        log.info("[Item: "+ corrected + "]");
        log.info("[Total: "+ allTokens.size() + "],[DI: "+ foundOnDI.size()+ "], [SX : " + foundOnSX.size()+ "], [Cache: "+ foundOnCache.size()+ "]");
        log.info("[Total: "+ allTokens.size() + "],[DI: "+ foundOnDI.size()+ "], [SX : " + foundOnSX.size()+ "], [Cache: "+ foundOnCache.size()+ "]");

        log.error("Not found "+ notFound.size()+" [ "+ StringUtils.arrayToCommaDelimitedString(notFound.toArray())+ "]");

        return corrected;
    }

    public void saveCorrectedDescription( long itemId, String corrected) {
        Item item = this.itemRepository.findOne(itemId);
        item.setDescricao_corrigida(corrected);
        this.itemRepository.save(item);
    }

    public String joinCorrected (List<Token> tokens) {
        StringBuffer sb = new StringBuffer();
        List<String> symbols = Arrays.asList(new String[]{",", ":", ";"});

        for (int i = 0; i < tokens.size(); i++ ) {
            String x = tokens.get(i).getForm();
            if(!symbols.contains(x)){
                sb.append(" ");
            }
            sb.append(x);
        }

        return sb.toString();
    };

}
