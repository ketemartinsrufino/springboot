package sentilex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SentilexSpringBootApplication {

	private static DataRepository dataRepository;

	@Autowired
	public SentilexSpringBootApplication(DataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}

	public static void main(String[] args) throws IOException {

		SpringApplication.run(SentilexSpringBootApplication.class, args);

		String filePath = "src/main/java/sentilex/SentiLexPT01/SentiLex-flex-PT01.txt";

		try (Stream<String> lines = Files.lines(Paths.get(filePath))){
			List headers = Arrays.asList(new String[]{"PoS", "GN", "TG", "POL", "ANOT", "="});
			List<String[]> result = lines.map(it -> it.split(",\\s*|\\.\\s*|;")) // dá split nos ;
					.map(it -> {

						for(int i = 0; i < it.length; i++) {
							int finalI = i;
							headers.forEach(h -> {
								it[finalI] = it[finalI].replace((CharSequence) h, "");
							});
						}
						return it;
					})
					.map ( it -> {
						dataRepository.save(new Data(it));
						return it;
					})
					.collect(Collectors.toList());

			System.out.println(result);

		}
	}

}
