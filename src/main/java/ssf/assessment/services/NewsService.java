package ssf.assessment.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ssf.assessment.model.Arrays;

@Service
public class NewsService {
    private static final Logger logger = LoggerFactory.getLogger(NewsService.class);

    private static String URL = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN";

    @Autowired
    @Qualifier("newsArticle")
    RedisTemplate<String, Arrays> redisTemplate;

    public Arrays getArticles() {
        String apiKey = System.getenv("CRYPTO_COMPARE_API_KEY");

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("api_key", apiKey);
            HttpEntity request = new HttpEntity(headers);
            resp = template.exchange(
                        URL,
                        HttpMethod.GET,
                        request,
                        String.class);
            Arrays a = Arrays.createJson(resp.getBody());
            return a;
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int saveArticles(final Arrays ctc) {
        logger.info("Save news > " + logger);
        redisTemplate.opsForValue().set(ctc.getArticles(), ctc);
        Arrays result = (Arrays) redisTemplate.opsForValue().get(ctc.getArticles());
        if (result != null)
            return 1;
        return 0;
    }
}
