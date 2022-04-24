package br.com.wohr.projectservicereader.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.wohr.projectservicereader.domain.ResponseUser;
import br.com.wohr.projectservicereader.domain.User;

@Component
public class UserReader implements ItemReader<User> {

	private RestTemplate restTemplate = new RestTemplate();

	private List<User> users = new ArrayList<User>();

	private int page = 1;
	private int userIndex = 0;
	private int total = 0;

	@Value("${app.job.chunkSize}")
	private int chunkSize;

	@Value("${app.job.pageSize}")
	private int pageSize;

	@Value("${app.job.limit}")
	private int limit;

	@Override
	public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		User user = null;

		if (userIndex < users.size()) {
			user = users.get(userIndex);
		} else {
			user = null;
		}

		userIndex++;
		return user;

	}

	@BeforeChunk
	public void beforeChunk(ChunkContext context) {

		for (int i = 0; i < chunkSize; i += pageSize) {

			if (total >= limit)
				return;

			users.addAll(fetchUserDataFromAPI());
			total += users.size();
			page++;
		}
	}

	@AfterChunk
	private void afterChunk(ChunkContext context) {

		System.out.println("Fim do chunk");
		userIndex = 0;
		users = new ArrayList<User>();

	}

	private List<User> fetchUserDataFromAPI() {

		ResponseEntity<ResponseUser> response = restTemplate
				.getForEntity(String.format("https://gorest.co.in/public/v1/users?page=%d", page), ResponseUser.class);

		return response.getBody().getData();

	}
}
